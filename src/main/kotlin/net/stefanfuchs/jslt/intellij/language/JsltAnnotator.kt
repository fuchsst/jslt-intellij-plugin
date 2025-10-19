package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiRecursiveElementWalkingVisitor
import com.intellij.psi.TokenType
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.elementType
import com.schibsted.spt.data.jslt.impl.BuiltinFunctions
import net.stefanfuchs.jslt.intellij.language.psi.*


class JsltAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is PsiErrorElement -> annotateError(element, holder)
            is JsltVariableUsage -> annotateVariableUsage(element, holder)
            is JsltLetVariableDecl -> annotateLetVariableDecl(element, holder)
            is JsltFunctionName -> annotateFunctionName(element, holder)
            is JsltFunctionDeclNameDecl -> annotateFunctionDeclNameDecl(element, holder)
            is JsltFunctionDeclParamDecl -> annotateFunctionDeclParamDecl(element, holder)
            is JsltImportDeclaration -> annotateImportDecl(element, holder)
            is JsltPair -> annotatePair(element, holder)
            is JsltFunctionCall -> annotateFunctionCall(element, holder)
        }

        // Check for BAD_CHARACTER tokens
        if (element.node.elementType == TokenType.BAD_CHARACTER) {
            holder
                .newAnnotation(HighlightSeverity.ERROR, "Unexpected character: '${element.text}'")
                .create()
        }
    }

    private fun annotateError(element: PsiErrorElement, holder: AnnotationHolder) {
        val errorDesc = element.errorDescription
        // Provide more meaningful error messages
        val message = when {
            errorDesc.contains("expected") -> {
                // Try to improve the message
                errorDesc.replace("IDENT", "identifier")
                    .replace("LPAREN", "'('")
                    .replace("RPAREN", "')'")
                    .replace("LBRACE", "'{'")
                    .replace("RBRACE", "'}'")
                    .replace("LBRACKET", "'['")
                    .replace("RBRACKET", "']'")
                    .replace("COMMA", "','")
                    .replace("COLON", "':'")
                    .replace("SEMICOLON", "';'")
            }
            else -> errorDesc
        }
        
        holder
            .newAnnotation(HighlightSeverity.ERROR, message)
            .create()
    }

    private fun annotateVariableUsage(element: JsltVariableUsage, holder: AnnotationHolder) {
        val varDecl = element.reference.resolve()
        if (varDecl == null) {
            holder.newAnnotation(HighlightSeverity.WARNING, "Unknown variable").create()
        } else if (varDecl.elementType == JsltTypes.FUNCTION_DECL_PARAM_DECL) {
            holder
                .newAnnotation(HighlightSeverity.INFORMATION, "Function parameter")
                .range(element.textRange)
                .textAttributes(JsltSyntaxHighlighter.PARAMETER)
                .create()
        } else {
            val letAssignment = varDecl.parent
            if (letAssignment.parent !is JsltFile) {
                holder
                    .newAnnotation(HighlightSeverity.INFORMATION, "Local variable")
                    .range(element.textRange)
                    .textAttributes(JsltSyntaxHighlighter.LOCAL_VARIABLE)
                    .create()
            }
        }
    }

    private fun annotateLetVariableDecl(element: JsltLetVariableDecl, holder: AnnotationHolder) {
        val letAssignment = element.parent
        val nameCount = letAssignment.parent.childrenOfType<JsltLetAssignment>().count { it.name == element.name }
        if (nameCount > 1) {
            holder
                .newAnnotation(HighlightSeverity.ERROR, "Duplicate variable declaration")
                .create()
        } else {
            // Check if variable is unused
            if (!isVariableUsed(element)) {
                holder
                    .newAnnotation(HighlightSeverity.WARNING, "Unused variable '${element.name}'")
                    .create()
            }
        }
    }

    private fun annotateFunctionName(element: JsltFunctionName, holder: AnnotationHolder) {
        val funcDecl = element.reference.resolve()
        if (funcDecl !is JsltFunctionDeclNameDecl) {
            val funcName = element.name ?: ""
            // Check if it's a built-in function (from JSLT library or our documentation)
            val isBuiltinFromLibrary = funcName in BuiltinFunctions.functions.keys
            val isDocumented = JsltBuiltinFunctionDocumentation.getDocumentation(funcName) != null
            
            if (isBuiltinFromLibrary || isDocumented) {
                val doc = JsltBuiltinFunctionDocumentation.getDocumentation(funcName) ?: "Built-in function"
                holder
                    .newAnnotation(HighlightSeverity.INFORMATION, doc)
                    .range(element.textRange)
                    .textAttributes(JsltSyntaxHighlighter.BUILDIN_FUNCTION_NAME)
                    .create()
            } else {
                if (element.firstChild.elementType == JsltTypes.IDENT) {
                    holder.newAnnotation(HighlightSeverity.WARNING, "Undefined function").create()
                } else {
                    val importAlias = element.importAlias
                    val importDecl = element
                        .containingFile
                        .childrenOfType<JsltImportDeclarations>()
                        .firstOrNull()
                        ?.importDeclarationList
                        ?.firstOrNull { it.name == importAlias }
                    if (importDecl == null) {
                        holder
                            .newAnnotation(HighlightSeverity.ERROR, "Undefined import alias $importAlias")
                            .create()
                    } else {
                        val importedFile = importDecl.node.findChildByType(JsltTypes.IMPORT_FILE_STRING)?.text
                        holder
                            .newAnnotation(HighlightSeverity.ERROR, "Function not found in imported file $importedFile")
                            .create()
                    }
                }

            }
        }
    }

    private fun annotateFunctionDeclNameDecl(element: JsltFunctionDeclNameDecl, holder: AnnotationHolder) {
        val nameCount = element.containingFile.childrenOfType<JsltFunctionDecl>().count { it.name == element.name }
        if (nameCount > 1) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Duplicate function declaration").create()
        } else {
            // Check if function is unused
            if (!isFunctionUsed(element)) {
                holder
                    .newAnnotation(HighlightSeverity.WARNING, "Unused function '${element.name}'")
                    .create()
            }
        }
    }

    private fun annotateFunctionDeclParamDecl(element: JsltFunctionDeclParamDecl, holder: AnnotationHolder) {
        val paramNameCount = (element.parent as JsltFunctionDeclParamList)
            .functionDeclParamDeclList
            .count { it.name == element.name }
        if (paramNameCount > 1) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Duplicate parameter declaration").create()
        } else {
            // Check if parameter is unused
            if (!isParameterUsed(element)) {
                holder
                    .newAnnotation(HighlightSeverity.WARNING, "Unused parameter '${element.name}'")
                    .create()
            }
        }
    }

    private fun annotateImportDecl(element: JsltImportDeclaration, holder: AnnotationHolder) {
        if (element.reference.resolve() == null) {
            holder
                .newAnnotation(HighlightSeverity.ERROR, "Referenced import file not found!")
                .range(element.reference.absoluteRange)
                .create()
        }
        val aliasCount = element.parent.children.count { (it as JsltImportDeclaration).name == element.name }
        if (aliasCount > 1) {
            holder
                .newAnnotation(HighlightSeverity.ERROR, "Duplicate import alias")
                .range(element.nameIdentifier!!.textRange)
                .create()
        } else {
            // Check if import alias is unused
            if (!isImportAliasUsed(element)) {
                holder
                    .newAnnotation(HighlightSeverity.WARNING, "Unused import alias '${element.name}'")
                    .range(element.nameIdentifier!!.textRange)
                    .create()
            }
        }
    }

    private fun annotatePair(element: JsltPair, holder: AnnotationHolder) {
        val elemParent = element.parent
        if (elemParent is JsltPairs) {
            val keyCount = elemParent
                .pairList
                .map { it.exprList.first().text }
                .count { it == element.exprList.first().text }
            if (keyCount > 1) {
                holder
                    .newAnnotation(HighlightSeverity.ERROR, "Duplicate key")
                    .range(element.exprList.first().textRange)
                    .create()
            }
        }
    }

    private fun annotateFunctionCall(element: JsltFunctionCall, holder: AnnotationHolder) {
        val functionName = element.functionName
        val funcDecl = functionName.reference.resolve()
        val callArgCount = element.exprList.size
        
        // Check if it's a user-defined function (not a built-in)
        if (funcDecl is JsltFunctionDeclNameDecl) {
            val functionDecl = funcDecl.parent as? JsltFunctionDecl
            if (functionDecl != null) {
                val declaredParamCount = functionDecl.functionDeclParamList?.functionDeclParamDeclList?.size ?: 0
                
                if (declaredParamCount != callArgCount) {
                    holder
                        .newAnnotation(
                            HighlightSeverity.ERROR, 
                            "Function '${functionName.name}' expects $declaredParamCount parameter(s), but $callArgCount provided"
                        )
                        .range(element.textRange)
                        .create()
                }
            }
        } else {
            // Check built-in functions
            val funcName = functionName.name ?: return
            val paramInfo = getBuiltinFunctionParamInfo(funcName)
            
            if (paramInfo != null) {
                val isValid = paramInfo.isValidParamCount(callArgCount)
                
                if (!isValid) {
                    val errorMsg = paramInfo.getErrorMessage(funcName, callArgCount)
                    holder
                        .newAnnotation(HighlightSeverity.ERROR, errorMsg)
                        .range(element.textRange)
                        .create()
                }
            }
        }
    }
    
    /**
     * Parameter information for built-in functions
     */
    private sealed class ParamInfo {
        abstract fun isValidParamCount(count: Int): Boolean
        abstract fun getErrorMessage(funcName: String, actualCount: Int): String
        
        /** Fixed number of parameters */
        data class Fixed(val count: Int) : ParamInfo() {
            override fun isValidParamCount(count: Int) = count == this.count
            override fun getErrorMessage(funcName: String, actualCount: Int) =
                "Function '$funcName' expects $count parameter(s), but $actualCount provided"
        }
        
        /** Range of parameters (for optional parameters) */
        data class Range(val min: Int, val max: Int) : ParamInfo() {
            override fun isValidParamCount(count: Int) = count in min..max
            override fun getErrorMessage(funcName: String, actualCount: Int) =
                "Function '$funcName' expects $min-$max parameter(s), but $actualCount provided"
        }
        
        /** Variadic parameters (minimum required, unlimited maximum) */
        data class Variadic(val minParams: Int) : ParamInfo() {
            override fun isValidParamCount(count: Int) = count >= minParams
            override fun getErrorMessage(funcName: String, actualCount: Int) =
                "Function '$funcName' requires at least $minParams parameter(s), but $actualCount provided"
        }
        
        /** Specific allowed counts (e.g., 0 or 2, but not 1) */
        data class Specific(val allowedCounts: Set<Int>) : ParamInfo() {
            override fun isValidParamCount(count: Int) = count in allowedCounts
            override fun getErrorMessage(funcName: String, actualCount: Int): String {
                val countsStr = allowedCounts.sorted().joinToString(" or ")
                return "Function '$funcName' expects $countsStr parameter(s), but $actualCount provided"
            }
        }
    }
    
    /**
     * Returns parameter information for built-in functions
     */
    private fun getBuiltinFunctionParamInfo(funcName: String): ParamInfo? {
        return when (funcName) {
            // General functions
            "contains" -> ParamInfo.Fixed(2)
            "size" -> ParamInfo.Fixed(1)
            "error" -> ParamInfo.Fixed(1)
            "fallback" -> ParamInfo.Variadic(1)  // variadic
            "min", "max" -> ParamInfo.Fixed(2)
            
            // Numeric functions
            "is-number", "is-integer", "is-decimal" -> ParamInfo.Fixed(1)
            "number" -> ParamInfo.Range(1, 2)  // optional fallback
            "round", "floor", "ceiling" -> ParamInfo.Fixed(1)
            "random" -> ParamInfo.Fixed(0)
            "sum" -> ParamInfo.Fixed(1)
            "mod" -> ParamInfo.Fixed(2)
            "hash-int" -> ParamInfo.Fixed(1)
            
            // String functions
            "is-string" -> ParamInfo.Fixed(1)
            "string" -> ParamInfo.Fixed(1)
            "test" -> ParamInfo.Fixed(2)
            "capture" -> ParamInfo.Fixed(2)
            "split" -> ParamInfo.Fixed(2)
            "join" -> ParamInfo.Fixed(2)
            "lowercase", "uppercase" -> ParamInfo.Fixed(1)
            "sha256-hex" -> ParamInfo.Fixed(1)
            "starts-with", "ends-with" -> ParamInfo.Fixed(2)
            "from-json" -> ParamInfo.Range(1, 2)  // optional fallback
            "to-json" -> ParamInfo.Fixed(1)
            "replace" -> ParamInfo.Fixed(3)
            "trim" -> ParamInfo.Fixed(1)
            "uuid" -> ParamInfo.Specific(setOf(0, 2))  // 0 for random UUID, or 2 for UUID v1
            
            // Boolean functions
            "boolean" -> ParamInfo.Fixed(1)
            "not" -> ParamInfo.Fixed(1)
            "is-boolean" -> ParamInfo.Fixed(1)
            
            // Object functions
            "is-object" -> ParamInfo.Fixed(1)
            "get-key" -> ParamInfo.Range(2, 3)  // optional fallback
            
            // Array functions
            "array" -> ParamInfo.Fixed(1)
            "is-array" -> ParamInfo.Fixed(1)
            "flatten" -> ParamInfo.Fixed(1)
            "all", "any" -> ParamInfo.Fixed(1)
            "zip" -> ParamInfo.Fixed(2)
            "zip-with-index" -> ParamInfo.Fixed(1)
            "index-of" -> ParamInfo.Fixed(2)
            
            // Time functions
            "now" -> ParamInfo.Fixed(0)
            "parse-time" -> ParamInfo.Range(2, 3)  // optional fallback
            "format-time" -> ParamInfo.Range(2, 3)  // optional timezone
            
            // URL functions
            "parse-url" -> ParamInfo.Fixed(1)
            
            else -> null  // Unknown function, don't validate
        }
    }

    private fun isVariableUsed(variableDecl: JsltLetVariableDecl): Boolean {
        val varName = variableDecl.name ?: return true
        val file = variableDecl.containingFile
        
        var isUsed = false
        file.accept(object : PsiRecursiveElementWalkingVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is JsltVariableUsage && element.name == varName) {
                    val resolved = element.reference.resolve()
                    if (resolved == variableDecl) {
                        isUsed = true
                        stopWalking()
                    }
                }
                super.visitElement(element)
            }
        })
        
        return isUsed
    }

    private fun isFunctionUsed(functionDecl: JsltFunctionDeclNameDecl): Boolean {
        val funcName = functionDecl.name ?: return true
        val file = functionDecl.containingFile
        
        var isUsed = false
        file.accept(object : PsiRecursiveElementWalkingVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is JsltFunctionName && element.name == funcName) {
                    val resolved = element.reference.resolve()
                    if (resolved == functionDecl) {
                        isUsed = true
                        stopWalking()
                    }
                }
                super.visitElement(element)
            }
        })
        
        return isUsed
    }

    private fun isParameterUsed(paramDecl: JsltFunctionDeclParamDecl): Boolean {
        val paramName = paramDecl.name ?: return true
        val functionDecl = paramDecl.parent?.parent as? JsltFunctionDecl ?: return true
        
        var isUsed = false
        functionDecl.accept(object : PsiRecursiveElementWalkingVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is JsltVariableUsage && element.name == paramName) {
                    val resolved = element.reference.resolve()
                    if (resolved == paramDecl) {
                        isUsed = true
                        stopWalking()
                    }
                }
                super.visitElement(element)
            }
        })
        
        return isUsed
    }

    private fun isImportAliasUsed(importDecl: JsltImportDeclaration): Boolean {
        val aliasName = importDecl.name ?: return true
        val file = importDecl.containingFile
        
        var isUsed = false
        file.accept(object : PsiRecursiveElementWalkingVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is JsltFunctionName && element.importAlias == aliasName) {
                    isUsed = true
                    stopWalking()
                }
                super.visitElement(element)
            }
        })
        
        return isUsed
    }

}

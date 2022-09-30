package net.stefanfuchs.jslt.intellij.language

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDecl


object JsltUtil {
    /**
     * Searches the entire project for Simple language files with instances of the Simple property with the given key.
     *
     * @param project current project
     * @param functionName     to check
     * @return matching properties
     */
    fun findFunctionDeclarationByName(project: Project?, functionName: String): List<JsltFunctionDecl> {
        val result: MutableList<JsltFunctionDecl> = ArrayList<JsltFunctionDecl>()
        val virtualFiles = FileTypeIndex.getFiles(JsltFileType.INSTANCE, GlobalSearchScope.allScope(project!!))
        for (virtualFile in virtualFiles) {
            val simpleFile: JsltFile? = PsiManager.getInstance(project).findFile(virtualFile!!) as JsltFile?
            if (simpleFile != null) {
                val functionDeclarations: Array<JsltFunctionDecl>? = PsiTreeUtil.getChildrenOfType(
                    simpleFile,
                    JsltFunctionDecl::class.java
                )
                if (functionDeclarations != null) {
                    for (functionDecl in functionDeclarations) {
                        if (functionName == functionDecl.name) {
                            result.add(functionDecl)
                        }
                    }
                }
            }
        }
        return result
    }

//    fun findProperties(project: Project?): List<SimpleProperty> {
//        val result: List<SimpleProperty> = ArrayList<SimpleProperty>()
//        val virtualFiles = FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(
//            project!!))
//        for (virtualFile in virtualFiles) {
//            val simpleFile: SimpleFile? = PsiManager.getInstance(project).findFile(virtualFile!!) as SimpleFile?
//            if (simpleFile != null) {
//                val properties: Array<SimpleProperty> = PsiTreeUtil.getChildrenOfType(simpleFile,
//                    SimpleProperty::class.java)
//                if (properties != null) {
//                    Collections.addAll(result, properties)
//                }
//            }
//        }
//        return result
//    }
//
//    /**
//     * Attempts to collect any comment elements above the Simple key/value pair.
//     */
//    fun findDocumentationComment(property: SimpleProperty): String {
//        val result: MutableList<String> = LinkedList()
//        var element: PsiElement = property.getPrevSibling()
//        while (element is PsiComment || element is PsiWhiteSpace) {
//            if (element is PsiComment) {
//                val commentText = element.getText().replaceFirst("[!# ]+".toRegex(), "")
//                result.add(commentText)
//            }
//            element = element.prevSibling
//        }
//        return StringUtil.join(Lists.reverse(result), "\n ")
//    }
}

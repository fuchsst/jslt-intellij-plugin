package net.stefanfuchs.jslt.intellij.language.actions

import com.intellij.json.JsonFileType
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import net.stefanfuchs.jslt.intellij.language.JsltFile
import net.stefanfuchs.jslt.intellij.language.JsltFileType
import net.stefanfuchs.jslt.intellij.language.ui.JsltExecutionDialog

class JsltExecuteWithFileActionGroup : ActionGroup() {

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(e: AnActionEvent) {
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        val isJsltFile = psiFile is JsltFile
        val isJsonFile = psiFile?.fileType?.defaultExtension == "json"
        
        e.presentation.isEnabledAndVisible = isJsltFile || isJsonFile
    }

    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        if (e == null) return emptyArray()
        
        val project = e.project ?: return emptyArray()
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return emptyArray()
        
        val actions = mutableListOf<AnAction>()
        
        // Add "New Empty" option
        actions.add(object : AnAction("New Empty ${if (psiFile is JsltFile) "JSON" else "JSLT"}") {
            override fun getActionUpdateThread(): ActionUpdateThread {
                return ActionUpdateThread.BGT
            }
            
            override fun actionPerformed(e: AnActionEvent) {
                val dialog = if (psiFile is JsltFile) {
                    JsltExecutionDialog(project, jsltContent = psiFile.text)
                } else {
                    JsltExecutionDialog(project, jsonContent = psiFile.text)
                }
                dialog.show()
            }
        })
        
        actions.add(Separator.getInstance())
        
        // Add file list based on current file type
        if (psiFile is JsltFile) {
            // List JSON files
            actions.addAll(getJsonFileActions(project, psiFile.text))
        } else {
            // List JSLT files
            actions.addAll(getJsltFileActions(project, psiFile.text))
        }
        
        return actions.toTypedArray()
    }

    private fun getJsonFileActions(project: Project, jsltContent: String): List<AnAction> {
        val actions = mutableListOf<AnAction>()
        val virtualFiles = FileTypeIndex.getFiles(JsonFileType.INSTANCE, GlobalSearchScope.projectScope(project))
        
        val filesByDirectory = virtualFiles.groupBy { it.parent?.name ?: "" }
        
        filesByDirectory.entries.sortedBy { it.key }.forEach { (dirName, files) ->
            if (files.size > 1) {
                // Create submenu for directory
                actions.add(object : ActionGroup(dirName, true) {
                    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
                        return files.sortedBy { it.name }.map { file ->
                            createFileAction(project, file, jsltContent, null)
                        }.toTypedArray()
                    }
                })
            } else {
                // Add file directly
                files.forEach { file ->
                    actions.add(createFileAction(project, file, jsltContent, null))
                }
            }
        }
        
        return actions
    }

    private fun getJsltFileActions(project: Project, jsonContent: String): List<AnAction> {
        val actions = mutableListOf<AnAction>()
        val virtualFiles = FileTypeIndex.getFiles(JsltFileType.INSTANCE, GlobalSearchScope.projectScope(project))
        
        val filesByDirectory = virtualFiles.groupBy { it.parent?.name ?: "" }
        
        filesByDirectory.entries.sortedBy { it.key }.forEach { (dirName, files) ->
            if (files.size > 1) {
                // Create submenu for directory
                actions.add(object : ActionGroup(dirName, true) {
                    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
                        return files.sortedBy { it.name }.map { file ->
                            createFileAction(project, file, null, jsonContent)
                        }.toTypedArray()
                    }
                })
            } else {
                // Add file directly
                files.forEach { file ->
                    actions.add(createFileAction(project, file, null, jsonContent))
                }
            }
        }
        
        return actions
    }

    private fun createFileAction(
        project: Project,
        file: VirtualFile,
        jsltContent: String?,
        jsonContent: String?
    ): AnAction {
        return object : AnAction(file.name) {
            override fun getActionUpdateThread(): ActionUpdateThread {
                return ActionUpdateThread.BGT
            }
            
            override fun actionPerformed(e: AnActionEvent) {
                val psiFile = PsiManager.getInstance(project).findFile(file)
                val fileContent = psiFile?.text ?: ""
                
                val dialog = if (jsltContent != null) {
                    JsltExecutionDialog(project, jsltContent = jsltContent, jsonContent = fileContent)
                } else {
                    JsltExecutionDialog(project, jsltContent = fileContent, jsonContent = jsonContent ?: "")
                }
                dialog.show()
            }
        }
    }
}

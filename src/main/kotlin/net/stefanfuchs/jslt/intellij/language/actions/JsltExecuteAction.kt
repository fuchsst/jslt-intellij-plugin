package net.stefanfuchs.jslt.intellij.language.actions

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import net.stefanfuchs.jslt.intellij.language.JsltFile
import net.stefanfuchs.jslt.intellij.language.ui.JsltExecutionDialog

class JsltExecuteAction : AnAction() {
    
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(e: AnActionEvent) {
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        val isJsltFile = psiFile is JsltFile
        val isJsonFile = psiFile?.fileType?.defaultExtension == "json"
        
        e.presentation.isEnabledAndVisible = isJsltFile || isJsonFile
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return
        
        val dialog = if (psiFile is JsltFile) {
            JsltExecutionDialog(project, jsltContent = psiFile.text)
        } else {
            // Assume JSON file
            JsltExecutionDialog(project, jsonContent = psiFile.text)
        }
        
        dialog.show()
    }
}

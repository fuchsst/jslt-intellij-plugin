package net.stefanfuchs.jslt.intellij.language.ui

import com.intellij.json.JsonFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.LanguageTextField
import com.schibsted.spt.data.jslt.Parser
import com.fasterxml.jackson.databind.ObjectMapper
import net.stefanfuchs.jslt.intellij.language.JsltLanguage
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*

class JsltExecutionDialog(
    private val project: Project,
    private val jsltContent: String = "",
    private val jsonContent: String = ""
) : DialogWrapper(project) {

    private val jsltEditor: LanguageTextField
    private val jsonInputEditor: LanguageTextField
    private val jsonOutputEditor: LanguageTextField
    private val executeButton: JButton
    private val objectMapper = ObjectMapper()

    init {
        title = "JSLT Transformation Executor"
        
        jsltEditor = LanguageTextField(JsltLanguage, project, jsltContent, false)
        jsonInputEditor = LanguageTextField(JsonFileType.INSTANCE.language, project, jsonContent, false)
        jsonOutputEditor = LanguageTextField(JsonFileType.INSTANCE.language, project, "", false)
        jsonOutputEditor.isEnabled = false
        
        executeButton = JButton("Execute Transformation")
        executeButton.addActionListener { executeTransformation() }
        
        init()
    }

    override fun createCenterPanel(): JComponent {
        val mainPanel = JPanel(BorderLayout())
        mainPanel.preferredSize = Dimension(900, 600)
        
        // Create split pane for inputs and output
        val splitPane = JSplitPane(JSplitPane.HORIZONTAL_SPLIT)
        splitPane.resizeWeight = 0.5
        
        // Left panel - inputs
        val leftPanel = JPanel(BorderLayout())
        val inputSplitPane = JSplitPane(JSplitPane.VERTICAL_SPLIT)
        inputSplitPane.resizeWeight = 0.5
        
        // JSLT panel
        val jsltPanel = JPanel(BorderLayout())
        jsltPanel.add(JLabel("JSLT Transformation:"), BorderLayout.NORTH)
        jsltEditor.preferredSize = Dimension(400, 250)
        jsltPanel.add(JBScrollPane(jsltEditor), BorderLayout.CENTER)
        
        // JSON Input panel
        val jsonInputPanel = JPanel(BorderLayout())
        jsonInputPanel.add(JLabel("JSON Input:"), BorderLayout.NORTH)
        jsonInputEditor.preferredSize = Dimension(400, 250)
        jsonInputPanel.add(JBScrollPane(jsonInputEditor), BorderLayout.CENTER)
        
        inputSplitPane.topComponent = jsltPanel
        inputSplitPane.bottomComponent = jsonInputPanel
        
        leftPanel.add(inputSplitPane, BorderLayout.CENTER)
        leftPanel.add(executeButton, BorderLayout.SOUTH)
        
        // Right panel - output
        val rightPanel = JPanel(BorderLayout())
        rightPanel.add(JLabel("JSON Output:"), BorderLayout.NORTH)
        jsonOutputEditor.preferredSize = Dimension(400, 500)
        rightPanel.add(JBScrollPane(jsonOutputEditor), BorderLayout.CENTER)
        
        splitPane.leftComponent = leftPanel
        splitPane.rightComponent = rightPanel
        
        mainPanel.add(splitPane, BorderLayout.CENTER)
        
        return mainPanel
    }

    private fun executeTransformation() {
        try {
            val jsltText = jsltEditor.text
            val jsonInputText = jsonInputEditor.text
            
            if (jsltText.isBlank()) {
                showError("JSLT transformation cannot be empty")
                return
            }
            
            if (jsonInputText.isBlank()) {
                showError("JSON input cannot be empty")
                return
            }
            
            // Parse JSLT
            val jsltExpr = Parser.compileString(jsltText)
            
            // Parse JSON input
            val jsonInput = objectMapper.readTree(jsonInputText)
            
            // Execute transformation
            val result = jsltExpr.apply(jsonInput)
            
            // Format and display output
            val prettyOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result)
            jsonOutputEditor.text = prettyOutput
            
        } catch (e: Exception) {
            showError("Error during transformation: ${e.message}")
            jsonOutputEditor.text = """
                {
                  "error": "${e.javaClass.simpleName}",
                  "message": "${e.message?.replace("\"", "\\\"")}"
                }
            """.trimIndent()
        }
    }

    private fun showError(message: String) {
        JOptionPane.showMessageDialog(
            contentPane,
            message,
            "Transformation Error",
            JOptionPane.ERROR_MESSAGE
        )
    }

    override fun createActions() = arrayOf(okAction)
}

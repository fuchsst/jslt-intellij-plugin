package net.stefanfuchs.jslt.intellij.language;


import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class JsltSyntaxHighlighterFactory extends SyntaxHighlighterFactory {

  @NotNull
  @Override
  public JsltSyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
    return new JsltSyntaxHighlighter();
  }

}
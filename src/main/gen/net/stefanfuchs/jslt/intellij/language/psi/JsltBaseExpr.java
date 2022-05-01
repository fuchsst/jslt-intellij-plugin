// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsltBaseExpr extends PsiElement {

  @Nullable
  JsltArray getArray();

  @Nullable
  JsltChainable getChainable();

  @Nullable
  JsltIfStatement getIfStatement();

  @Nullable
  JsltObject getObject();

  @Nullable
  JsltObjectComprehension getObjectComprehension();

  @Nullable
  JsltParenthesis getParenthesis();

}

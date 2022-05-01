// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsltAdditiveExpr extends PsiElement {

  @NotNull
  List<JsltAdditiveOperator> getAdditiveOperatorList();

  @NotNull
  List<JsltMultiplicativeExpr> getMultiplicativeExprList();

}

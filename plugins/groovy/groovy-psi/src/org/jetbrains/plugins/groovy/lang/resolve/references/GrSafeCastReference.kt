// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.plugins.groovy.lang.resolve.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiType
import org.jetbrains.plugins.groovy.lang.psi.api.GroovyResolveResult
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrSafeCastExpression
import org.jetbrains.plugins.groovy.lang.psi.impl.statements.expressions.HardcodedGroovyMethodConstants.AS_TYPE
import org.jetbrains.plugins.groovy.lang.psi.impl.statements.expressions.TypesUtil.createJavaLangClassType
import org.jetbrains.plugins.groovy.lang.resolve.api.Arguments
import org.jetbrains.plugins.groovy.lang.resolve.api.GroovyMethodCallReferenceBase
import org.jetbrains.plugins.groovy.lang.resolve.api.LazyTypeArgument
import org.jetbrains.plugins.groovy.lang.resolve.impl.resolveImpl2

class GrSafeCastReference(element: GrSafeCastExpression) : GroovyMethodCallReferenceBase<GrSafeCastExpression>(element) {

  override fun getRangeInElement(): TextRange = element.operationToken.textRangeInParent

  override val receiver: PsiType? get() = element.operand.type

  override val methodName: String get() = AS_TYPE

  override val arguments: Arguments? get() = listOf(LazyTypeArgument { createJavaLangClassType(element.castTypeElement?.type, element) })

  override fun doResolve(incomplete: Boolean): Collection<GroovyResolveResult> = resolveImpl2(incomplete)
}

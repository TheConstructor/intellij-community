// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.siyeh.ig.fixes.logging;

import com.siyeh.InspectionGadgetsBundle;
import com.siyeh.ig.IGQuickFixesTestCase;
import com.siyeh.ig.logging.StringConcatenationArgumentToLogCallInspection;

public class StringConcatenationArgumentToLogCallFixTest extends IGQuickFixesTestCase {

  @Override
  public void setUp() throws Exception {
    super.setUp();
    myDefaultHint = InspectionGadgetsBundle.message("string.concatenation.argument.to.log.call.quickfix");
    myFixture.addClass("package org.slf4j; public interface Logger { void info(String format); }");
    myFixture.addClass("package org.slf4j; public class LoggerFactory { public static Logger getLogger(Class clazz) { return null; }}");
    myFixture.enableInspections(new StringConcatenationArgumentToLogCallInspection());
  }

  public void testUseOfConstant() { doTest(); }
  public void testCharLiteral() { doTest(); }

  @Override
  protected String getRelativePath() {
    return "logging/string_concatenation_argument_to_log_call";
  }
}

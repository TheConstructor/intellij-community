// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.ide.util;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * An utility class for running a task once in the history for project or application
 *
 * @author Konstantin Bulenkov
 */
public class RunOnceUtil {
  /**
   * Perform the task if it was not performed before for the project
   * @param project project
   * @param id unique id for the task
   * @param task task
   * @return <code>true</code> if task has been performed
   * <code>false</code> if task had already been performed before
   */
  public static boolean runOnceForProject(@NotNull Project project, @NotNull String id, @NotNull Runnable task) {
    return _runOnce(PropertiesComponent.getInstance(project), id, task);
  }

  /**
   * Perform the task if it was not performed before for the project
   * @param id unique id for the task
   * @param task task
   * @return <code>true</code> if task has been performed
   * <code>false</code> if task had already been performed before
   */
  public static boolean runOnceForApp(@NotNull String id, @NotNull Runnable task) {
    return _runOnce(PropertiesComponent.getInstance(), id, task);
  }

  private static boolean _runOnce(@NotNull PropertiesComponent storage, @NotNull String id, @NotNull Runnable activity) {
    String key = createKey(id);
    if (storage.isTrueValue(key)) {
      return false;
    }

    activity.run();
    storage.setValue(key, true);
    return true;
  }

  private static String createKey(@NotNull String id) {
    return "RunOnceActivity." + id;
  }
}

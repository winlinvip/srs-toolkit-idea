// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.components.panels.NonOpaquePanel
import com.intellij.util.ui.UIUtil

class SrsExplorerToolWindow(project: Project): SimpleToolWindowPanel(true, true), Disposable {
    init {
        background = UIUtil.getTreeBackground()
        setContent(NonOpaquePanel())
    }

    override fun dispose() {
    }

    companion object {
        fun getInstance(project: Project): SrsExplorerToolWindow {
            return ServiceManager.getService(project, SrsExplorerToolWindow::class.java)
        }
    }
}

// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.panels.NonOpaquePanel
import com.intellij.util.ui.UIUtil

import net.ossrs.ideasrs.MyBundle

class SrsExplorerFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        // Content for SRS explorer.
        var explorer = SrsExplorerToolWindow.getInstance(project)
        var content = toolWindow.contentManager.factory.createContent(explorer, null, false)
        toolWindow.contentManager.addContent(content)

        // The help icon on SRS explorer.
        toolWindow.helpId = SrsHelpIds.EXPLORER_WINDOW.id

        // Set the actions.
        toolWindow.setTitleActions(listOf(
            ActionManager.getInstance().getAction("srs.settings.refresh")
        ))
    }

    override fun init(toolWindow: ToolWindow) {
        toolWindow.stripeTitle = MyBundle.message("explorer.title")
    }
}

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

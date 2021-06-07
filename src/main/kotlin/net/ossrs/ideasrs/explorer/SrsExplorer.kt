// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.util.treeView.AbstractTreeStructureBase
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.Disposable
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.panels.NonOpaquePanel
import com.intellij.util.ui.UIUtil
import net.ossrs.ideasrs.SrsBundle

class SrsExplorerFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        // Content for SRS explorer.
        val explorer = SrsExplorerToolWindow.getInstance(project)
        val content = toolWindow.contentManager.factory.createContent(explorer, null, false)
        toolWindow.contentManager.addContent(content)

        // The help icon on SRS explorer.
        toolWindow.helpId = SrsHelpIds.EXPLORER_WINDOW.id

        // Set the actions.
        toolWindow.setTitleActions(
            listOf(
                ActionManager.getInstance().getAction("srs.settings.refresh")
            )
        )
    }

    override fun init(toolWindow: ToolWindow) {
        toolWindow.stripeTitle = SrsBundle.message("explorer.title")
    }
}

class SrsExplorerToolWindow(project: Project) : SimpleToolWindowPanel(true, true), Disposable {
    private val treeUIWrapper = NonOpaquePanel()
    private val treeModel = SrsExplorerTreeModel(project)

    init {
        background = UIUtil.getTreeBackground()
        setContent(treeUIWrapper)
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }

    companion object {
        fun getInstance(project: Project): SrsExplorerToolWindow {
            return ServiceManager.getService(project, SrsExplorerToolWindow::class.java)
        }
    }
}

class SrsExplorerTreeModel(project: Project) : AbstractTreeStructureBase(project) {
    override fun getProviders(): MutableList<TreeStructureProvider>? {
        TODO("Not yet implemented")
    }

    override fun getRootElement(): Any {
        TODO("Not yet implemented")
    }

    override fun commit() {
        TODO("Not yet implemented")
    }

    override fun hasSomethingToCommit(): Boolean {
        TODO("Not yet implemented")
    }
}

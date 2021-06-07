// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.ex.ActionManagerEx
import com.intellij.openapi.application.runInEdt
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.PopupHandler
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.components.panels.NonOpaquePanel
import com.intellij.ui.tree.AsyncTreeModel
import com.intellij.ui.tree.StructureTreeModel
import com.intellij.ui.treeStructure.Tree
import com.intellij.util.ui.UIUtil
import net.ossrs.ideasrs.SrsBundle
import java.awt.Component
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeModel

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
    private val actionManager = ActionManagerEx.getInstanceEx()
    private val treePanel = NonOpaquePanel()
    private val treeModel = SrsExplorerTreeModel(project)

    private val structureTreeModel = StructureTreeModel(treeModel, this)
    private val srsTree = createTree(AsyncTreeModel(structureTreeModel, true, this))
    private val srsTreePanel = ScrollPaneFactory.createScrollPane(srsTree)

    init {
        background = UIUtil.getTreeBackground()
        setContent(treePanel)

        runInEdt {
            treePanel.setContent(srsTreePanel)
        }
    }

    private fun createTree(model: TreeModel): Tree {
        val tree = Tree(model)
        tree.isRootVisible = false
        tree.autoscrolls = true
        tree.cellRenderer = SrsTreeCellRenderer()

        tree.addMouseListener(
            object : PopupHandler() {
                override fun invokePopup(comp: Component?, x: Int, y: Int) {
                    val explorerNode = getSelectedNodesSameType<SrsExplorerNode<*>>()?.get(0) ?: return
                    val actionGroupName = (explorerNode as? SrsResourceActionNode)?.actionGroupName()

                    val totalActions = mutableListOf<AnAction>()

                    (actionGroupName?.let { actionManager.getAction(it) } as? ActionGroup)?.let { totalActions.addAll(it.getChildren(null)) }

                    val actionGroup = DefaultActionGroup(totalActions)
                    if (actionGroup.childrenCount > 0) {
                        val popupMenu = actionManager.createActionPopupMenu(explorerToolWindowPlace, actionGroup)
                        popupMenu.component.show(comp, x, y)
                    }
                }
            }
        )

        return tree
    }

    private inline fun <reified T : SrsExplorerNode<*>> getSelectedNodesSameType(): List<T>? {
        val selectedNodes = getSelectedNodes<T>()
        if (selectedNodes.isEmpty()) {
            return null
        }

        val firstClass = selectedNodes[0]::class.java
        return if (selectedNodes.all { firstClass.isInstance(it) }) {
            selectedNodes
        } else {
            null
        }
    }

    private inline fun <reified T : SrsExplorerNode<*>> getSelectedNodes() = srsTree.selectionPaths?.let {
        it.map { it.lastPathComponent }
            .filterIsInstance<DefaultMutableTreeNode>()
            .map { it.userObject }
            .filterIsInstance<T>()
            .toList()
    } ?: emptyList<T>()

    override fun dispose() {
    }

    companion object {
        fun getInstance(project: Project): SrsExplorerToolWindow {
            return ServiceManager.getService(project, SrsExplorerToolWindow::class.java)
        }
        const val explorerToolWindowPlace = "ExplorerToolWindow"
    }
}

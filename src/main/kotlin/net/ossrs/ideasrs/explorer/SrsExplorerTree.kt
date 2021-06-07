// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.ide.util.treeView.AbstractTreeStructureBase
import com.intellij.ide.util.treeView.NodeDescriptor
import com.intellij.ide.util.treeView.NodeRenderer
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.project.Project
import com.intellij.ui.SimpleTextAttributes
import javax.swing.Icon
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode

class SrsExplorerTreeModel(project: Project) : AbstractTreeStructureBase(project) {
    override fun getProviders(): List<TreeStructureProvider>? {
        return listOf(SrsTreeStructureProvider())
    }

    override fun getRootElement() = SrsExplorerRootNode(myProject)

    override fun commit() {
    }

    override fun hasSomethingToCommit(): Boolean = false
}

class SrsTreeStructureProvider : TreeStructureProvider {
    override fun modify(
        parent: AbstractTreeNode<*>,
        children: MutableCollection<AbstractTreeNode<*>>,
        settings: ViewSettings?
    ): MutableCollection<AbstractTreeNode<*>> {
        return children.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.toString() }).toMutableList()
    }
}

class SrsTreeCellRenderer : NodeRenderer() {
    override fun customizeCellRenderer(
        tree: JTree,
        value: Any?,
        selected: Boolean,
        expanded: Boolean,
        leaf: Boolean,
        row: Int,
        hasFocus: Boolean
    ) {
        super.customizeCellRenderer(tree, value, selected, expanded, leaf, row, hasFocus)
        if (value is DefaultMutableTreeNode && value.userObject is NodeDescriptor<*>) {
            icon = (value.userObject as NodeDescriptor<*>).icon
        }
    }
}

class SrsExplorerRootNode(private val nodeProject: Project) : AbstractTreeNode<Any>(nodeProject, Object()) {
    override fun getChildren(): List<SrsExplorerNode<*>> {
        val endpoints = ExtensionPointName<SrsExplorerService>("net.ossrs.ideasrs.explorer.services")
        val nodes = endpoints.extensionList.map { it.buildNode(nodeProject) }
        return nodes
    }

    override fun update(presentation: PresentationData) {
    }
}

abstract class SrsExplorerNode<T : Any>(val nodeProject: Project, value: T, private val nodeIcon: Icon?) :
    AbstractTreeNode<T>(nodeProject, value) {

    override fun update(presentation: PresentationData) {
        presentation.let {
            it.setIcon(nodeIcon)
            it.addText(displayName(), SimpleTextAttributes.REGULAR_ATTRIBUTES)
            statusText()?.let { status ->
                it.addText(" [$status]", SimpleTextAttributes.GRAY_ATTRIBUTES)
            }
        }
    }

    open fun displayName() = value.toString()

    open fun statusText(): String? = null

    open fun onDoubleClick() {}

    override fun toString(): String = displayName()
}

interface SrsResourceActionNode {
    fun actionGroupName(): String
}

// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.ide.util.treeView.AbstractTreeStructureBase
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.project.Project
import com.intellij.ui.SimpleTextAttributes
import net.ossrs.ideasrs.SrsBundle
import javax.swing.Icon

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

class SrsExplorerNodeSRS(project: Project, service: SrsExplorerServiceSRS) :
    SrsExplorerNode<SrsExplorerService>(project, service, null) {
    override fun displayName(): String = SrsBundle.message("explorer.node.srs")
    override fun isAlwaysShowPlus(): Boolean = true
    override fun getChildren(): List<SrsExplorerNode<*>> {
        return emptyList()
    }
}

class SrsExplorerNodeFFmpeg(project: Project, service: SrsExplorerServiceFFmpeg) :
    SrsExplorerNode<SrsExplorerService>(project, service, null) {
    override fun displayName(): String = SrsBundle.message("explorer.node.ffmpeg")
    override fun isAlwaysShowPlus(): Boolean = true
    override fun getChildren(): List<SrsExplorerNode<*>> {
        return emptyList()
    }
}

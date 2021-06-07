// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.openapi.project.Project
import net.ossrs.ideasrs.SrsBundle

interface SrsExplorerService {
    val serviceId: String
    fun buildNode(project: Project): SrsExplorerNode<*>
}

class SrsExplorerServiceSRS : SrsExplorerService {
    override val serviceId: String = "srs"
    override fun buildNode(project: Project) = SrsExplorerNodeSRS(project, this)
}

class SrsExplorerNodeSRS(project: Project, service: SrsExplorerServiceSRS) :
    SrsExplorerNode<SrsExplorerService>(project, service, null), SrsResourceActionNode {
    override fun displayName(): String = SrsBundle.message("explorer.node.srs")
    override fun isAlwaysShowPlus(): Boolean = true
    override fun getChildren(): List<SrsExplorerNode<*>> {
        return emptyList()
    }
    override fun actionGroupName() = "net.ossrs.ideasrs.explorer.srs"
}

class SrsExplorerServiceFFmpeg : SrsExplorerService {
    override val serviceId: String = "ffmpeg"
    override fun buildNode(project: Project) = SrsExplorerNodeFFmpeg(project, this)
}

class SrsExplorerNodeFFmpeg(project: Project, service: SrsExplorerServiceFFmpeg) :
    SrsExplorerNode<SrsExplorerService>(project, service, null) {
    override fun displayName(): String = SrsBundle.message("explorer.node.ffmpeg")
    override fun isAlwaysShowPlus(): Boolean = true
    override fun getChildren(): List<SrsExplorerNode<*>> {
        return emptyList()
    }
}

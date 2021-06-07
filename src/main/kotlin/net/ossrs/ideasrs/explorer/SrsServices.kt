// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.openapi.project.Project

interface SrsExplorerService {
    val serviceId: String
    fun buildNode(project: Project): SrsExplorerNode<*>
}

class SrsExplorerServiceSRS : SrsExplorerService {
    override val serviceId: String = "srs"
    override fun buildNode(project: Project) = SrsExplorerNodeSRS(project, this)
}

class SrsExplorerServiceFFmpeg : SrsExplorerService {
    override val serviceId: String = "ffmpeg"
    override fun buildNode(project: Project) = SrsExplorerNodeFFmpeg(project, this)
}

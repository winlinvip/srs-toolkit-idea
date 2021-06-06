// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.services

import com.intellij.openapi.project.Project
import net.ossrs.ideasrs.SrsBundle

class MyProjectService(project: Project) {

    init {
        println(SrsBundle.message("projectService", project.name))
    }
}

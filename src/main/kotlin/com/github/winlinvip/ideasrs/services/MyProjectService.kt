package com.github.winlinvip.ideasrs.services

import com.github.winlinvip.ideasrs.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

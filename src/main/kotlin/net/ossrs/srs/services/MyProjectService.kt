package net.ossrs.srs.services

import com.intellij.openapi.project.Project
import net.ossrs.srs.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

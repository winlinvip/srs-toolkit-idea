package net.ossrs.ideasrs.services

import com.intellij.openapi.project.Project
import net.ossrs.ideasrs.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

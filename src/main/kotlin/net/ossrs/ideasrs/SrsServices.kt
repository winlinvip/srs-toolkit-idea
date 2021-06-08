// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications.Bus.notify
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

interface SrsExplorerService {
    val serviceId: String
    fun buildNode(project: Project): SrsExplorerNode<*>
}

class SrsExplorerServiceSRS : SrsExplorerService {
    override val serviceId: String = "srs"
    override fun buildNode(project: Project) = SrsExplorerNodeSRS(project, this)
}

class SrsExplorerNodeSRS(project: Project, service: SrsExplorerServiceSRS) :
    SrsExplorerNode<SrsExplorerService>(project, service, null), SrsExplorerActionNode {
    override fun displayName(): String = SrsBundle.message("explorer.node.srs")
    override fun isAlwaysShowPlus(): Boolean = true
    override fun getChildren(): List<SrsExplorerNode<*>> {
        return emptyList()
    }
    override fun actionGroupName() = "net.ossrs.ideasrs.srs"
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

@State(name = "server", storages = [Storage("srs.xml")])
class SrsServerSettingsService : PersistentStateComponent<SrsServerSettingsService.State> {
    class State {
        var home: String? = null
    }
    private var myState = State()

    override fun getState() = myState

    override fun loadState(state: State) {
        myState = state
    }

    companion object {
        fun getInstance(project: Project): SrsServerSettingsService {
            return ServiceManager.getService(project, SrsServerSettingsService::class.java)
        }
    }
}

class SrsBuildServerTask(project: Project, val home: String) :
    Task.Backgroundable(project, SrsBundle.message("srs.build.task", home), true) {

    override fun run(indicator: ProgressIndicator) = runBlocking {
        indicator.fraction = 0.0
        while (indicator.fraction < 1.0) {
            indicator.checkCanceled()
            indicator.fraction += 0.2
            delay(1000)
        }

        notify(
            Notification(
                NOTIFICATION_GROUP_ID,
                SrsBundle.message("srs.build.title"),
                SrsBundle.message("srs.build.done", home),
                NotificationType.INFORMATION
            ),
            project
        )
    }
}

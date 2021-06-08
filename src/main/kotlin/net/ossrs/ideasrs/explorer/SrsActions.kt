// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import net.ossrs.ideasrs.SrsBundle
import java.awt.Component
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

class SrsRefreshAction(text: String = SrsBundle.message("settings.refresh.description")) :
    AnAction(text, null, AllIcons.Actions.Refresh) {
    override fun actionPerformed(e: AnActionEvent) {
        TODO("Not yet implemented")
    }
}

// For icon, see https://jetbrains.design/intellij/resources/icons_list/
class SrsCreateServerAction :
    DumbAwareAction(SrsBundle.message("srs.create.server.title"), null, AllIcons.General.Add) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.getRequiredData(LangDataKeys.PROJECT)
        val dialog = SrsCreateServerActionDialog(project)
        dialog.show()
    }
}

class SrsCreateServerActionDialog(
    private val project: Project,
    parent: Component? = null
) : DialogWrapper(project, parent, false, IdeModalityType.PROJECT) {
    val view = SrsCreateServerActionPanel()
    val settings = SrsServerSettingsService.getInstance(project)

    init {
        title = SrsBundle.message("srs.create.server.title")
        setOKButtonText(SrsBundle.message("srs.create.server.create"))
        view.serverName.text = settings.state.home

        init()
    }

    override fun createCenterPanel(): JComponent? = view.component

    override fun getPreferredFocusedComponent(): JComponent? = view.serverName

    override fun doOKAction() {
        settings.state.home = view.serverName.text
        super.doOKAction()
    }
}

class SrsCreateServerActionPanel {
    lateinit var serverName: JTextField
        private set
    lateinit var component: JPanel
        private set
}

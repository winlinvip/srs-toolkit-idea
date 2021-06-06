// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs.explorer

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

import net.ossrs.ideasrs.MyBundle

class SrsRefreshAction(text: String = MyBundle.message("settings.refresh.description")) :
    AnAction(text, null, AllIcons.Actions.Refresh) {
    override fun actionPerformed(e: AnActionEvent) {
        TODO("Not yet implemented")
    }
}

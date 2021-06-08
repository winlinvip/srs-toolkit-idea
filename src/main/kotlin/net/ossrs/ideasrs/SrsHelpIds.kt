// Copyright (c) 2021 Winlin
// SPDX-License-Identifier: MIT

package net.ossrs.ideasrs

import com.intellij.openapi.help.WebHelpProvider

enum class SrsHelpIds(shortId: String, val url: String) {
    EXPLORER_WINDOW(
        "explorerWindow",
        "https://github.com/ossrs/srs-toolkit-idea/wiki"
    ),

    SRS(
        "srs",
        "https://github.com/ossrs/srs/wiki"
    );

    val id = "net.ossrs.ideasrs.$shortId"
}

class SrsHelpIdTranslator : WebHelpProvider() {
    override fun getHelpPageUrl(helpTopicId: String): String? {
        return SrsHelpIds.values().asSequence().map { it.id to it.url }.toMap().getOrElse(helpTopicId) {
            "https://github.com/ossrs/srs-toolkit-idea/wiki"
        }
    }
}

// For all notifications or balloons.
const val NOTIFICATION_GROUP_ID = "SRS Toolkit"

# idea-srs

![Build](https://github.com/ossrs/idea-srs/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/16837.svg)](https://plugins.jetbrains.com/plugin/16837)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/16837.svg)](https://plugins.jetbrains.com/plugin/16837)

<!-- Plugin description -->

Supports the development of [SRS](https://github.com/ossrs/srs) server applications 
with IntelliJ IDEA. 

* Download the source code of SRS, or docker images.
* Build SRS from source code, for macOS and Linux only.

> Note: [SRS](https://github.com/ossrs/srs) is a simple, high efficiency
and realtime video server, supports RTMP/WebRTC/HLS/HTTP-FLV/SRT/GB28181.

<!-- Plugin description end -->

## Template ToDo list

- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Verify the [pluginGroup](/gradle.properties), [plugin ID](/src/main/resources/META-INF/plugin.xml) and [sources package](/src/main/kotlin).
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the Plugin ID in the above README badges.
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "idea-srs"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/ossrs/idea-srs/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

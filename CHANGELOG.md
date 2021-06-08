<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# srs-toolkit-idea Changelog

## [Unreleased]
## [0.0.17] - 2021-06-08
### Added
- Background task and notifications.

## [0.0.16] - 2021-06-08
### Removed
- Eliminate the unused listeners and services.
### Added
- Add persistence for SRS server settings.
- Add create SRS server dialog.

## [0.0.15] - 2021-06-07
### Added
- Explorer service and node.
### Changed
- Refine file names.
- Disable some detekt warnings.

## [0.0.14] - 2021-06-06
### Changed
- Rename MyBundle to SrsBundle.
- Extract SrsExplorerToolWindow to file.
### Added
- Add configuration 'Run Plugin(zh)' for localization.
- Add SrsBundle_zh.properties for localization.
- Create tree view for explorer.
- Add refresh SRS connection.

## [0.0.13] - 2021-06-06
### Changed
- Refine LICENSE with http://spdx.org/licenses/
### Added
- Add SrsExplorer tool window.
- Add WebHelpProvider for explorer window.

## [0.0.11] - 2021-05-25
### Fixed
- Change the state to develop, not released.
### Changed
- Change build to util 211.
- Change vendor to OSSRS.

## [0.0.9] - 2021-05-23
### Added
- Add date to release header.
- Add CHANGELOG.
- Change the name of plugin.xml to SRS server.
- Initial scaffold created from [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template)
### Changed
- Change logo to SRS.
- Change the pluginName to srs-toolkit-idea
- Change the search name from "srs" to "srs server".
- Change pluginName to idea-srs
- Keep pluginGroup to net.ossrs.ideasrs

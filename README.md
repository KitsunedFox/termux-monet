<p align="center">
  <img src="/art/logo.png?raw=true">
</p>
<div align="center">
  <a href="https://github.com/HardcodedCat/termux-monet/actions/workflows/debug_build.yml?query=branch%3Amaster">
    <img src="https://img.shields.io/github/actions/workflow/status/HardcodedCat/termux-monet/debug_build.yml?branch=master&label=Build&logo=gradle"/>
  </a>
  <a href="https://github.com/HardcodedCat/termux-monet/actions/workflows/run_tests.yml?query=branch%3Amaster">
    <img src="https://img.shields.io/github/actions/workflow/status/HardcodedCat/termux-monet/run_tests.yml?branch=master&label=Test&logo=gradle"/>
  </a>
</div>
<br />
<div align="center">
  <a href="https://www.android.com/versions/oreo-8-0/">
    <img src="https://img.shields.io/static/v1?label=Android&message=8%2B&color=brightgreen&style=flat&logo=android&logoColor=white"/>
  </a>
  <a href="https://github.com/HardcodedCat/termux-monet/commits/master">
    <img src="https://img.shields.io/github/last-commit/HardcodedCat/termux-monet/master?color=informational&label=Updated&logo=github"/>
  </a>
  <a href="https://github.com/HardcodedCat/termux-monet/releases">
    <img src="https://badgen.net/github/tag/HardcodedCat/termux-monet?color=orange&icon=terminal&label=Latest"/>
  </a>
  <a href="/LICENSE">
    <img src="https://img.shields.io/badge/License-GPLv3_only-informational.svg?logo=gnu&label=License"/>
  </a>
</div>
<br />

<p align="center">
  <img src="/art/screenshot.png?raw=true" width=50% height=50%>
</p>

***
## MONET IS ONLY AVAILABLE ON `ANDROID 12+`!
#### LOWER VERSIONS WILL DISPLAY A STATIC, BLACK AND WHITE MONET THEME
***

[Termux-Monet](https://github.com/HardcodedCat/termux-monet) is a unofficial, modified fork of [Termux](https://github.com/termux/termux-app), an Android terminal application and Linux environment, with Monet Theming Implementations.

***
## Contents
- [Phantom Process Killer](#Phantom-Process-Killer)
- [Nightly Releases](#Nightly-Builds)
- [Wikis](#Wikis)
- [Miscellaneous](#Miscellaneous)
- [True italic fonts](#True-italic-fonts)
- [Displaying images in Termux](#Displaying-images-in-Termux)
- [Running termux from ADB](#Running-termux-from-ADB)
- [Debugging](#Debugging)
- [Disclaimer](#Disclaimer)
- [Forking Instructions](#Forking)
- [Special Thanks](#Special-Thanks)
***

## Termux Monet and Plugins

> The [Termux Monet](https://github.com/HardcodedCat/termux-monet) fork app comes with the following optional modified plugin apps:
>
> **These are Modified Plugins. All these repos are Forks from termux-app**
> - [Termux:Styling](https://github.com/HardcodedCat/termux-styling)
> - [Termux:API](https://github.com/HardcodedCat/termux-api)
> - [Termux:Boot](https://github.com/HardcodedCat/termux-boot)
> - [Termux:Float](https://github.com/HardcodedCat/termux-float)
> - [Termux:Tasker](https://github.com/HardcodedCat/termux-tasker)
> - [Termux:Widget](https://github.com/HardcodedCat/termux-widget)

***

## Phantom Process Killer

**NOTICE:**
> **Termux is broken on Android 12+.** Android OS will kill any (phantom) processes greater than 32 (limit is for all apps combined) and also kill any processes using excessive CPU. You may get `[Process completed (signal 9) - press Enter]` message in the terminal without actually exiting the shell process yourself. Check the related issue [#2366](https://github.com/termux/termux-app/issues/2366), [issue tracker](https://issuetracker.google.com/u/1/issues/205156966), [gist with details](https://gist.github.com/agnostic-apollo/dc7e47991c512755ff26bd2d31e72ca8) and [this TLDR comment](https://github.com/termux/termux-app/issues/2366#issuecomment-1009269410) on how to disable trimming of phantom processes.

#### Deactivation Instructions (ADB):
- On an ADB console, paste the following commands on the following order:
```
adb shell "/system/bin/device_config set_sync_disabled_for_tests persistent"
```
```
adb shell "/system/bin/device_config put activity_manager max_phantom_processes 2147483647"
```
```
adb shell settings put global settings_enable_monitor_phantom_procs false
```

#### Deactivation Instructions (ROOT):
- On Termux (or any Terminal Emulator), paste the following commands on the following order:
```
su -c /system/bin/device_config set_sync_disabled_for_tests persistent
```
```
su -c /system/bin/device_config put activity_manager max_phantom_processes 2147483647
```
```
su -c setprop persist.sys.fflag.override.settings_enable_monitor_phantom_procs false
```

#### Experimental Method (MAGISK)

- On a Rooted phone with Magisk installed, flash the following module:

    > [![](https://img.shields.io/static/v1?message=LetTheGhostsOut.zip&logo=magisk&labelColor=5c5c5c&color=00af9c&logoColor=white&label=%20&style=for-the-badge)](https://raw.githubusercontent.com/HardcodedCat/termux-monet/master/ppr/PhantomProcessRetainer-main.zip)

- After that, `PhantomProcessKiller might be deactivated on every device boot.` Please make an [issue](https://github.com/HardcodedCat/termux-monet/issues) on this repo if the module didn't work for you.

#### Check if PhantomProcessKiller was Disabled (ROOT):
```
su -c /system/bin/dumpsys activity settings | grep max_phantom_processes
```
```
su -c /system/bin/device_config get activity_manager max_phantom_processes
```
- Both commands above should return `2147483647`

```
su -c getprop persist.sys.fflag.override.settings_enable_monitor_phantom_procs
```
- It should return "false"

***

## Nightly Builds

> Termux-Monet application can be obtained on **GitHub** either from [`GitHub Releases`](https://github.com/HardcodedCat/termux-monet/releases) for **stablier releases** or from [`GitHub Actions`](https://github.com/HardcodedCat/termux-monet/actions/workflows/debug_build.yml) for the latest **unstable releases**.

#### Releases (Stablier)
 - The APKs for `GitHub Releases` will be listed under `Assets` drop-down of a release. These are automatically attached when a new version is released.

#### Nightly (Unstable)
 - The APKs for `GitHub Actions` will be listed under `Artifacts` section of a workflow run. These are created for each commit/push done to the repository and can be used by users who don't want to wait for releases and want to try out the latest features immediately. Note that for seeing action workflows, you **need** to be [**logged into a `GitHub` account**](https://github.com/login) for the `Artifacts` links to be enabled/clickable. If you are using the [`GitHub` app](https://github.com/mobile), then make sure to open workflow link in a browser like Chrome or Firefox that has your GitHub account logged in since the in-app browser may not be logged in. 

**Security warning**: APK files on GitHub are signed with a test key that has been [shared with community](https://github.com/termux/termux-app/blob/master/app/testkey_untrusted.jks). This IS NOT an official developer key and everyone can use it to generate releases for own testing. Be very careful when using Termux GitHub builds obtained elsewhere except https://github.com/termux/termux-app. Everyone is able to use it to forge a malicious Termux update installable over the GitHub build. Think twice about installing Termux builds distributed via Telegram or other social media. If your device get caught by malware, we will not be able to help you.

The [test key](https://github.com/termux/termux-app/blob/master/app/testkey_untrusted.jks) shall not be used to impersonate @termux and can't be used for this anyway. This key is not trusted by us and it is quite easy to detect its use in user generated content.

Keystore information:
```
Alias name: alias
Creation date: Oct 4, 2019
Entry type: PrivateKeyEntry
Certificate chain length: 1
Certificate[1]:
Owner: CN=APK Signer, OU=Earth, O=Earth
Issuer: CN=APK Signer, OU=Earth, O=Earth
Serial number: 29be297b
Valid from: Wed Sep 04 02:03:24 EEST 2019 until: Tue Oct 26 02:03:24 EEST 2049
Certificate fingerprints:
         SHA1: 51:79:55:EA:BF:69:FC:05:7C:41:C7:D3:79:DB:BC:EF:20:AD:85:F2
         SHA256: B6:DA:01:48:0E:EF:D5:FB:F2:CD:37:71:B8:D1:02:1E:C7:91:30:4B:DD:6C:4B:F4:1D:3F:AA:BA:D4:8E:E5:E1
Signature algorithm name: SHA1withRSA (disabled)
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3
```

***

## Wikis

- [Termux Wiki](https://wiki.termux.com/wiki/)
- [Termux App Wiki](https://github.com/termux/termux-app/wiki)
- [Termux Packages Wiki](https://github.com/termux/termux-packages/wiki)

***

## True italic fonts
> As described in https://github.com/termux/termux-app/pull/2829, true italic fonts support can be enabled when placing an italic font in `~/.termux/font-italic.ttf`

***

## Displaying images in Termux

> As described in https://github.com/termux/termux-app/pull/2973, Termux can display images and gifs using sixel and iterm2 protocols.

> For displaying images and gifs using `Sixel`, do `pkg install libsixel` and use `img2sixel image.png`

> For displaying images using `iTerm2`, download the [imgcat](https://iterm2.com/utilities/imgcat) script, and use it with the command `./imgcat image.png`

***

## Dynamic Motd

> As described in [motds: add static motd back and use it as default instead of dynamic motd and add support for custom motd](https://github.com/termux/termux-tools/commit/6a0f5cc138c2c87fef91807fe0244e3531821d4c), in https://github.com/termux/termux-tools/pull/8, and in https://github.com/termux/termux-packages/pull/11250, Termux can now display both standard and dynamic motds.

> For using your own custom dynamic motd, place a `motd.sh` executable file at `~/.termux/`.

> To use the dynamic motd provided by the latest termux-tools package, run `ln -sf $PREFIX/etc/motd.sh ~/.termux/motd.sh`.

***

## Wallpaper support
> As described in https://github.com/termux/termux-app/pull/3079, Wallpapers can be defined either by placing `background_landscape.jpeg` and `background.jpeg` on `.termux` folder or by selecting the option `Set background image` inside the terminal context menu, under "Appearance".
> Background image loading can also be enabled/disabled from settings. From Termux Settings -> Termux -> Termux Style -> Background Image.

> An wallpaper overlay color can be defined through `termux.properties`, by using the key `background-overlay-color=#COLOR`. Supported color formats are `#AARRGGBB` and `#RRGGBB`

***

## Running termux from ADB
> As described in https://github.com/termux/termux-app/pull/1969, it is possible to access Termux directrly from adb shell via run-as. see the following example:

```% adb shell
dreamlte:/ $ run-as com.termux
dreamlte:/data/data/com.termux $ PATH=/data/data/com.termux/files/usr/bin LD_PRELOAD=/data/data/com.termux/files/usr/lib/libtermux-exec.so /data/data/com.termux/files/usr/bin/bash -l
~ $ python
Python 3.9.2 (default, Feb 22 2021, 12:26:04)
[Clang 9.0.8 (https://android.googlesource.com/toolchain/llvm-project 98c855489 on linux
Type "help", "copyright", "credits" or "license" for more information.
>>>
```

***

## Miscellaneous
- [FAQ](https://wiki.termux.com/wiki/FAQ)
- [Termux File System Layout](https://github.com/termux/termux-packages/wiki/Termux-file-system-layout)
- [Differences From Linux](https://wiki.termux.com/wiki/Differences_from_Linux)
- [Package Management](https://wiki.termux.com/wiki/Package_Management)
- [Remote Access](https://wiki.termux.com/wiki/Remote_Access)
- [Backing up Termux](https://wiki.termux.com/wiki/Backing_up_Termux)
- [Terminal Settings](https://wiki.termux.com/wiki/Terminal_Settings)
- [Touch Keyboard](https://wiki.termux.com/wiki/Touch_Keyboard)
- [Android Storage and Sharing Data with Other Apps](https://wiki.termux.com/wiki/Internal_and_external_storage)
- [Android APIs](https://wiki.termux.com/wiki/Termux:API)
- [Moved Termux Packages Hosting From Bintray to IPFS](https://github.com/termux/termux-packages/issues/6348)
- [Running Commands in Termux From Other Apps via `RUN_COMMAND` intent](https://github.com/termux/termux-app/wiki/RUN_COMMAND-Intent)
- [Termux and Android 10](https://github.com/termux/termux-packages/wiki/Termux-and-Android-10)

***

## Debugging

You can help debug problems of the `Termux` app and its plugins by setting appropriate `logcat` `Log Level` in `Termux` app settings -> `<APP_NAME>` -> `Debugging` -> `Log Level` (Requires `Termux` app version `>= 0.118.0`). The `Log Level` defaults to `Normal` and log level `Verbose` currently logs additional information. Its best to revert log level to `Normal` after you have finished debugging since private data may otherwise be passed to `logcat` during normal operation and moreover, additional logging increases execution time.

The plugin apps **do not execute the commands themselves** but send execution intents to `Termux` app, which has its own log level which can be set in `Termux` app settings -> `Termux` -> `Debugging` -> `Log Level`. So you must set log level for both `Termux` and the respective plugin app settings to get all the info.

Once log levels have been set, you can run the `logcat` command in `Termux` app terminal to view the logs in realtime (`Ctrl+c` to stop) or use `logcat -d > logcat.txt` to take a dump of the log. You can also view the logs from a PC over `ADB`. For more information, check official android `logcat` guide [here](https://developer.android.com/studio/command-line/logcat).

Moreover, users can generate termux files `stat` info and `logcat` dump automatically too with terminal's long hold options menu `More` -> `Report Issue` option and selecting `YES` in the prompt shown to add debug info. This can be helpful for reporting and debugging other issues. If the report generated is too large, then `Save To File` option in context menu (3 dots on top right) of `ReportActivity` can be used and the file viewed/shared instead.

Users must post complete report (optionally without sensitive info) when reporting issues. Issues opened with **(partial) screenshots of error reports** instead of text will likely be automatically closed/deleted.

##### Log Levels

- `Off` - Log nothing.
- `Normal` - Start logging error, warn and info messages and stacktraces.
- `Debug` - Start logging debug messages.
- `Verbose` - Start logging verbose messages.
##

***

## Disclaimer

> Do not report issues occurred in Termux Monet on termux-app repo, unless you're completely certain that this problem is also present in the regular Termux App.
>
> By installing this app, you agree that you're aware of its unstableness.
> 
> I am not responsible for bricked devices, dead SD cards, thermonuclear war, or you getting fired because Termux didn't load. Please do some research if you have any concerns about features and code included in this forked App before installing it! YOU are choosing to replace your Termux app with this one, and if you point the finger at me for messing up your terminal and device, I will laugh at you.
>
> Usage of Termux for attacking/hacking targets without prior mutual consent is illegal. It is the end user's responsibility to obey all applicable local, state and federal laws. Developers assume no liability and are not responsible for any misuse or damage caused by this program.

***

## Forking

- Check [`TermuxConstants`](https://github.com/HardcodedCat/termux-monet/blob/master/termux-shared/src/main/java/com/termux/shared/termux/TermuxConstants.java) javadocs for instructions on what changes to make in the app to change package name.
- Keep targetSdk 28 to avoid login error `not exec("/data/data/com.termux/files/usr/bin/login"): Permission denied`.
- You also need to recompile bootstrap zip for the new package name. Check [building bootstrap](https://github.com/termux/termux-packages/wiki/For-maintainers#build-bootstrap-archives), [here](https://github.com/termux/termux-app/issues/1983) and [here](https://github.com/termux/termux-app/issues/2081#issuecomment-865280111).
- Currently, not all plugins use `TermuxConstants` from `termux-shared` library and have hardcoded `com.termux` values and will need to be manually patched.
- If forking termux plugins, check [Forking and Local Development](https://github.com/HardcodedCat/termux-monet/wiki/Termux-Libraries#forking-and-local-development) for info on how to use termux libraries for plugins.
- Enable GitHub actions and do any commit to build termux. e.g. upload a placeholder file.
- Create empty releases. You don't need to manually upload termux apks, GitHub Actions will do all the job for you.

## Special Thanks

- Thanks for [Android Repository](https://t.me/AndroidRepo) and [MódulOS.tk](https://t.me/moduloyappstk) for sharing and supporting the project!
- Thanks for [Termux](https://github.com/termux/termux-app) for creating this incredible app!

***

<p align="center">
<img src="https://raw.githubusercontent.com/HardcodedCat/termux-monet/master/art/ic_monet.svg" width=50% height=50%>
</p>

<div align="center">
  <a href="https://github.com/HardcodedCat/termux-monet/releases">
    <img src="https://img.shields.io/github/downloads/HardcodedCat/termux-monet/total?color=brightgreen&label=Downloads"/>
  </a>
  <a href="https://github.com/HardcodedCat/termux-monet/releases">
    <img src="https://badgen.net/github/assets-dl/HardcodedCat/termux-monet?label=Updates"/>
  </a>
  <a href="https://api.github.com/repos/HardcodedCat/termux-monet/forks">
    <img src="https://img.shields.io/github/forks/HardcodedCat/termux-monet?style=social"/>
  </a>
</div>

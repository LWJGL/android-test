![lwjgl-android](https://i.imgur.com/bUMfzP6.jpg)

[![License](https://img.shields.io/badge/license-BSD-blue.svg)](https://github.com/LWJGL/android-test/blob/master/LICENSE.md)
![Size](https://reposs.herokuapp.com/?path=lwjgl/android-test)
[![Slack Status](https://slack.lwjgl.org/badge.svg)](https://slack.lwjgl.org/)

## LWJGL 3 - Android example project and tests

Build instructions for the Android version of lwjgl3:

- `clone` [lwjgl3](https://github.com/LWJGL/lwjgl3) and `checkout` the `android` branch.
- `SET/export` the `ANDROID_SDK_HOME` environment variable. Its value should be the root of the Android SDK. The Android NDK must also be installed under the root, in the default `ndk-bundle` subdirectory.
- Run `ant compile-templates`. This will take 1-2 minutes.
- Run `ant aar`. This will produce an `lwjgl.aar` file in the `bin/android/` folder.
- Copy `lwjgl.aar` to the `android-test` repository, in the `lwjgl` folder.

Build instructions for the Android demos:

- Open the root in Android Studio.
- Wait for gradle synchronization and indexing to complete.
- Build the project.
- Connect a platform 24 compatible device, either via USB or Wi-Fi.
- Launch either the `gears` or `hellovulkan` run configurations. (shortcut: `Alt+Shift+F10`)

Installation of Vulkan validation layers:

- Create a JNI library folder for the target architecture. For example:
    * `hellovulkan/src/main/jniLibs/arm64-v8a/` or
    * `hellovulkan/src/main/jniLibs/armeabi-v7a/`
- Copy the shared libraries from the corresponding folder in the Android NDK. For example:
    * `<sdkroot>/ndk-bundle/sources/third_party/vulkan/src/build-android/jniLibs/arm64-v8a/` or
    * `<sdkroot>/ndk-bundle/sources/third_party/vulkan/src/build-android/jniLibs/armeabi-v7a/`
- Set the `VALIDATE` variable to `true` in `HelloVulkan.java:53`

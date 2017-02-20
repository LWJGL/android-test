# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in G:\PROGRAMS\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizations !field/*,!class/merging,*
-optimizationpasses 5

-keepclassmembers class org.lwjgl.system.** { *; }
-keepclassmembernames class org.lwjgl.system.** { *; }

-keepclassmembers class sun.misc.Unsafe { *; }
-keepclassmembernames class sun.misc.Unsafe { *; }
 -ignorewarnings

-repackageclasses com.gozdekahraman.currencyapp


-keep class com.gozdekahraman.currencyapp.model.** {*;}
#end Edit

-keep class android.support.v4.** { *; }

-keep public class * extends android.support.v4.**

-keep public class * extends android.support.v7.**

-keep public class * extends android.support.v7.widget.**

-dontwarn android.support.v7.**

-keep class android.support.v7.** { *; }

-keep interface android.support.v7.** { *; }

-keep class android.support.v7.widget.SearchView { *; }

-keep public class * extends android.app.SearchManager

-keep public class * extends android.app.Fragment

-keep public class * extends android.app.Activity

-keep public class * extends android.app.Application

-keep public class * extends android.app.Service

-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.app.backup.BackupAgentHelper

-keep public class * extends android.preference.Preference

-keep class com.facebook.** {
   *;
}
-keepclassmembers class * extends android.os.AsyncTask {
    protected void onPreExecute();
    protected *** doInBackground(...);
    protected void onPostExecute(...);
}

-keepnames class * implements android.os.Parcelable

-keepnames class * implements android.os.Parcel

-keep class * extends android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# Retrofit 2.X
## https://square.github.io/retrofit/ ##

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8

# Keep GSON stuff
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }

# Keep these for GSON and Jackson
-keepattributes EnclosingMethod

#OneSignal

-dontwarn com.onesignal.**

# These 2 methods are called with reflection.
-keep class com.google.android.gms.common.api.GoogleApiClient {
    void connect();
    void disconnect();
}

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

-keep class com.onesignal.ActivityLifecycleListenerCompat** {*;}

# Keep Picasso
-keep class com.squareup.picasso.** { *; }
-keepclasseswithmembers class * {
    @com.squareup.picasso.** *;
}
-keepclassmembers class * {
    @com.squareup.picasso.** *;
}

-keepclassmembers class * extends com.stephentuso.welcome.WelcomeActivity {
    public static java.lang.String welcomeKey();
}

#-keep class com.apptracktion.OkResult {*;}
#-keep class com.apptracktion.RequestBody {*;}

#eventbus

-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#butterknife

# Retain generated class which implement Unbinder.
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }

# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinding.
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }


#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# Keep the entry point to this library, see META-INF\services\javax.annotation.processing.Processor
-keep class com.bumptech.glide.annotation.compiler.GlideAnnotationProcessor


# "duplicate definition of library class"
#-dontnote sun.applet.**
# "duplicate definition of library class"
#-dontnote sun.tools.jar.**
# Reflective accesses in com.google.common.util.concurrent.* and some others
-dontnote com.bumptech.glide.repackaged.com.google.common.**
# com.google.common.collect.* and some others (….common.*.*)
-dontwarn com.google.j2objc.annotations.Weak
# com.google.common.util.concurrent.FuturesGetChecked$GetCheckedTypeValidatorHolder$ClassValueValidator
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#-dontwarn **

-keep class com.google.firebase.** {*;}

# Butterknife
-dontwarn butterknife.internal.**
# Retain generated class which implement Unbinder.
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }

# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinding.
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }
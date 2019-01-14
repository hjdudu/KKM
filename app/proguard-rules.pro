# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontusemixedcaseclassnames          #混淆时不使用大小写混合类名
-dontskipnonpubliclibraryclasses     #不跳过library中的非public的类
-verbose                             #打印混淆的详细信息
-dontoptimize                        #不进行优化，建议使用此选项，
-dontpreverify                       #不进行预校验,Android不需要,可加快混淆速度。
-ignorewarnings                      #忽略警告
#-optimizationpasses 5               #指定代码的压缩级别

-ignorewarnings
#EASEUI
-keep class android.support.v4.** {*;}
-keep class org.xmlpull.** {*;}
-keep class com.baidu.** {*;}
-keep public class * extends com.umeng.**
 -keep class com.umeng.** { *; }
-keep class com.squareup.picasso.* {*;}
-keep class com.hyphenate.* {*;}
-keep class com.hyphenate.chat.** {*;}
-keep class org.jivesoftware.** {*;}
-keep class org.apache.** {*;}
-keep class com.superrtc.** {*;}
-keep class vi.com.gdi.bgl.**{*;}
#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils,注意前面的包名，
#不要SmileUtils复制到自己的项目下keep的时候还是写的demo里的包名
-keep class com.hyphenate.easeui.utils.SmileUtils {*;}
#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}


#友盟
   -dontshrink
   -dontoptimize
   -dontwarn com.google.android.maps.**
   -dontwarn android.webkit.WebView
   -dontwarn com.umeng.**
   -dontwarn com.tencent.weibo.sdk.**
   -dontwarn com.facebook.**
   -keep public class javax.**
   -keep public class android.webkit.**
   -dontwarn android.support.v4.**
 -keepclassmembers class * {
   public <init> (org.json.JSONObject);
 }
 -keep public class com.kekemei.kekemei.R$*{
   public static final int *;
 }
 -keepclassmembers enum * {
   public static **[] values(); public static ** valueOf(java.lang.String);
 }

 #3D 地图 V5.0.0之前：
    -keep   class com.amap.api.maps.**{*;}
    -keep   class com.autonavi.amap.mapcore.*{*;}
    -keep   class com.amap.api.trace.**{*;}

   # 3D 地图 V5.0.0之后：
    -keep   class com.amap.api.maps.**{*;}
    -keep   class com.autonavi.**{*;}
    -keep   class com.amap.api.trace.**{*;}

   # 定位
    -keep class com.amap.api.location.**{*;}
    -keep class com.amap.api.fence.**{*;}
    -keep class com.autonavi.aps.amapapi.model.**{*;}

   # 搜索
    -keep   class com.amap.api.services.**{*;}

   # 2D地图
    -keep class com.amap.api.maps2d.**{*;}
    -keep class com.amap.api.mapcore2d.**{*;}

   # 导航
    -keep class com.amap.api.navi.**{*;}
    -keep class com.autonavi.**{*;}
    # 环信
    -keep class com.hyphenate.** {*;}
    -dontwarn  com.hyphenate.**



    -keep class com.lljjcoder.**{
    	*;
    }

    #地区3级联动选择器

    -keep class com.lljjcoder.**{
    	*;
    }

    -dontwarn demo.**
    -keep class demo.**{*;}
    -dontwarn net.sourceforge.pinyin4j.**
    -keep class net.sourceforge.pinyin4j.**{*;}
    -keep class net.sourceforge.pinyin4j.format.**{*;}
    -keep class net.sourceforge.pinyin4j.format.exception.**{*;}


    #alipay start
    -dontwarn android.net.**
    -keep class android.net.SSLCertificateSocketFactory{*;}

    -keep class com.alipay.android.app.IAlixPay{*;}
    -keep class com.alipay.android.app.IAlixPay$Stub{*;}
    -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
    -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
    -keep class com.alipay.sdk.app.PayTask{ public *;}
    -keep class com.alipay.sdk.app.AuthTask{ public *;}
    #alipay end



    -dontwarn
    -optimizationpasses 5
    -dontusemixedcaseclassnames
    -dontskipnonpubliclibraryclasses
    -dontpreverify
    -verbose
    -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

    -keep public class * extends android.app.Activity
    -keep public class * extends android.app.Application
    -keep public class * extends android.app.Service
    -keep public class * extends android.content.BroadcastReceiver
    -keep public class * extends android.content.ContentProvider
    -keep public class * extends android.app.backup.BackupAgentHelper
    -keep public class * extends android.preference.Preference
    -keep public class com.android.vending.licensing.ILicensingService

    -keepclasseswithmembernames class * {
        native <methods>;
    }

    -keepclasseswithmembers class * {
        public <init>(android.content.Context, android.util.AttributeSet);
    }

    -keepclasseswithmembers class * {
        public <init>(android.content.Context, android.util.AttributeSet, int);
    }

    -keepclassmembers class * extends android.app.Activity {
       public void *(android.view.View);
    }

    -keepclassmembers enum * {
        public static **[] values();
        public static ** valueOf(java.lang.String);
    }

    -keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
    }

    -keep public class * extends android.app.Fragment
    -keep public class * extends android.app.Activity
    -keep public class * extends android.app.Application
    -keep public class * extends android.app.Service
    -keep public class * extends android.content.BroadcastReceiver
    -keep public class * extends android.preference.Preference
    -keep public class * extends android.content.ContentProvider
    -keep public class * extends android.support.v4.**
    -keep public class * extends android.support.annotation.**
    -keep public class * extends android.support.v7.**

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
    -keepattributes Signature
    -keepattributes *Annotation*
    -keep class sun.misc.Unsafe { *; }
    -keep class com.google.gson.stream.** { *; }
    # Application classes that will be serialized/deserialized over Gson 下面替换成自己的实体类
    -keep class com.kekemei.kekemei.bean.** { *; }
#JavaBean
-keepclassmembers public class com.kekemei.kekemei.bean.** {
   void set*(***);
   *** get*();
}
    -dontwarn com.squareup.okhttp3.**
    -keep class com.squareup.okhttp3.** { *;}
    -dontwarn okio.**

    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }

     #okgo
        -dontwarn com.lzy.okgo.**
        -keep class com.lzy.okgo.**{*;}

        #okrx
        -dontwarn com.lzy.okrx.**
        -keep class com.lzy.okrx.**{*;}

        #okserver
        -dontwarn com.lzy.okserver.**
        -keep class com.lzy.okserver.**{*;}

        #okhttp
        -dontwarn okhttp3.**
        -keep class okhttp3.**{*;}

        #okio
        -dontwarn okio.**
        -keep class okio.**{*;}

-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keep class com.kekemei.kekemei.view.loading.** { *; }
-keep class com.kekemei.kekemei.view.loading.indicators.** { *; }

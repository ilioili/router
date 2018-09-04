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
#Router -end
 -keepattributes *Annotation*
 -keep public interface com.trc.android.router.**
 -keep public class com.trc.android.router.**
 -keep interface com.trc.android.router.**
 -keep @interface com.trc.android.router.annnotation.**
  -keep @com.trc.android.router.annnotation.** class *
 #Router -end


 # Add project specific ProGuard rules here.
 # By default, the flags in this file are appended to flags specified
 # in D:\Android\SDK/tools/proguard/proguard-android.txt
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

 # The support library contains references to newer platform versions.
 # Don't warn about those in case this app is linking against an older
 # platform version.  We know about them, and they are safe.

 # This is a configuration file for ProGuard.
 # http://proguard.sourceforge.net/index.html#manual/usage.html

 # Optimizations: If you don't want to optimize, use the
 # proguard-android.txt configuration file instead of this one, which
 # turns off the optimization flags.  Adding optimization introduces
 # certain risks, since for example not all optimizations performed by
 # ProGuard works on all versions of Dalvik.  The following flags turn
 # off various optimizations known to have issues, but the list may not
 # be complete or up to date. (The "arithmetic" optimization can be
 # used if you are only targeting Android 2.0 or later.)  Make sure you
 # test thoroughly if you go this route.
 -optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
 -optimizationpasses 5
 -allowaccessmodification
 -dontpreverify

 # The remainder of this file is identical to the non-optimized version
 # of the Proguard configuration file (except that the other file has
 # flags to turn off optimization).

 -dontusemixedcaseclassnames
 -dontskipnonpubliclibraryclasses
 -verbose



 -keepattributes *Annotation*
 -keep public class com.google.vending.licensing.ILicensingService
 -keep public class com.android.vending.licensing.ILicensingService

 # For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
 -keepclasseswithmembernames class * {
     native <methods>;
 }

 # keep setters in Views so that animations can still work.
 # see http://proguard.sourceforge.net/manual/examples.html#beans
 -keepclassmembers public class * extends android.view.View {
    void set*(***);
    *** get*();
 }

 # We want to keep methods in Activity that could be used in the XML attribute onClick
 -keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
 }

 # For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 -keep class * implements android.os.Parcelable {
   public static final android.os.Parcelable$Creator *;
 }

 -keepclassmembers class **.R$* {
     public static <fields>;
 }

 # The support library contains references to newer platform versions.
 # Don't warn about those in case this app is linking against an older
 # platform version.  We know about them, and they are safe.
 -dontwarn android.support.**


 #Gson
 -dontwarn com.google.code.gson.**
 -keep class com.google.code.gson.** { *; }
 -keep interface com.google.code.gson.** { *; }
 -keepattributes Signature
 -keep class sun.misc.Unsafe { *; }
 #-keep class cn.pocketwallet.pocketwallet.model.** { *; }

 # Serializable
 -keepnames class * implements java.io.Serializable
 -keepclassmembers class * implements java.io.Serializable {
     <fields>;
 }

 # OkHttp
 -keepattributes *Annotation*
 -keep class com.squareup.okhttp.** { *; }
 -keep interface com.squareup.okhttp.** { *; }
 -dontwarn com.squareup.okhttp.**
 -dontwarn okio.**

 # OkHttp
 -keep class okhttp3.** { *; }
 -keep interface okhttp3.** { *; }
 -dontwarn okhttp3.**

 # model(反序列化Json用的 )
 #-keep class com.tairanchina.taiheapp.** { *; }
 #实体类不参与混淆
 -keep class io.trchain.cube.model.**{*;}

 # For lianlianpay
 -dontwarn com.yintong.**
 -keep class com.yintong.** { *; }
 -keep interface com.yintong.** { *; }


 #apache
 -dontwarn org.apache.**
 -keep class org.apache.** { *; }
 -keep interface org.apache.** { *; }


 #UMeng
 -dontwarn com.umeng.**
 -keep class com.umeng.** { *; }
 -keep interface com.umeng.** { *; }
 -keep public class * extends com.umeng.**
 -keepclassmembers class * {
    public <init>(org.json.JSONObject);
 }

 -keep public class cn.pocketwallet.pocketwallet.R$*{
 public static final int *;
 }

 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 -keep public class com.umeng.fb.** { *;}
 -keep public class com.umeng.fb.ui.ThreadView { }


 -keep class com.tencent.** {*;}
 -dontwarn com.tencent.**
 -keep public class com.umeng.soexample.R$*{
     public static final int *;
 }
 -keep public class com.umeng.soexample.R$*{
     public static final int *;
 }
 -keep class com.tencent.open.TDialog$*
 -keep class com.tencent.open.TDialog$* {*;}
 -keep class com.tencent.open.PKDialog
 -keep class com.tencent.open.PKDialog {*;}
 -keep class com.tencent.open.PKDialog$*
 -keep class com.tencent.open.PKDialog$* {*;}

 -keep class com.umeng.scrshot.**
 -keep public class com.tencent.** {*;}
 -keep class com.umeng.socialize.sensor.**
 -keep class com.umeng.socialize.handler.**
 -keep class com.umeng.socialize.handler.*

 -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
 -keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

 -keep class com.sina.** {*;}
 -dontwarn com.sina.**

 -keep public interface com.facebook.**
 -keep public interface com.tencent.**
 -keep public interface com.umeng.socialize.**
 -keep public interface com.umeng.socialize.sensor.**
 -keep public interface com.umeng.scrshot.**

 -keep public class com.umeng.socialize.* {*;}
 -keep public class javax.**
 -keep public class android.webkit.**

 -dontwarn com.google.zxing.**
 -dontwarn com.squareup.picsso.**
 -dontwarn com.yintong.**
 -dontwarn Decoder.**
 -dontwarn com.seaway.pinpad.**
 -dontwarn com.nineoldandroids.**
 -dontwarn com.seaway.android.**


 #以下为UmengPush所需要的混淆配置

 -dontwarn com.ut.mini.**
 -dontwarn okio.**
 -dontwarn com.xiaomi.**
 -dontwarn com.squareup.wire.**
 -dontwarn android.support.v4.**

 -keepattributes *Annotation*

 -keep class android.support.v4.** { *; }
 -keep interface android.support.v4.app.** { *; }

 -keep class okio.** {*;}
 -keep class com.squareup.wire.** {*;}

 -keep class com.umeng.message.protobuffer.* {
 	 public <fields>;
          public <methods>;
 }

 -keep class com.umeng.message.* {
 	 public <fields>;
          public <methods>;
 }

 -keep class org.android.agoo.impl.* {
 	 public <fields>;
          public <methods>;
 }

 -keep class org.android.agoo.service.* {*;}

 -keep class org.android.spdy.**{*;}

 -keep public class com.tairanchina.taiheapp.R$*{
     public static final int *;
 }

 -keepclassmembers class * extends com.tencent.smtt.sdk.WebChromeClient{
    		public void openFileChooser(...);
 }

 -keepclassmembers class * {
     @io.trchain.cube.utils.injectionview.Click <methods>;
 }

 #Retrofit
 -dontwarn retrofit2.**
 -keep class retrofit2.** { *; }
 -keep interface retrofit2.** { *; }
 -keepattributes Signature
 -keepattributes Exceptions
 -keepclassmembernames interface * {
     @retrofit2.http.* <methods>;
 }

 #Rx
 -dontwarn rx.**
 -keep class rx.** { *; }
 -keep interface rx.** { *; }

 #Qiniu
 -dontwarn com.qiniu.**
 -keep class com.qiniu.** { *; }


 ##支付宝 开始
 #-libraryjars libs/alipaySDK-20161222.jar
 #-dontwarn com.alipay.**
 #-keep class com.alipay.android.app.IAlixPay{*;}
 #-keep class com.alipay.android.app.IAlixPay$Stub{*;}
 #-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
 #-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
 #-keep class com.alipay.sdk.app.PayTask{ public *;}
 #-keep class com.alipay.sdk.app.AuthTask{ public *;}
 #
 ##支付宝 结束


 -dontwarn com.tairan.pay.**
 -keep class com.tairan.pay.**{*;}
 -keep interface com.tairan.pay.** { *; }

 #Umeng配置-start
 -dontwarn com.taobao.**
 -dontwarn anet.channel.**
 -dontwarn anetwork.channel.**
 -dontwarn org.android.**
 -dontwarn org.apache.thrift.**
 -dontwarn com.xiaomi.**
 -dontwarn com.huawei.**

 -keepattributes *Annotation*

 -keep class com.taobao.** {*;}
 -keep class org.android.** {*;}
 -keep class anet.channel.** {*;}
 -keep class com.umeng.** {*;}
 -keep class com.xiaomi.** {*;}
 -keep class com.huawei.** {*;}
 -keep class org.apache.thrift.** {*;}

 -keep class com.alibaba.sdk.android.**{*;}
 -keep class com.ut.**{*;}
 -keep class com.ta.**{*;}

 -keep public class **.R$*{
    public static final int *;
 }

 #（可选）避免Log打印输出
 -assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
    public static *** w(...);
  }
  #Umeng配置-end


 #支付宝 开始
 #-libraryjars libs/alipaySingle-20161222.jar
 -dontwarn com.alipay.**
 -keep class com.alipay.android.app.IAlixPay{*;}
 -keep class com.alipay.android.app.IAlixPay$Stub{*;}
 -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
 -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
 -keep class com.alipay.sdk.app.PayTask{ public *;}
 -keep class com.alipay.sdk.app.AuthTask{ public *;}

 #支付宝 结束

 #EventBus -start
 -keepattributes *Annotation*
 -keep @interface com.tairanchina.core.eventbus.*
 -keepclassmembers,allowobfuscation class * {
     @com.tairanchina.core.eventbus.Action *;
 }
 #EventBus -end


 #未混淆的类和成员
 #-printseeds seeds.txt
 #列出从 apk 中删除的代码
 #-printusage unused.txt
 #基线包使用，生成mapping.txt
 -printmapping mapping.txt
 #生成的mapping.txt在app/buidl/outputs/mapping/release路径下，移动到/app路径下

 #修复后的项目使用，保证混淆结果一致
 #-applymapping mapping.txt

 #hotfix start
 -keep class com.taobao.sophix.**{*;}
 -keep class com.ta.utdid2.device.**{*;}
 #防止inline
 -dontoptimize
 #hotfix end



 #UmengPush-start
 -dontwarn com.taobao.**
 -dontwarn anet.channel.**
 -dontwarn anetwork.channel.**
 -dontwarn org.android.**
 -dontwarn org.apache.thrift.**
 -dontwarn com.xiaomi.**
 -dontwarn com.huawei.**

 -keepattributes *Annotation*

 -keep class com.taobao.** {*;}
 -keep class org.android.** {*;}
 -keep class anet.channel.** {*;}
 -keep class com.umeng.** {*;}
 -keep class com.xiaomi.** {*;}
 -keep class com.huawei.** {*;}
 -keep class org.apache.thrift.** {*;}

 -keep class com.alibaba.sdk.android.**{*;}
 -keep class com.ut.**{*;}
 -keep class com.ta.**{*;}

 -keep public class **.R$*{
    public static final int *;
 }

 #（可选）避免Log打印输出
 -assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
    public static *** w(...);
  }
 #UmentPush-end


 #Glide start
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * extends com.bumptech.glide.AppGlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }
 #Glide end


 #小能客服
 -dontwarn cn.xiaoneng.**
 -dontwarn android.support.v4xn.**
 -dontwarn orgxn.fusesource.**
 -keep class cn.xiaoneng.** {*;}
 -keep class android.support.v4xn.** {*;}
 -keep class orgxn.fusesource.** {*;}
 #


 ################直播相关混淆##########################
 -keep class com.youth.banner.** {
     *;
  }
  -keep class com.cactus.ctbaselibrary.**{*;}
  -dontwarn com.cactus.ctbaselibrary.**

  -keep public class * implements com.bumptech.glide.module.GlideModule
  -keep public class * extends com.bumptech.glide.AppGlideModule
  -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
  }

  -keep class com.pili.pldroid.player.** { *; }
  -keep class tv.danmaku.ijk.media.player.** {*;}
  -keep class com.qiniu.pili.droid.streaming.** { *; }

  -dontwarn com.igexin.**
  -keep class com.igexin.** { *; }
  -keep class org.json.** { *; }

  -dontwarn com.netease.**
  -dontwarn io.netty.**
  -keep class com.netease.** {*;}
  #如果 netty 使用的官方版本，它中间用到了反射，因此需要 keep。如果使用的是我们提供的版本，则不需要 keep
  -keep class io.netty.** {*;}

  #如果你使用全文检索插件，需要加入
  -dontwarn org.apache.lucene.**
  -keep class org.apache.lucene.** {*;}
  ############################直播相关混淆##################################################

 #Router -end
  -keepattributes *Annotation*
  -keep public interface com.trc.android.router.**
  -keep public class com.trc.android.router.**
  -keep interface com.trc.android.router.**
  -keep @interface com.trc.android.router.annnotation.**
   -keep @com.trc.android.router.annnotation.** class *
  #Router -end

  ###新友盟sdk
  -dontshrink
  -dontoptimize
  -dontwarn com.tencent.**
  -dontwarn com.google.android.maps.**
  -dontwarn androidumeng.webkit.WebView
  -dontwarn com..**
  -dontwarn com.tencent.weibo.sdk.**
  -dontwarn com.facebook.**
  -keep public class javax.**
  -keep public class android.webkit.**
  -dontwarn android.support.v4.**
  -keep enum com.facebook.**
  -keepattributes Exceptions,InnerClasses,Signature
  -keepattributes *Annotation*
  -keepattributes SourceFile,LineNumberTable

  -keep public interface com.facebook.**
  -keep public interface com.tencent.**
  -keep public interface com.umeng.socialize.**
  -keep public interface com.umeng.socialize.sensor.**
  -keep public interface com.umeng.scrshot.**

  -keep public class com.umeng.socialize.* {*;}


  -keep class com.facebook.**
  -keep class com.facebook.** { *; }
  -keep class com.umeng.scrshot.**
  -keep public class com.tencent.** {*;}
  -keep class com.umeng.socialize.sensor.**
  -keep class com.umeng.socialize.handler.**
  -keep class com.umeng.socialize.handler.*
  -keep class com.umeng.weixin.handler.**
  -keep class com.umeng.weixin.handler.*
  -keep class com.umeng.qq.handler.**
  -keep class com.umeng.qq.handler.*
  -keep class UMMoreHandler{*;}
  -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
  -keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
  -keep class im.yixin.sdk.api.YXMessage {*;}
  -keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
  -keep class com.tencent.mm.sdk.** {
     *;
  }
  -keep class com.tencent.mm.opensdk.** {
     *;
  }
  -keep class com.tencent.wxop.** {
     *;
  }
  -keep class com.tencent.mm.sdk.** {
     *;
  }
  -dontwarn twitter4j.**
  -keep class twitter4j.** { *; }

  -keep class com.tencent.** {*;}
  -dontwarn com.tencent.**
  -keep class com.kakao.** {*;}
  -dontwarn com.kakao.**
  -keep public class com.umeng.com.umeng.soexample.R$*{
      public static final int *;
  }
  -keep public class com.linkedin.android.mobilesdk.R$*{
      public static final int *;
  }
  -keepclassmembers enum * {
      public static **[] values();
      public static ** valueOf(java.lang.String);
  }

  -keep class com.tencent.open.TDialog$*
  -keep class com.tencent.open.TDialog$* {*;}
  -keep class com.tencent.open.PKDialog
  -keep class com.tencent.open.PKDialog {*;}
  -keep class com.tencent.open.PKDialog$*
  -keep class com.tencent.open.PKDialog$* {*;}
  -keep class com.umeng.socialize.impl.ImageImpl {*;}
  -keep class com.sina.** {*;}
  -dontwarn com.sina.**
  -keep class  com.alipay.share.sdk.** {
     *;
  }

  -keepnames class * implements android.os.Parcelable {
      public static final ** CREATOR;
  }

  -keep class com.linkedin.** { *; }
  -keep class com.android.dingtalk.share.ddsharemodule.** { *; }
  -keepattributes Signature


  ################React Native相关混淆 START##########################
  # Keep our interfaces so they can be used by other ProGuard rules.
  # See http://sourceforge.net/p/proguard/bugs/466/
  -keep,allowobfuscation @interface com.facebook.proguard.annotations.DoNotStrip
  -keep,allowobfuscation @interface com.facebook.proguard.annotations.KeepGettersAndSetters
  -keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

  # Do not strip any method/class that is annotated with @DoNotStrip
  -keep @com.facebook.proguard.annotations.DoNotStrip class *
  -keep @com.facebook.common.internal.DoNotStrip class *
  -keepclassmembers class * {
      @com.facebook.proguard.annotations.DoNotStrip *;
      @com.facebook.common.internal.DoNotStrip *;
  }

  -keepclassmembers @com.facebook.proguard.annotations.KeepGettersAndSetters class * {
    void set*(***);
    *** get*();
  }

  -keep class * extends com.facebook.react.bridge.JavaScriptModule { *; }
  -keep class * extends com.facebook.react.bridge.NativeModule { *; }
  -keepclassmembers,includedescriptorclasses class * { native <methods>; }
  -keepclassmembers class *  { @com.facebook.react.uimanager.UIProp <fields>; }
  -keepclassmembers class *  { @com.facebook.react.uimanager.annotations.ReactProp <methods>; }
  -keepclassmembers class *  { @com.facebook.react.uimanager.annotations.ReactPropGroup <methods>; }
  -keep class com.facebook.** { *; }

  -dontwarn com.facebook.**

  # TextLayoutBuilder uses a non-public Android constructor within StaticLayout.
  # See libs/proxy/src/main/java/com/facebook/fbui/textlayoutbuilder/proxy for details.
  -dontwarn android.text.StaticLayout

  # okhttp

  -keepattributes Signature
  -keepattributes *Annotation*
  -keep class okhttp3.** { *; }
  -keep interface okhttp3.** { *; }
  -dontwarn okhttp3.**

  # okio

  -keep class sun.misc.Unsafe { *; }
  -dontwarn java.nio.file.*
  -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
  -dontwarn okio.**
  ################React Native相关混淆 END##########################


 ################X5内核 相关混淆 START########################################################################################################
  -keep class com.tencent.smtt.export.external.**{
      *;
  }

  -keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
  	*;
  }

  -keep class com.tencent.smtt.sdk.CacheManager {
  	public *;
  }

  -keep class com.tencent.smtt.sdk.CookieManager {
  	public *;
  }

  -keep class com.tencent.smtt.sdk.WebHistoryItem {
  	public *;
  }

  -keep class com.tencent.smtt.sdk.WebViewDatabase {
  	public *;
  }

  -keep class com.tencent.smtt.sdk.WebBackForwardList {
  	public *;
  }

  -keep public class com.tencent.smtt.sdk.WebView {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
  	public static final <fields>;
  	public java.lang.String getExtra();
  	public int getType();
  }

  -keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.WebView$PictureListener {
  	public <fields>;
  	public <methods>;
  }


  -keepattributes InnerClasses

  -keep public enum com.tencent.smtt.sdk.WebSettings$** {
      *;
  }

  -keep public enum com.tencent.smtt.sdk.QbSdk$** {
      *;
  }

  -keep public class com.tencent.smtt.sdk.WebSettings {
      public *;
  }


  -keepattributes Signature
  -keep public class com.tencent.smtt.sdk.ValueCallback {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.WebViewClient {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.DownloadListener {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.WebChromeClient {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
  	public <fields>;
  	public <methods>;
  }

  -keep class com.tencent.smtt.sdk.SystemWebChromeClient{
  	public *;
  }
  # 1. extension interfaces should be apparent
  -keep public class com.tencent.smtt.export.external.extension.interfaces.* {
  	public protected *;
  }

  # 2. interfaces should be apparent
  -keep public class com.tencent.smtt.export.external.interfaces.* {
  	public protected *;
  }

  -keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
  	public protected *;
  }

  -keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.WebIconDatabase {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.WebStorage {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.DownloadListener {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.QbSdk {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
  	public <fields>;
  	public <methods>;
  }
  -keep public class com.tencent.smtt.sdk.CookieSyncManager {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.Tbs* {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.utils.LogFileUtils {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.utils.TbsLog {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.utils.TbsLogClient {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.CookieSyncManager {
  	public <fields>;
  	public <methods>;
  }

  # Added for game demos
  -keep public class com.tencent.smtt.sdk.TBSGamePlayer {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
  	public <fields>;
  	public <methods>;
  }

  -keep public class com.tencent.smtt.utils.Apn {
  	public <fields>;
  	public <methods>;
  }
  -keep class com.tencent.smtt.** {
  	*;
  }
  # end


  -keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
  	public <fields>;
  	public <methods>;
  }

  -keep class MTT.ThirdAppInfoNew {
  	*;
  }

  -keep class com.tencent.mtt.MttTraceEvent {
  	*;
  }

  # Game related
  -keep public class com.tencent.smtt.gamesdk.* {
  	public protected *;
  }

  -keep public class com.tencent.smtt.sdk.TBSGameBooter {
          public <fields>;
          public <methods>;
  }
 ################X5内核 相关混淆 END########################################################################################################

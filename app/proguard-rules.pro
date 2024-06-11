# Material Components
#-dontwarn com.google.android.material.**
#-keep class com.google.android.material.** { *; }
#
## ViewPager2
#-dontwarn androidx.viewpager2.**
#-keep class androidx.viewpager2.** { *; }
#
## Retrofit
#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#
## Gson Converter
#-keep class com.google.gson.** { *; }
#-dontwarn com.google.gson.**
#-keepattributes Signature
#-keepattributes *Annotation*
#-keep class com.google.gson.reflect.TypeToken { *; }
#-keep class **$$Lambda$* { *; }
#
## OkHttp3
#-dontwarn okhttp3.**
#-keep class okhttp3.** { *; }
#-dontwarn okio.**
#-dontwarn javax.annotation.**
#
## OkHttp3 Logging Interceptor
#-keep class okhttp3.logging.** { *; }
#-dontwarn okhttp3.logging.**
#
## Glide
#-keep public class * extends com.bumptech.glide.module.AppGlideModule
#-keep public class * extends com.bumptech.glide.module.LibraryGlideModule
#-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#    **[] $VALUES;
#    public *;
#}
#-dontwarn com.bumptech.glide.**
#-keep class com.bumptech.glide.load.** { *; }
#-keep class com.bumptech.glide.request.** { *; }
#
## 保留注解和Lambda表达式
#-keepattributes *Annotation*
#-keep class **$$Lambda$* { *; }

-keep class com.www446.haveagoodday.model.** { *; }


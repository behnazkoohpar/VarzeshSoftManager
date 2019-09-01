
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
-dontwarn com.google.errorprone.annotations.*

-keep public class com.noor.payment.data.model.** { *;}
-dontwarn junit.textui.*
-dontnote org.apache.commons.codec.**
-dontwarn org.conscrypt.*
-dontwarn javax.xml.namespace.*
-dontwarn javax.naming.directory.*
-dontwarn javax.naming.*
-dontnote javax.xml.namespace.*
-dontnote javax.xml.*
-keep class pl.droidsonroids.gif.*
-keep class com.theartofdev.edmodo.cropper.*
-keep class com.github.aakira.expandablelayout.*
-keep class com.github.anastr.speedviewlib.**
-keep class me.itangqi.waveloadingview.*
-keep class com.orm.SugarRecord
-keepclassmembers class net.sourceforge.zbar.ImageScanner { *; }
-keepclassmembers class net.sourceforge.zbar.Image { *; }
-keepclassmembers class net.sourceforge.zbar.Symbol { *; }
-keepclassmembers class net.sourceforge.zbar.SymbolSet { *; }
#-keep public class com.google.android.gms.* { public *; }
#-dontwarn com.google.android.gms.**
#-keep class com.google.android.gms.maps.** { *; }
#-keep interface com.google.android.gms.maps.* { *; }
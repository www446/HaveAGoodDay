plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.www446.haveagoodday"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.www446.haveagoodday"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs{
        create("release") {
            storeFile = file("Untitled")
            storePassword = "123123"
            keyAlias = "key0"
            keyPassword = "123123"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug{
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)

    //androidx官方控件库
    implementation("com.google.android.material:material:1.12.0")
    //viewpager2官方库
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    //网络请求三方库
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    //gson解析库
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")
    //三方打印网络日志的库
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    //三方图片加载库
    implementation ("com.github.bumptech.glide:glide:4.16.0")


}
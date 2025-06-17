import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.n0tice"
    compileSdk = 35

    val properties = Properties()
    properties.load(FileInputStream(rootProject.file("local.properties")))

    defaultConfig {
        applicationId = "com.example.n0tice"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "CONSUMER_KEY", properties.getProperty("CONSUMER_KEY"))
        buildConfigField("String", "CONSUMER_SECRET", properties.getProperty("CONSUMER_SECRET"))

        buildConfigField("String", "OAUTH_NAVER_CLIENT_NAME", properties.getProperty("OAUTH_NAVER_CLIENT_NAME"))
        buildConfigField("String", "OAUTH_NAVER_CLIENT_ID", properties.getProperty("OAUTH_NAVER_CLIENT_ID"))
        buildConfigField("String", "OAUTH_NAVER_CLIENT_SECRET", properties.getProperty("OAUTH_NAVER_CLIENT_SECRET"))

        buildConfigField("String", "OAUTH_GOOGLE_CLIENT_ID", properties.getProperty("OAUTH_GOOGLE_CLIENT_ID"))
        buildConfigField("String", "OAUTH_GOOGLE_CLIENT_SECRET", properties.getProperty("OAUTH_GOOGLE_CLIENT_SECRET"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.material)

    // navigation
    implementation(libs.androidx.navigation.compose)

    // viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.logging.interceptor)

    // The view calendar library for Android
    implementation("com.kizitonwose.calendar:view:2.7.0")

    // The compose calendar library for Android
    implementation("com.kizitonwose.calendar:compose:2.7.0")
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.browser)

    implementation("com.navercorp.nid:oauth:5.10.0") // jdk 11

    // naver login dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.legacy:legacy-support-core-utils:1.0.0")
    implementation("androidx.browser:browser:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("androidx.fragment:fragment-ktx:1.3.6")
    implementation("com.squareup.moshi:moshi-kotlin:1.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.1")
    implementation("com.airbnb.android:lottie:3.1.0")

    // google play services
    implementation ("com.google.gms:google-services:4.4.2")
    implementation ("com.google.firebase:firebase-auth:23.2.1")
    implementation ("com.google.firebase:firebase-bom:33.15.0")
    implementation ("com.google.android.gms:play-services-auth:21.3.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
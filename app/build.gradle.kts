import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.jvillad1.marsrover"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        all {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.nasa.gov/mars-photos/api/v1/\""
            )
            buildConfigField(
                "String",
                "NASA_API_KEY",
                gradleLocalProperties(rootDir).getProperty("nasaApiKey")
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
        kotlinCompilerVersion = "1.4.31"
    }
}

dependencies {

    // UI
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.activity:activity-compose:1.3.0-alpha06")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material-icons-core:1.0.0-beta04")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-beta04")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // KTX
    implementation("androidx.core:core-ktx:1.3.2")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.33-beta")
    implementation("androidx.hilt:hilt-common:1.0.0-beta01")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha01")
    kapt("com.google.dagger:hilt-compiler:2.33-beta")
    kapt("androidx.hilt:hilt-compiler:1.0.0-beta01")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${rootProject.extra["compose_version"]}")

    // Navigation
    implementation("androidx.navigation:navigation-compose:1.0.0-alpha10")

    // Logging
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Networking
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.moshi:moshi:1.11.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Room
    implementation ("androidx.room:room-runtime:2.2.6")
    implementation ("androidx.room:room-ktx:2.2.6")
    kapt ("androidx.room:room-compiler:2.2.6")

    // Accompanist
    implementation ("com.google.accompanist:accompanist-glide:0.7.0")
    implementation ("com.google.accompanist:accompanist-insets:0.7.1")

    // Test
    testImplementation("junit:junit:4.13.2")

    // Android Test
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
}

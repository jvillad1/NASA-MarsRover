buildscript {
    val compose_version by extra("1.0.0-beta06")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.35")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

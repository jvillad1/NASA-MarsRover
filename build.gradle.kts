import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

buildscript {
    val compose_version by extra("1.0.0-beta01")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha13")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

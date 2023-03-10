buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependency.GradlePlugin.GRADLE_ANDROID)
        classpath(Dependency.GradlePlugin.GRADLE_KOTLIN)
        classpath(Dependency.GradlePlugin.GRADLE_HILT)
    }
}

plugins {
    id(Dependency.GradlePlugin.GRADLE_KTLINT) version Version.GRADLE_KTLINT
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

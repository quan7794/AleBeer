plugins {
    id("org.sonarqube") version app.wac.team.buildsrc.Depends.Versions.sonarqubeVersion
    detekt
    id("com.github.ben-manes.versions") version app.wac.team.buildsrc.Depends.Versions.checkDependencyVersionsVersion
    id("com.osacky.doctor") version app.wac.team.buildsrc.Depends.Versions.gradleDoctorVersion
    id("com.autonomousapps.dependency-analysis") version app.wac.team.buildsrc.Depends.Versions.dependencyAnalysisVersion
    id("org.sonatype.gradle.plugins.scan") version app.wac.team.buildsrc.Depends.Versions.sonatypeScanGradleVersion
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(app.wac.team.buildsrc.Depends.ClassPaths.gradle)
        classpath(
            kotlin(
                app.wac.team.buildsrc.Depends.ClassPaths.kotlin_gradle_plugin,
                version = app.wac.team.buildsrc.Depends.Versions.kotlinVersion
            )
        )
        classpath(app.wac.team.buildsrc.Depends.ClassPaths.navigation_safe_args_gradle_plugin)
        classpath(app.wac.team.buildsrc.Depends.ClassPaths.hilt_android_gradle_plugin)
        classpath(app.wac.team.buildsrc.Depends.ClassPaths.sonarqube_gradle_plugin)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        mavenCentral()
        maven("https://maven.google.com/")
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

configure<com.osacky.doctor.DoctorExtension> {
    disallowMultipleDaemons.set(false)
    negativeAvoidanceThreshold.set(500)
    warnWhenJetifierEnabled.set(true)

    javaHome {
        ensureJavaHomeIsSet.set(true)
        ensureJavaHomeMatches.set(true)
        failOnError.set(true)
    }
}

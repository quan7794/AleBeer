package app.wac.team.buildsrc

import app.wac.team.buildsrc.Depends.Versions.appVersionCode

object Depends {

    object Versions {
        const val appVersionCode = 1_000_000
        const val gradleVersion = "7.3.1"
        const val androidCompileSdkVersion = 33
        const val targetSdkVersion = 33
        const val minSdkVersion = 21
        const val kotlinVersion = "1.7.21"
        const val rxKotlinVersion = "3.0.1"
        const val rxAndroidVersion = "3.0.2"
        const val rxJavaVersion = "3.1.5"
        const val rxBinding = "4.0.0"
        const val retrofit2Version = "2.9.0"
        const val okhttpLoggingVersion = "5.0.0-alpha.10"
        const val chuckerVersion = "3.5.2"
        const val gsonVersion = "2.10"
        const val retrofitConverterGson = "2.9.0"
        const val lifecycleVersion = "2.4.0"
        const val constraintLayoutVersion = "2.1.4"
        const val supportVersion = "1.4.1"
        const val materialVersion = "1.4.0"
        const val coreKtxVersion = "1.9.0"
        const val navigationVersion = "2.5.2"
        const val pagingVersion = "3.1.1"
        const val multidexVersion = "2.0.1"
        const val fragmentExtVersion = "1.4.0"
        const val recyclerviewVersion = "1.3.0-rc01"
        const val hiltVersion = "2.44"
        const val hiltCompilerVersion = "1.0.0"
        const val hiltNavigationComposeVersion = "1.0.0-alpha03"
        const val javaxInjectVersion = "1"
        const val timberVersion = "5.0.1"
        const val lottieVersion = "4.2.2"
        const val glideVersion = "4.14.2"
        const val autoDispose = "2.1.1"
        const val dataStorePreferenceVersion = "1.0.0"
        const val coroutinesAndroidVersion = "1.6.4"

        const val mockitoKotlinVersion = "2.2.0"
        const val mockitoCoreVersion = "4.10.0"
        const val mockitoInlineVersion = "4.10.0"
        const val espressoVersion = "3.5.0"
        const val junitVersion = "4.13.2"
        const val supportTestVersion = "1.5.0"
        const val testCoreVersion = "1.4.0"
        const val testExtJunitVersion = "1.1.3"
        const val sonarqubeVersion = "3.5.0.2730"
        const val detektVersion = "1.22.0"
        const val checkDependencyVersionsVersion = "0.44.0"
        const val gradleDoctorVersion = "0.8.1"
        const val dependencyAnalysisVersion = "1.17.0"
        const val sonatypeScanGradleVersion = "2.5.3"
        const val leakCanaryVersion = "2.10"
        const val coroutinesTestVersion = "1.6.4"
        const val mockkVersion = "1.13.3"
        const val archCoreTestingVersion = "2.1.0"
    }

    object ClassPaths {
        const val gradle = "com.android.tools.build:gradle:${app.wac.team.buildsrc.Depends.Versions.gradleVersion}"
        const val kotlin_gradle_plugin = "gradle-plugin"
        const val navigation_safe_args_gradle_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${app.wac.team.buildsrc.Depends.Versions.navigationVersion}"

        const val hilt_android_gradle_plugin = "com.google.dagger:hilt-android-gradle-plugin:${app.wac.team.buildsrc.Depends.Versions.hiltVersion}"
        const val sonarqube_gradle_plugin = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${app.wac.team.buildsrc.Depends.Versions.sonarqubeVersion}"
        const val detekt_gradle_plugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${app.wac.team.buildsrc.Depends.Versions.detektVersion}"
    }

    object Libraries {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${app.wac.team.buildsrc.Depends.Versions.kotlinVersion}"
        const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${app.wac.team.buildsrc.Depends.Versions.kotlinVersion}"
        const val multidex = "androidx.multidex:multidex:${app.wac.team.buildsrc.Depends.Versions.multidexVersion}"
        const val hilt_android = "com.google.dagger:hilt-android:${app.wac.team.buildsrc.Depends.Versions.hiltVersion}"
        const val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:${app.wac.team.buildsrc.Depends.Versions.hiltVersion}"
        const val hilt_compiler = "androidx.hilt:hilt-compiler:${app.wac.team.buildsrc.Depends.Versions.hiltCompilerVersion}"
        const val fragment_ktx = "androidx.fragment:fragment-ktx:${app.wac.team.buildsrc.Depends.Versions.fragmentExtVersion}"
        const val android_core_ktx = "androidx.core:core-ktx:${app.wac.team.buildsrc.Depends.Versions.coreKtxVersion}"
        const val paging_runtime_ktx = "androidx.paging:paging-runtime-ktx:${app.wac.team.buildsrc.Depends.Versions.pagingVersion}"
        const val paging_rx = "androidx.paging:paging-rxjava3:${app.wac.team.buildsrc.Depends.Versions.pagingVersion}"
        const val java_inject = "javax.inject:javax.inject:${app.wac.team.buildsrc.Depends.Versions.javaxInjectVersion}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${app.wac.team.buildsrc.Depends.Versions.retrofit2Version}"
        const val retrofit_adapter_rx = "com.squareup.retrofit2:adapter-rxjava3:${app.wac.team.buildsrc.Depends.Versions.retrofit2Version}"
        const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${app.wac.team.buildsrc.Depends.Versions.okhttpLoggingVersion}"
        const val timber = "com.jakewharton.timber:timber:${app.wac.team.buildsrc.Depends.Versions.timberVersion}"
        const val material = "com.google.android.material:material:${app.wac.team.buildsrc.Depends.Versions.materialVersion}"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val junit = "junit:junit:${app.wac.team.buildsrc.Depends.Versions.junitVersion}"
        const val test_runner = "androidx.test:runner:${app.wac.team.buildsrc.Depends.Versions.supportTestVersion}"
        const val test_rules = "androidx.test:rules:${app.wac.team.buildsrc.Depends.Versions.supportTestVersion}"
        const val test_core = "androidx.test:core:${app.wac.team.buildsrc.Depends.Versions.testCoreVersion}"
        const val test_ext_junit = "androidx.test.ext:junit:${app.wac.team.buildsrc.Depends.Versions.testExtJunitVersion}"
        const val espresso_core = "androidx.test.espresso:espresso-core:${app.wac.team.buildsrc.Depends.Versions.espressoVersion}"
        const val gson = "com.google.code.gson:gson:${app.wac.team.buildsrc.Depends.Versions.gsonVersion}"
        const val converter_gson = "com.squareup.retrofit2:converter-gson:${app.wac.team.buildsrc.Depends.Versions.retrofitConverterGson}"
        const val appcompat = "androidx.appcompat:appcompat:${app.wac.team.buildsrc.Depends.Versions.supportVersion}"
        const val mockito_core = "org.mockito:mockito-core:${app.wac.team.buildsrc.Depends.Versions.mockitoCoreVersion}"
        const val mockito_inline = "org.mockito:mockito-inline:${app.wac.team.buildsrc.Depends.Versions.mockitoInlineVersion}"
        const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${app.wac.team.buildsrc.Depends.Versions.mockitoKotlinVersion}"
        const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${app.wac.team.buildsrc.Depends.Versions.coroutinesTestVersion}"
        const val mockk = "io.mockk:mockk:${app.wac.team.buildsrc.Depends.Versions.mockkVersion}"
        const val arch_core_testing = "androidx.arch.core:core-testing:${app.wac.team.buildsrc.Depends.Versions.archCoreTestingVersion}"
        const val rx_kotlin = "io.reactivex.rxjava3:rxkotlin:${app.wac.team.buildsrc.Depends.Versions.rxKotlinVersion}"
        const val rx_java = "io.reactivex.rxjava3:rxjava:${app.wac.team.buildsrc.Depends.Versions.rxJavaVersion}"
        const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata:${app.wac.team.buildsrc.Depends.Versions.lifecycleVersion}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${app.wac.team.buildsrc.Depends.Versions.constraintLayoutVersion}"
        const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${app.wac.team.buildsrc.Depends.Versions.navigationVersion}"
        const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${app.wac.team.buildsrc.Depends.Versions.navigationVersion}"
        const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${app.wac.team.buildsrc.Depends.Versions.lifecycleVersion}"
        const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${app.wac.team.buildsrc.Depends.Versions.lifecycleVersion}"
        const val lifecycle_common = "androidx.lifecycle:lifecycle-common:${app.wac.team.buildsrc.Depends.Versions.lifecycleVersion}"
        const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${app.wac.team.buildsrc.Depends.Versions.lifecycleVersion}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${app.wac.team.buildsrc.Depends.Versions.recyclerviewVersion}"
        const val rx_java_android = "io.reactivex.rxjava3:rxandroid:${app.wac.team.buildsrc.Depends.Versions.rxAndroidVersion}"
        const val rx_binding3 = "com.jakewharton.rxbinding4:rxbinding:${app.wac.team.buildsrc.Depends.Versions.rxBinding}"
        const val glide = "com.github.bumptech.glide:glide:${app.wac.team.buildsrc.Depends.Versions.glideVersion}"
        const val glide_compiler = "com.github.bumptech.glide:compiler:${app.wac.team.buildsrc.Depends.Versions.glideVersion}"
        const val lottie = "com.airbnb.android:lottie:${app.wac.team.buildsrc.Depends.Versions.lottieVersion}"
        const val autodispose = "com.uber.autodispose2:autodispose:${app.wac.team.buildsrc.Depends.Versions.autoDispose}"
        const val autodispose_android = "com.uber.autodispose2:autodispose-android:${app.wac.team.buildsrc.Depends.Versions.autoDispose}"
        const val autodispose_android_arch = "com.uber.autodispose2:autodispose-androidx-lifecycle:${app.wac.team.buildsrc.Depends.Versions.autoDispose}"
        const val leak_canary = "com.squareup.leakcanary:leakcanary-android:${app.wac.team.buildsrc.Depends.Versions.leakCanaryVersion}"
        const val chucker = "com.github.chuckerteam.chucker:library:${app.wac.team.buildsrc.Depends.Versions.chuckerVersion}"
        const val chucker_no_op = "com.github.chuckerteam.chucker:library-no-op:${app.wac.team.buildsrc.Depends.Versions.chuckerVersion}"
        const val dataStore_preferences = "androidx.datastore:datastore-preferences:${app.wac.team.buildsrc.Depends.Versions.dataStorePreferenceVersion}"
        const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${app.wac.team.buildsrc.Depends.Versions.coroutinesAndroidVersion}"
    }

    object Environments {
        const val debugBaseUrl = "https://api.punkapi.com/v2/"
        const val releaseBaseUrl = "https://api.punkapi.com/v2/"
    }

    fun generateVersionName(): String {
        val patch: Int = appVersionCode.rem(1000)
        val minor: Int = (appVersionCode / 1000).rem(1000)
        val major: Int = (appVersionCode / 1000000).rem(1000)

        return "$major.$minor.$patch"
    }

}
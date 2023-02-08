import app.wac.team.buildsrc.Depends

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "app.wac.team.wacbase.basemodule"
    compileSdk = Depends.Versions.androidCompileSdkVersion

    defaultConfig {
        minSdk = Depends.Versions.minSdkVersion
        targetSdk = Depends.Versions.targetSdkVersion

        testInstrumentationRunner = Depends.Libraries.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }
    sourceSets {
        named("main") {
            java.srcDir("src/${this.name}/kotlin")
            res.srcDir("src/${this.name}/res/dimens")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    lint {
        lint.abortOnError = false
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}

dependencies {
    implementation(Depends.Libraries.kotlin)
    implementation(Depends.Libraries.android_core_ktx)
    implementation(Depends.Libraries.appcompat)
    implementation(Depends.Libraries.material)
    implementation(Depends.Libraries.timber)

    implementation(Depends.Libraries.coroutines_android)

    implementation(Depends.Libraries.lifecycle_viewmodel_ktx)
    implementation(Depends.Libraries.android_core_ktx)
    implementation(Depends.Libraries.fragment_ktx)
    implementation(Depends.Libraries.appcompat)
    implementation(Depends.Libraries.rx_java)
    implementation(Depends.Libraries.rx_java_android)
    implementation(Depends.Libraries.gson)
    implementation(Depends.Libraries.paging_runtime_ktx)
    implementation(Depends.Libraries.constraintlayout)

    testImplementation(Depends.Libraries.junit)
    androidTestImplementation(Depends.Libraries.test_runner)
    androidTestImplementation(Depends.Libraries.test_ext_junit)
    androidTestImplementation(Depends.Libraries.espresso_core)
}
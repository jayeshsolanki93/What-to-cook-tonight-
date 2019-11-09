private object Versions {
    const val kotlin = "1.3.30"

    const val android_kotlin_plugin = "1.3.50"
    const val android_gradle_plugin = "3.5.2"

    const val coroutines = "1.3.2"

    const val appcompat = "1.0.2"
    const val constraint_layout = "1.1.3"
    const val recyclerview = "1.0.0"

    const val androidx_lifecycle = "2.2.0-alpha05"

    const val dagger = "2.24"
    const val gson = "2.8.1"
    const val okhttp = "4.2.0"
    const val retrofit = "2.6.2"
    const val glide = "4.10.0"

    const val timber = "4.7.1"

    const val junit = "4.12"
    const val espresso = "3.2.0"

    const val stetho = "1.5.1"
    const val leakcanary = "2.0-beta-3"

    const val ktlint = "0.24.0"
}

@Suppress("unused")
object Libs {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val android_kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.android_kotlin_plugin}"
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"

    const val androidx_lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_convertor_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val glide_okhttp_integration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val junit = "junit:junit:${Versions.junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val stetho_okhttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"

    const val ktlint = "com.github.shyiko:ktlint:${Versions.ktlint}"
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("com.android.tools.build:gradle:4.2.0-alpha13")
        classpath("org.koin:koin-gradle-plugin:2.2.0-rc-1")
    }
}
group = "com.example.marvelcomicsviewer"
version = "1.0-SNAPSHOT"



repositories {
    mavenCentral()
}

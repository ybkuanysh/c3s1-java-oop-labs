plugins {
    java
    application
    id("org.javamodularity.moduleplugin") version "2.0.1" apply false
    id("org.openjfx.javafxplugin") version "0.1.0" apply false
    id("org.beryx.jlink") version "4.1.1" apply false
}

allprojects {
    group = "com.ybkuanysh"

    repositories {
        mavenCentral()
    }
}
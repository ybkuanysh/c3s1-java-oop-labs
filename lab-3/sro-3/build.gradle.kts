plugins {
    java
    application
    id("org.javamodularity.moduleplugin")
    id("org.openjfx.javafxplugin")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("com.ybkuanysh.lab3.sro3")
    mainClass.set("com.ybkuanysh.lab3.sro3.Main")
    applicationDefaultJvmArgs = listOf("--enable-native-access=javafx.graphics")
}

javafx {
    version = "25.0.4"
    modules = listOf("javafx.controls")
}

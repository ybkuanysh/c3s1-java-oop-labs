plugins {
    java
    application
    id("org.javamodularity.moduleplugin")
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
    mainModule.set("com.ybkuanysh.lab4.task1")
    mainClass.set("com.ybkuanysh.lab4.task1.Main")
}

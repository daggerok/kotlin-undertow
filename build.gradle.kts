plugins {
    java
    application
}

application {
    mainClassName = "App"
}

dependencies {
    implementation("io.undertow:undertow-core:2.0.1.Final")
    implementation("com.google.code.gson:gson:2.8.5")
}

repositories {
    jcenter()
}

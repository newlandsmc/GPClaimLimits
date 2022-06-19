import java.io.ByteArrayOutputStream

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "com.semivanilla"
version = "1.0-SNAPSHOT"

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19-R0.1-SNAPSHOT") // Paper
    compileOnly("com.github.TechFortress:GriefPrevention:16.18")
}

bukkit {
    name = rootProject.name
    main = "$group.${rootProject.name}"
    version = "${rootProject.version}-${gitCommit()}"
    apiVersion = "1.19"
    website = "https://github.com/SemiVanilla-MC/${rootProject.name}"
    authors = listOf("SemiVanilla-MC")
    depend = listOf("GriefPrevention")
}

fun gitCommit(): String {
    val os = ByteArrayOutputStream()
    project.exec {
        commandLine = "git rev-parse --short HEAD".split(" ")
        standardOutput = os
    }
    return String(os.toByteArray()).trim()
}
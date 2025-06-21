plugins {
    kotlin("jvm") version "2.1.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    // Solo usar si realmente necesitas kotlin-sql, y si tienes token GitHub
    // maven("https://maven.pkg.github.com/plilja/kotlin-sql") {
    //     credentials {
    //         username = "your-username"
    //         password = "your-token"
    //     }
    // }
}

dependencies {
    implementation("io.javalin:javalin:6.3.0")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

    /* Web dependencies */
    implementation("org.webjars.npm:vue:3.5.4")
    implementation("org.webjars.npm:vuetify:3.7.0")
    implementation("org.webjars.npm:mdi__font:7.2.96")
    implementation("org.webjars:font-awesome:6.5.2")
    implementation("org.webjars.npm:roboto-fontface:0.10.0")

    implementation("com.zaxxer:HikariCP:6.0.0")
    // ❌ kotlin-sql requiere autenticación GitHub, considera Exposed o eliminarlo
    // implementation("se.plilja:kotlin-sql:0.5.1")
    implementation("com.github.seratch:kotliquery:1.9.1") // Activa en JitPack
    implementation("org.firebirdsql.jdbc:jaybird:5.0.5.java11")

}

tasks.test {
    useJUnitPlatform()
}

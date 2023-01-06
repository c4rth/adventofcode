dependencies {
    implementation(project(":common"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    api(platform("org.junit:junit-bom:5.9.1"))
    api("org.junit.jupiter:junit-jupiter")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    implementation("org.slf4j:slf4j-simple:2.0.6")
}
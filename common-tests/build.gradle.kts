dependencies {
    implementation(project(":common"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation(kotlin("test"))
    api("org.junit.jupiter:junit-jupiter:5.10.1")
}
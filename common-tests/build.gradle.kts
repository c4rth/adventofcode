dependencies {
    implementation(project(":common"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    api(platform("org.junit:junit-bom:5.10.1"))
    api("org.junit.jupiter:junit-jupiter")
    runtimeOnly("org.junit.platform:junit-platform-launcher")
    api("org.jetbrains.kotlin:kotlin-test:2.0.21")
    api("org.junit.jupiter:junit-jupiter:5.10.1")
}
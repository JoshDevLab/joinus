dependencies {
    implementation ("org.springframework:spring-context")
    implementation ("org.springframework:spring-tx")

    testImplementation(project(":storage:db-core"))
}

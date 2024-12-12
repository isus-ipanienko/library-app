load("@rules_spring//springboot:springboot.bzl", "springboot")

java_library(
    name = "core",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**"]),
    deps = [
        "@maven//:org_springframework_boot_spring_boot",
        "@maven//:org_springframework_boot_spring_boot_actuator",
        "@maven//:org_springframework_boot_spring_boot_actuator_autoconfigure",
        "@maven//:org_springframework_boot_spring_boot_autoconfigure",
        "@maven//:org_springframework_boot_spring_boot_loader",
        "@maven//:org_springframework_boot_spring_boot_starter",
        "@maven//:org_springframework_boot_spring_boot_starter_logging",
        "@maven//:org_springframework_spring_aop",
        "@maven//:org_springframework_spring_beans",
        "@maven//:org_springframework_spring_context",
        "@maven//:org_springframework_spring_core",
        "@maven//:org_springframework_spring_expression",
        "@maven//:org_springframework_spring_web",
        "@maven//:org_springframework_boot_spring_boot_starter_jetty",
        "@maven//:org_springframework_boot_spring_boot_starter_web",
        "@maven//:org_springframework_boot_spring_boot_starter_thymeleaf",
        "@maven//:org_springframework_boot_spring_boot_loader_tools",
        "@maven//:org_springframework_spring_webmvc",
    ],
)

springboot(
    name = "app",
    boot_app_class = "com.merito.app.AppApplication",
    java_library = ":core",
    boot_launcher_class = "org.springframework.boot.loader.launch.JarLauncher",
)

java_test(
    name = "SampleRestFuncTest",
    srcs = ["src/test/java/com/merito/app/AppApplicationTests.java"],
    resources = glob(["src/test/resources/**"]),
    deps = [
        ":helloworld_lib",
        "@maven//:junit_junit",
        "@maven//:org_hamcrest_hamcrest_core",
        "@maven//:org_springframework_spring_beans",
        "@maven//:org_springframework_boot_spring_boot_test",
        "@maven//:org_springframework_spring_test",
    ],
)

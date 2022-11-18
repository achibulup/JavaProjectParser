mvn clean compile assembly:single -Dorg.slf4j.simpleLogger.defaultLogLevel=warn && rm cli/executable.jar -f && cp target/java-project-parser-1.0-SNAPSHOT-jar-with-dependencies.jar cli/executable.jar

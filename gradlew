#!/usr/bin/env sh

DIR="$(cd "$(dirname "$0")" && pwd)"

CLASSPATH="$DIR/gradle/wrapper/gradle-wrapper.jar"

JAVA_CMD="java"

exec "$JAVA_CMD" -Xmx64m -Xms64m \
  -classpath "$CLASSPATH" \
  org.gradle.wrapper.GradleWrapperMain "$@"

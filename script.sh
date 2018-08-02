#!/bin/sh
cd $TRAVIS_BUILD_DIR/gradle/task3
chmod 777 gradlew
./gradlew check

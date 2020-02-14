# Sample tests

This repo hosts the some example test code in java

## Set up test environment

These information should be in .zprofile

### JAVA
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

### Android Home
export ANDROID_HOME=/Users/pchien/Library/Android/sdk
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
export PATH=$ANDROID_HOME/tools/bin:$PATH

### Environment variables

source ~/.zprofile

## To run test

mvn -Pprofile-id clean test

## To start grid hub

java -jar /path/to/selenium-server-standalone.jar -role hub

## To register appium node to selenium grid hub

appium -p 4723 --nodeconfig ./src/test/resources/nodeconfig-android.json
appium -p 4733 --nodeconfig ./src/test/resources/nodeconfig-android.json
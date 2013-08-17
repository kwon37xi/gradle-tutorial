# Gradle Tutorial

## 시작하기

* http://www.gradle.org/
* gradle 1.7 다운로드
* 설치하기

```
export GRADLE_HOME=/home/kwon37xi/java/gradle
export PATH=$PATH:$GRADLE_HOME/bin

gradle -v
gradle tasks
```

* 프로젝트 만들어보기

```
mkdir gradle-tutorial
cd gradle-tutorial
gradle setupBuild --type java-library
```

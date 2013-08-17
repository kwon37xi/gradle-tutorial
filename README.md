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

## Java 클래스를 작성하고 컴파일 해 보기

* Java 기본 소스 디렉토리 구조를 사용한다.
```
java -cp build/classes/main/ Library
```

## Java 클래스를 Gradle로 실행해 보며 Task 추가해보기

```
./gradlew build
./gradlew tasks
./gradlew runLibrary
```

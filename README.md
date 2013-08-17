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

## Java 클래스의 테스트를 만들고 실행해보기

* Library.java에 add 메소드를 추가한다.
* 먼저 일부러 Library.java에서 틀린 메소드로 만들어 본다.

```
./gradlew test
```

* 다시 잘 작동하는 테스트로 고치고 실행해본다.
* 단일 테스트 클래스 실행

```
./gradlew -Dtest.single=LibraryTest test
```

## Java 설정 튜닝

* build.gradle 파일에서 Java 관련 설정을 해본다.

```groovy
ext {
  javaVersion='1.7'
}

buildDir = 'build' // 원래 기본값

sourceCompatibility = javaVersion
targetCompatibility = javaVersion
[compileJava, compileTestJava]*.options*.encoding = 'UTF-8'
```

* 프로젝트 기본 레이아웃
** src/main/java : 실행 자바 소스
** src/main/resources : 실행 리소스
** src/test/java : 테스트 자바 소스
** src/test/resources : 테스트 리소스
** src/소스셋/java : 특정 소스 셋의 Java 소스
** src/소스셋/resources : '특정 소스 셋의 리소스

## Eclipse 설정 파일 생성하기

* eclipse 플러그인을 지정하고,
* Eclipse에서 프로젝트를 열어 본다.
* External Tools를 통해 gradlew eclipse를 실행할 수 있도록 한다.

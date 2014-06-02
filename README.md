# Gradle Tutorial

* 이 문서를 좀 더 발전시킨 [Rocking the gradle](https://github.com/ihoneymon/rocking-the-gradle) 참조.

## Tutorial 따라하기

```
git clone https://github.com/kwon37xi/gradle-tutorial.git
git reset --hard step_01
# 이후 git reset --hard step_xx 숫자를 높여가며 실행해 본다.
# 최종으로 복귀하려면 git reset --hard origin/master 수행
````

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
  * src/main/java : 실행 자바 소스
  * src/main/resources : 실행 리소스
  * src/test/java : 테스트 자바 소스
  * src/test/resources : 테스트 리소스
  * src/소스셋/java : 특정 소스 셋의 Java 소스
  * src/소스셋/resources : '특정 소스 셋의 리소스

## Eclipse 설정 파일 생성하기

* eclipse 플러그인을 지정하고,
* Eclipse에서 프로젝트를 열어 본다.
* External Tools를 통해 gradlew eclipse를 실행할 수 있도록 한다.

## 멀티 프로젝트 전환하고 웹 프로젝트를 구성해본다

* Java Library와 Web으로 구성된 멀티 프로젝트로 전환한다.

```
mkdir tutorial
cd tutorial
# 필요한 파일들을 이동한다.
git mv build.gradle src tutorial/

mkdir tutorial-web
cd tutorial-web
gradle setupBuild --type java-library
rm settings.gradle
rm gradlew*
rm -rf gradle
mkdir -p src/main/webapp/WEB-INF # webapp 기본디렉토리

# tutorial-web/build.gradlejc war 플러그인 추가
apply plugin: 'war'

# providedCompile로 서블릿 라이브러리를 추가해본다.
providedCompile "javax.servlet:javax.servlet-api:3.0.1"

# settings.gradle에 tutorial, tutorial-web 추가한다.

# 모든 build.gradle에 apply plugin: 'eclipse-wtp' 적용
- 일종의 gradle 버그?

# tutorial-web/build.gradle 에 wtp 설정 넣기

# Eclipse에서 프로젝트를 삭제하고, .classpath, .project, .settings도 삭제하고 general 프로젝트로 다시 import.

gradlew eclipse
```

* Search for nested project로 tutorial과 tutorial-web 프로젝트 import

* 서블릿을 생성해본다.
  * gradletutorial.servlet.HomeServlet
  * /WEB-INF/views/home.jsp
* Run on server, http://localhost:8080/home 호출하여 실행한다.

## build.gradle 정리 및 프로젝트 의존성 지정

* 양쪽 프로젝트에 존재하는 build.gradle 중복을 정리하자.

```
subprojects {
...
}
````

* tutorial-web이 tutorial에 의존하고, tutorial에 있는 클래스의 특정 값을 웹 페이지로 출력하자(이를 하기전에 먼저 Eclipse에서 tutorial-web의 의존성에 tutorial 프로젝트가 없음을 확인한다).

```
compile project(':tutorial')
```

## Logback을 설정해보자

* List로 의존성 묶음 지정
* 전역 depenency exclude.
  * 전역 exclude는 subprojects {} 에 지정해야만 모든 프로젝트에서 exclude가 된다.

## Gradle Wrapper 설정과 명령행 실행

* Gradle Wrapper를 사용하여 모든 프로그래머들에게 동일한 Gradle 버전을 설치과정 없이 공급할 수 있다.
* gradlew, gradlew.bat 스크립트를 편집하여 동일한 설정을 적용할 수 있다.

* 기본적으로 태스크는 현재 디렉토리가 속한 프로젝트를 기준으로 실행한다.
* 최상위 프로젝트에서 실행시 특정 태스크를 실행하면 하위 프로젝트에 대해서도 해당 태스크가 존재하면 실행한다.
* 특정 프로젝트 지정하여 태스크를 실행하려면 :project-name:taskname 으로 실행한다.

```
gradlew :tutorial:dependencies
```

* 명령행 몇가지
  * -q : 조용히 실행.
  * -i, -d : info 레벨, debug 레벨 실행
  * -recompile-scripts : build.gradle 재컴파일

## 참고자료

* http://wiki.kwonnam.pe.kr/gradle
* http://www.gradle.org/
* https://github.com/SpringSource/gradle-plugins/tree/master/propdeps-plugin : provided, optional 지원

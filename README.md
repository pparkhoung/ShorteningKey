Shortening Service

URL 을 입력받아 짧게 줄여주고, Shortening 된 URL 을 입력하면 원래 URL 로 리다이렉트하는 URL
예) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro

#요구사항

* webapp 으로 개발하고 URL 입력폼 제공 및 결과 출력
* URL Shortening Key 는 8 Character 이내로 생성되어야 합니다.
* 동일한 URL 에 대한 요청은 동일한 Shortening Key 로 응답해야 합니다.
* Shortening 된 URL 을 요청받으면 원래 URL 로 리다이렉트 합니다.
* Shortening Key 생성 알고리즘은 직접 구현해야 합니다. 
* Database 미사용


#문제해결전략

화면을 통해 입력받는 OriginaUrl의 입력이력 체크(valueMap 확인)
입력이력 존재 시 먼저 만들어진 Key 값 리턴
입력이력 미존재 시 새로운 키 생성 keyMap, valueMap HashMap 변수에 Key/OriginaUrl 값 저장
Database 미사용 대신 HashMap 사용 keyMap, valueMap HashMap 변수에 Key/OriginaUrl 값 저장
Key 생성 시 숫자,영문 대소문자 사용사용하기 위해 arrChars 배열에 0~61 숫자 부터 영문자 대소문자 배열에 저장
URL Shortening Key 는 8 Character 이내로 생성 조건으로 Random.class 이용 0~61까지 랜덤하게 조회 후 조회된 값으로 arrChars 값 추출
keyMap 체크하영 중복되지 않은 8자리 수 Key 생성 
생성된 Key 값을 화면에 보여주고 화면에 보여지는 shortUrl 값을 입력 시 원래 OriginaUrl 호출 화면이동


#프로젝트 빌드 및 실행방법

- 프로젝트 환경 구성
Tool : spring-tool-suite-3.9.6.RELEASE-e4.9.0-win32
Java : java 1.8
프로젝트 : Spring Legacy Project(Spring MVC Project)
was : Tomcat 8.0

- 프로젝트 빌드
프로젝트 환경구성 호환하는 버전을 사용(프로젝트 환경 구성 참조)

프로젝트 import 방법
1. URL : https://github.com/pparkhoung/ShorteningKey 을 통해 프로젝트 직접 다운로드 후 프로젝트 추가 
2. STS내 window > Show View > Other > Git > Git Repositores > Clone a Git repository > Clone URI 선택
Password : 회신 메일에 입력
github에 있는 프로젝트를 내려받을 로컬 포덜을 지정 후 Working Tree > ShorteningKey 프로젝트 import projects 클릭하면 해당 STS내 프로젝트 세팅

- 실행방법
STS 내 서버 tomcat 추가 후 ShorteningKey add 
tomcat 서버 Modules의 Path 경로 최초 세팅 시 /main 을 Edit해 Path 경로 모두 삭제
서버 구동 후 http://localhost:8080/insertURL.do 접근 시 url 입력 화면 호출(포트는 필요 시 로컬 환경에 세팅된 포트로 변경)

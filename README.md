# Springboot Security, JWT 실습

## Day 1 (11/28 월)
- 회원 가입 API 구현
  - username, password, email 입력받아 등록
  - username, email 중복 시 에러 발생
  - @RestControllAdvice를 사용한 ExceptionManager 추가
  - RuntimeException을 상속받은 CustomizedException 추가
  - User Controller Test Code 추가

## Day 2 (11/29 화)
- Spring Security 적용
  - spring-boot-starter-security 라이브러리 설치
  - Security Config 추가
  - BCryptPasswordEncoder 추가
  - 회원가입 시 password가 암호화되서 DB에 등록됨
  - Security Test 코드 구현
  - 로그인 성공 시 JWT Token 발행

## Day 3 (12/05 월)
- Filter을 사용해 login, join을 제외한 POST 요청 차단
- Header(Authorization)에서 JWT Token 추출
- 추출한 JWT Token 만료시간 체크
- 추출한 JWT Token에서 username 추출
- 추출한 username으로 User 객체 불러오기
- Controller에서 Authentication 접근

## Day 4 (12/07 수)
- Hospital, User, Visit 예제
- ERD
![](https://user-images.githubusercontent.com/87286719/206073709-f0c73b58-41bc-4c31-a00d-410041f4f1d7.png)
- Visit 등록 기능 구현
  - JwtToken 인증 성공 시 등록 가능
  - Hospital Id는 직접 입력 받음
- 전체 Visit 조회 기능 구현
- 특정 User가 작성한 Visit 전체 조회 기능 구현
- 특정 Hospital에 추가된 Visit 전체 조회 기능 구현
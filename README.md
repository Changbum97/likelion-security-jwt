# Springboot Security, JWT 실습

## Day 1 (11/28 월)

- 회원 가입 API 구현
  - username, password, email 입력받아 등록
  - username, email 중복 시 에러 발생
  - @RestControllAdvice를 사용한 ExceptionManager 추가
  - RuntimeException을 상속받은 CustomizedException 추가
  - User Controller Test Code 추가
  
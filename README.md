# jwt-practice
jwt 토큰, Spring security로 API요청 제한 설정

# Spring Security 아키텍쳐 설명
<img width="80%" src="https://user-images.githubusercontent.com/77635421/135835456-10e6655d-7b65-4b0e-91dc-d84128b64650.png"/>


#### 일단 모든 요청 시에 Filter를 거치게 됨.
#### 여러 거지 Filter가 있으며
#### Filter Chain 형식으로 다 연결있고, 거치게 되는 순서가 있음.
#### 따로 설정해주지 않으면 디폴트로 되어있는 filter들이 음
#### FilterSecuryInterCeptor는 앞에서 지나온 filter들에 대한 정보가 포함되어있음.

### 1 AuthenticationManager (인터페이스)
#### : Authentication 객체를 받아 인증하여 성공하였으면, 해당 객체에 상태 저장 하고 객체를 반환하는 메소드.
#### :isAuthentication(boolean)로 까보면 값이 true가 되어있게 됨

### 2 ProvdierManager
#### :위 1. AuthenticationManager의 구현체.

### 3 UsernamePasswordAuthentiationToken 
#### :Authentication 인터페이스의 구현체

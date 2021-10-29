# jwt-practice
jwt, Spring security로 API요청 제한 설정

# 1. Jwt로 인증, 인가 적용하기 위한 이론
#   1-(1) Jwt 설명
<img width="80%" src="https://user-images.githubusercontent.com/77635421/135846233-0542ad3e-bba9-42ce-86b1-d29303641cbf.JPG"/>

<img width="80%" src="https://user-images.githubusercontent.com/77635421/135844390-263a6b40-572c-4a3c-a918-a2e4cb6dd5e9.JPG"/>

##### [예약된 clainm 전체 리스트](https://www.iana.org/assignments/jwt/jwt.xhtml)
##### 암호화할 때 키는 대칭키, 비대칭키 모두 이용 가능하다.
##### [추가 JWT 스펙 알고리즘](https://tools.ietf.org/html/rfc75183section-3)
##### 컴네 수업 참조할 필요 有
##### 수업 중 보안 부분 4가지 경우에서 어떻게 보면 벗어나는 케이스
##### JWT 토큰의 경우 암호화 시에 Hash함수도 같이 적용.
##### 하지만 Plain Text를 보내지 않기 때문에 case3의 Keyed Hash 나 case4의 전자서명 이라고 볼 수는 없음.

#    1-(2) Jwt 토큰을 Access Token, Refresh Token 2개를 이용
<img width="80%" src="https://user-images.githubusercontent.com/77635421/135838629-d065e93b-7550-4f53-a2c4-c56c2b4b5277.JPG"/>

# 2.Spring Security 아키텍쳐 설명
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


<pre><code>

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  (각종 configure() 메소드 구현)
  
}
</code></pre>

1. 각종 요청 별 권한을 설정한다. ( API는 configure(Httpsecurity http) )
2. 내가 만든 Filter, 혹은 각종 구현체들을 여기서 설정한다. ( APi. Custom한 Filter는 configure(HttpSecurity http), UsernameDetailsService같은건 configure(AuthenticationManagerBuilder auth) )

자세한건 API 도큐먼트[https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.html]직접 가서 봐야한다.

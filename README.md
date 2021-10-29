# 스프링 시큐리티와 인증, 인가 공부

## 1. Jwt로 인증, 인가 적용하기 위한 이론
###   1-(1) Jwt 설명
<img width="80%" src="https://user-images.githubusercontent.com/77635421/135846233-0542ad3e-bba9-42ce-86b1-d29303641cbf.JPG"/>

<img width="80%" src="https://user-images.githubusercontent.com/77635421/135844390-263a6b40-572c-4a3c-a918-a2e4cb6dd5e9.JPG"/>

##### [예약된 clainm 전체 리스트](https://www.iana.org/assignments/jwt/jwt.xhtml)
##### 암호화할 때 키는 대칭키, 비대칭키 모두 이용 가능하다.
##### [추가 JWT 스펙 알고리즘](https://tools.ietf.org/html/rfc75183section-3)
##### 컴네 수업 참조할 필요 有
##### 수업 중 보안 부분 4가지 경우에서 어떻게 보면 벗어나는 케이스
##### JWT 토큰의 경우 암호화 시에 Hash함수도 같이 적용.
##### 하지만 Plain Text를 보내지 않기 때문에 case3의 Keyed Hash 나 case4의 전자서명 이라고 볼 수는 없음.

###    1-(2) Jwt 토큰을 Access Token, Refresh Token 2개를 이용
<img width="80%" src="https://user-images.githubusercontent.com/77635421/135838629-d065e93b-7550-4f53-a2c4-c56c2b4b5277.JPG"/>

## 2.Spring Security

### 2-(1) 전반적인 Flow
#### 1. 내 웹 어플리케이션으로 HTTP 요청이 온다.
#### 2. 서블릿 필터부터 거친다. 서블릿에 도달하기 전에
#### 3. 이때 DelegatingFilterProxy 가 여러가지 서블릿 필터 중 하나
#### 4. DelegatingFilterProxy에서는 FilterChainProxy를 찾게된다. 이 때 FilterChainProxy의 Bean이름은 springSecurityFilterChain이다. 여기서 순수 서블릿에서 스프링 시큐리티 모듈로 넘어가게 되는 부분이다.
#### 5. FilterChainProxy에는 디폴트로 15개의 Filter가 존재하며 경우에따라 내가 Filter를 만들어 추가할 수 있다.
#### 6. Filter 가 다 통과되었다면 서블릿에 도달하게 된다.

### 2-(2) 여기에서 각종 설정을 세팅한다.

<pre><code>

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  (각종 configure() 메소드 구현)
  
}
</code></pre>

##### 1. 각종 요청 별 권한을 설정한다. ( API는 configure(Httpsecurity http) )
##### 2. 내가 만든 Filter, 혹은 각종 구현체들을 여기서 설정한다. ( APi. Custom한 Filter는 configure(HttpSecurity http), UsernameDetailsService같은건 configure(AuthenticationManagerBuilder auth) )

###### 자세한건 API 도큐먼트[https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.html] 가서 봐야한다.

### 2-(3) Filter중 UsernamePasswordAuthenticationFilter 설명 
##### 대표적인 Filter 중 하나는 UsernamePasswordAuthenticationFilter  (AbstractAuthenticationProcessingFilter를 상속받았음)
 
 ![제목 없음](https://user-images.githubusercontent.com/77635421/139477044-78a957b7-f5c5-4efd-847a-3805adfb38e2.jpg)

##### 1. 내부에 보면 attemptAuthentication으로 인증과정을 진행.
##### 2. AuthenticationManager에게 전달된다. (실제 구현체는 보통 ProviderManager 아니면 내가 만들 수도)
##### 3. ProviderManager는 AuthenticationProvider에게 위임 (AuthenticationProvider의 실제 구현체 중 하나는 DaoAuthenticationProvider. 이거도 내가 경우에 따라 커스텀 가능)
##### 4. DaoAuthenticationProvider의 경우에는 UserDetails를 검증하고 Authentication객체를 반환한다 (가보면 UserDetailsService의 loadUserByName()을 호출한다.)
##### 5. 이후 SecurityContextHolder에 저장.
 
##### 위의 경우에는 Form기반 로그인이므로 SSR의 경우에 적합할 것으로 보임. 만약 FE와 BE를 분리한다면 문자열만 날아올텐데 다른 방식이 적당해보인다. 
 
 


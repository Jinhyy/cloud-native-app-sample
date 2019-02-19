
# jOpenAPI  
openAPI platform side project  
  
# Hystrix(장애관리)  
  
### Hystrix 주요 환경변수 값(공통적용)  
  
hystrix:  
  command:  
    default:  
      execution:  
        isolation:  
          thread:  
            timeoutInMilliseconds: 3000 # 타임아웃 시간, df:2000  
      circuitBreaker:  
        requestVolumeThreshold: 1 # 타임아웃 호출 개수, df:20  
        errorThresholdPercentage: 50  #타임아웃 퍼센티지, default 50  
        sleepWindowInMilliseconds: 10000 #타임아웃 연장 단위 : default 5000  
          
개별적으로 적용을 원한다면, 메소드에, @HytrixProperty(name,value)를 사용하여 적용한다.   
  
  
#### 1. 캐시 데이터를 사용하여 폴백 구현하는 방법.  
@EnableCaching, @CachePut 등을 사용하여 요청 실패결과를 캐쉬에 저장하여 관리.  
  
#### 2. hystrix  + Turbine 대쉬보드  
@Hystrix-Dashboard(인스턴스별 장애 모니터링)과, 이를 다 묶어 보여주는  
Turbine을 활용하여 통합 모니터링 할 수 있다.  
  
### #3.스트리밍(rabitMQ or kafka) + 터빈  
메트릭 수집을 메시지 브로커를 사용하여 비동기식으로 구현한다.  
  
  
# Client LoadBalancer - Ribbon  
  Client (API Caller) 에 탑재되는 S/W 모듈  
 주어진 서버 목록에 대해서 Load Balancing 을 수행함  
  eureka 와 같이 사용시에, 동적으로 Load Balancing 가능해진다.  
    
###  Ribbobn 의 장점 (단점도 있지만… )  
  H/W 가 필요 없이 S/W 로만 가능 (비용 down, 유연성 up)  
  서버 목록의 동적 변경이 자유로움 (단 Coding 필요)  
  Load Balancing Schema 이 마음대로 구성 가능 (단 Coding 필요)  
    
    
 ### 리본실습 정리  
 - Ribbon 디펜던시 추가 후 RestTemplate에 `@LoadBalanced`  
 - application.yml 설정에 특정 서비스(product)의 주소 설정  
 - RestTemplate 사용 시 주소 넣지 않고 서비스 이름(product) 사용  
  ### Ribbon의 Retry 기능  
  주의  
  - Retry를 시도하다가도 HystrixTimeout이 발생하면, 즉시 에러 반환 리턴할 것이다.  
     (Hystrix로 Ribbon을 감싸서 호출한 상태이기 때문에)  
     - Retry를 끄거나, 재시도 횟수를 0으로 하여도 해당 서버로의 호출이 항상 동일한 비율  
 로. 실패하지는 않는다. (**실패한 서버로의 호출은 특정 시간동안 Skip 되고 그 간격은 조정된  
 다 - BackOff**)  
     - classpath 에 retry 가 존재해야 한다는 점 주의  
  ### Ribbon 정리      
 - Ribbon은 여러 Component에 내장되어있으며, 이를 통해 Client Load Balancing이  
 수행 가능하다.  
     - Ribbon에는 매우 다양한 설정이 가능하다 (서버선택, 실패시 Skip 시간, Ping 체크)  
     - Ribbon에는 Retry기능이 내장 되어있다.  
     - Eureka의 자동으로 서버목록 알아내는 기능과 함께하면 유용하다. (뒤에 실습)
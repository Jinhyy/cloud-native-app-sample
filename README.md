# jOpenAPI
openAPI platform side project

# Hystrix

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
        
개별적으로 적용을 원한다면, 메소드에, @HytrixProperty(name,value)를 사용하여 
적용한다. 

###  장애 관리(Hystrix)
#### 1. 캐시 데이터를 사용하여 폴백 구현하는 방법.
@EnableCaching, @CachePut 등을 사용하여 요청 실패결과를 캐쉬에 저장하여 관리.

#### 2. hystrix  + Turbine 대쉬보드
@Hystrix-Dashboard(인스턴스별 장애 모니터링)과, 이를 다 묶어 보여주는
Turbine을 활용하여 통합 모니터링 할 수 있다.

### 3.스트리밍(rabitMQ or kafka) + 터빈
메트릭 수집을 메시지 브로커를 사용하여 비동기식으로 구현한다.

     
  
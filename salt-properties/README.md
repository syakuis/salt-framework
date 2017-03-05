## salt-properties

프레임워크가 구동되기 위해 필요한 정보를 관리한다.
spring profiles 에 따라 프로퍼티를 변경하여 로드한다.
설정된 순서에 따라 순차적으로 로드되며 동일한 키는 나중에 입력된 키로 사용된다.
최우선 실행되는 spring configuration 이다.


### lifecycle

다른 설정(@Configuration)보다 최우선 실행한다.

1. call SaltPropertiesConfiguration
2. call SaltPropertiesConfigurer
3. call SaltPropertiesFactoryBean
4. call InitializeProperties
5. intro
6. return Properties

### properties

```
classpath*:org/saltframework/config/salt.properties
classpath*:salt.properties
classpath*:salt-%s.properties
```

%s = profiles

profiles = dev, test, prod 혹은 dev1, dev2, dev3 ... 

### key tag

config.* = salt 에서 사용되는 프로퍼티 키이다.

그외 오픈소스 이름을 네임택으로 사용한다.

jpa.* 혹은 datasouces.* 으로 사용하여 중복을 막는 다.


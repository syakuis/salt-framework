## salt-properties

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

### name tag

config.* = salt 에서 사용되는 프로퍼티

그외 오픈소스 이름을 네임택으로 사용한다.


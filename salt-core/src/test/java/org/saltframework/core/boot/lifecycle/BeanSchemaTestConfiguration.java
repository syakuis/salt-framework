package org.saltframework.core.boot.lifecycle;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 17.
 */
@Configuration
@ImportResource(locations = "classpath*:test-context.xml")
public class BeanSchemaTestConfiguration {
}

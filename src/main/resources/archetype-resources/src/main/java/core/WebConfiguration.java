package ${groupId}.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局默认配置类
 */
@Configuration
public class WebConfiguration {

    @Bean
    public StringToResultHttpMessageConverter myConverter(ObjectMapper mapper) {
        return new StringToResultHttpMessageConverter().setMapper(mapper);
    }
}

package ${groupId}.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 全局默认配置类
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer{


    /**
     *  解决controller返回string时报错
     *  由于StringConverter在converters中的位置靠前，导致在controller中直接返回string时，
     *  spring会认为你要返回的是text/plan类型，无法转换为RestBaseResponse类型从而导致报错。
     *  这里的逻辑就是将MappingJackson2HttpMessageConverter的位置靠前，优先将string返回成json类型
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(t -> t.getClass() == MappingJackson2HttpMessageConverter.class)
                .findAny()
                .ifPresent(t->converters.add(0, t));
    }
}

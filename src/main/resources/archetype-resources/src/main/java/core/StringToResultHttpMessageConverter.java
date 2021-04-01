package ${groupId}.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import ${groupId}.model.HttpResult;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 为string类型返回值定制的消息处理器，配合补充。
 * <p>
 * 默认spring对string的处理经过GlobalResultHandler之后会返回text/plain类型的json对象,在这个消息转换器中，专门处理string类型，
 * 给前端正确的application/json响应头。
 * @author Administrator
 */
public class StringToResultHttpMessageConverter extends AbstractHttpMessageConverter<HttpResult> {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private ObjectMapper mapper;

    public StringToResultHttpMessageConverter() {
        this(DEFAULT_CHARSET);
    }

    public StringToResultHttpMessageConverter(Charset charset) {
        super(charset, MediaType.APPLICATION_JSON, MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return String.class == clazz;
    }

    @Override
    protected HttpResult readInternal(Class<? extends HttpResult> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream body = httpInputMessage.getBody();
        byte[] bytes = new byte[body.available()];
        body.read(bytes);
        String str = new String(bytes);
        return mapper.readValue(str, HttpResult.class);
    }

    @Override
    protected void writeInternal(HttpResult httpResult, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Charset charset = getContentTypeCharset(httpOutputMessage.getHeaders().getContentType());
        StreamUtils.copy(mapper.writeValueAsString(httpResult), charset, httpOutputMessage.getBody());
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharset() != null) {
            return contentType.getCharset();
        } else {
            return getDefaultCharset();
        }
    }

    public StringToResultHttpMessageConverter setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
        return this;
    }
}

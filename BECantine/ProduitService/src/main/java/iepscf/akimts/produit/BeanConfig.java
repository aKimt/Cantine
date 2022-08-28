package iepscf.akimts.produit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@ComponentScan({"iepscf.akimts.data.repository", "iepscf.akimts.data.entity"})
@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate builder(RestTemplateBuilder builder){
        return builder.additionalInterceptors(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                log.info(request.getMethod() + " : " + request.getURI());
                return execution.execute(request, body);
            }
        }).build();
    }

}

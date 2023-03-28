import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import javax.annotation.PostConstruct;

@Data
@Configuration
public class ClientConfig {
    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        restTemplate = new RestTemplate();
        DefaultUriBuilderFactory uriTemplateHandler = new DefaultUriBuilderFactory("https://rickandmortyapi.com/api");

        restTemplate.setUriTemplateHandler(uriTemplateHandler);
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

            return execution.execute(request, body);
        });
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
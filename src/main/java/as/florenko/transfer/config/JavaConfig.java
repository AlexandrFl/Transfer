package as.florenko.transfer.config;

import as.florenko.transfer.repository.Repository;
import as.florenko.transfer.service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public Service s() {
        return new Service(r());
    }
    @Bean
    public Repository r() {
        return new Repository();
    }
}

package ynu.jackielinn.redis4.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperties {
    private String password;
    private Sentinel sentinel;

    @Data
    public static class Sentinel {
        private String master;
        private Set<String> nodes;
    }
}
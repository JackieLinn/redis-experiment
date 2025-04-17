package ynu.jackielinn.redis4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;

@Configuration
public class RedisSentinelConfig {

    private final RedisProperties redisProperties;

    public RedisSentinelConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        RedisSentinelConfiguration config = new RedisSentinelConfiguration();
        config.setMaster(redisProperties.getSentinel().getMaster());
        redisProperties.getSentinel().getNodes().forEach(node -> {
            String[] parts = node.split(":");
            config.sentinel(parts[0], Integer.parseInt(parts[1]));
        });
        config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        return config;
    }
}
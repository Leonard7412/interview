package com.harper.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.config
 * @Author: liuhb_mios_ah
 * @CreateTime: 2024-05-16  11:18
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class RedisConfig {
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // 这里根据你的Redis配置进行调整
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("122.51.98.236");
        config.setPort(6379);
        config.setPassword("207412");
        config.setDatabase(0);
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        // 设置序列化器等配置，这里省略了具体的序列化配置
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}

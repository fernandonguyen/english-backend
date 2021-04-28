package com.codegym.englishbackend.basic;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
@EnableCaching
public class EhcacheConfig {
//    @Bean
//    public CacheManager cacheManager() {
//        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean ehCacheCacheManager() {
//        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
//        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
//        factory.setShared(true);
//        return factory;
//    }

    @Bean
    public KeyGenerator multiplyKeyGenerator() {
        return (Object target, Method method, Object... params) -> method.getName() + "_" + Arrays.toString(params);
    }

}

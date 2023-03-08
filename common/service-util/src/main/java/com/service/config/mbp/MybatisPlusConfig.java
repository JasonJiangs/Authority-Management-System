package com.service.config.mbp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Jason
 * @Date: 2023/3/8 11:09 11 09
 */

/*
https://baomidou.com/pages/2976a3/#spring-boot
 */
@MapperScan(value = "com.oa.auth.mapper")
@Configuration
public class MybatisPlusConfig {
    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

//    @Bean
//    public Interceptor mybatisPlusInterceptor(){
//        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
//        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(){
//            @Override
//            protected void handlerLimit(IPage<?> page) {
//                long pageSize = page.getSize();
//                if(pageSize == 0){
//                    page.setSize(page.getTotal());
//                }
//                super.handlerLimit(page);
//            }
//        };
//        paginationInnerInterceptor.setOverflow(true);
//        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
//        return mybatisPlusInterceptor;
//    }

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }

}
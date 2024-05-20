package com.mrgao.xbqx.config;

import com.mrgao.xbqx.interceptor.CustomizedInterceptor;
import lombok.Getter;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Gao
 * @date 2024/4/24 9:53
 * @apiNote:
 */
@Getter
@Configuration
public class AutoConfig {

    //private final Product product;
    //private final Product twoProduct;
    ////
    ////@Bean
    ////public Product twoProduct() {
    ////    return new Product();
    ////}
    //
    //public AutoConfig(ObjectProvider<Product> productObjectProvider, Product twoProduct) {
    //    this.product = productObjectProvider.getIfAvailable();
    //    this.twoProduct = twoProduct;
    //}


    /**
     * 配置(自定义)拦截器
     *
     * @return
     */
    @Bean
    public Interceptor customizedInterceptor() {
        return new CustomizedInterceptor();
    }
}

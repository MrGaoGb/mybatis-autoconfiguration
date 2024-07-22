package com.mrgao.xbqx.service;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Gao
 * @date 2024/7/5 22:40
 * @apiNote:
 */
@Service
public class ProductService implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 判断是否存在
     *
     * @return
     */
    public Integer judgeExist() {
        Boolean exist = environment.getProperty("custom-data.exist", Boolean.class, true);
        if (exist) {
            return 99;
        } else {
            return -1;
        }
    }
}

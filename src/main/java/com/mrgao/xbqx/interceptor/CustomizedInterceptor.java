package com.mrgao.xbqx.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Arrays;

/**
 * @author Mr.Gao
 * @date 2024/5/16 12:04
 * @apiNote:自定义拦截器Interceptor
 */
@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(type = Executor.class,
                method = "commit",
                args = {boolean.class}
        )
})
public class CustomizedInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取方法参数
        Object[] args = invocation.getArgs();
        System.out.println("拦截到 " + invocation.getMethod().getName() + " 执行！方法参数为：" + Arrays.toString(args));
        return invocation.proceed();
    }
}

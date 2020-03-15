package com.mybatis.interepter;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


import java.util.Properties;

@Intercepts(value = {
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class, RowBounds.class,ResultHandler.class})
})
public class MBinterepter implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {


        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

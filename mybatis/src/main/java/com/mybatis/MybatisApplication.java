package com.mybatis;

import com.mybatis.interepter.MBinterepter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Properties;

@MapperScan("com.mybatis.mapper")
@SpringBootApplication
public class MybatisApplication implements CommandLineRunner {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Properties properties = new Properties();
        properties.setProperty("nb", "mybaties");
        MBinterepter interceptor = new MBinterepter();
        interceptor.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}

package com.springboot.springlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Car implements BeanFactoryAware, BeanNameAware, BeanFactoryPostProcessor,InitializingBean, DisposableBean {

    private  CarBody carBody = new CarBody();

    public Car(){
        System.out.println("Car 构造方法。。。。");
    }


    @Override
    public void destroy() throws Exception {

        System.out.println("destroy 对象销毁。。。。");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("afterPropertiesSet bean 初始化完成后");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        System.out.println("setBeanFactory 设置beanfactory");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName 设置beanName");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory 设置listtabfacotry");
    }
}

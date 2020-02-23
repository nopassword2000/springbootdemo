package com.springbeandefinetionregster;


import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Data
public class BeanDefineUser implements BeanPostProcessor {

    String userName;

    String password;

    int age;

    public BeanDefineUser(){
       // System.out.println("user");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {


        if (bean instanceof BeandefineRegster){
            System.out.println("--------------------------------------------------------------------");
        }
        //System.out.println("benas name==================>" + beanName + "name-------------" + bean.getClass().getName() );
        if ("beandefineRegster".equals(beanName)){

            BeandefineRegster beandefineRegster = (BeandefineRegster)bean;
            try {
                for (Field field : beandefineRegster.getClass().getSuperclass().getDeclaredFields()){
                    ReflectionUtils.makeAccessible(field);
                    String fileName = field.getType().getName();
                    String className = BeanDefineUser.class.getName();
                    if (fileName.equals(className)){
                        BeanDefineUser beanDefineUser = new BeanDefineUser();
                        beanDefineUser.setAge(10);
                        beanDefineUser.setUserName("no");
                        beanDefineUser.setPassword("aaaa");
                        ReflectionUtils.setField(field,beandefineRegster,beanDefineUser);
                    }



               }

               /*              ReflectionUtils.setField(getClass().getField("userName"),bean, "nopassword");
                ReflectionUtils.setField(getClass().getField("password"),bean, "123456");
                ReflectionUtils.setField(getClass().getField("age"),bean, 13);*/

            }catch (Exception ex){

                System.out.println("ex" +ex.getMessage());
            }

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("benas name==================>" + beanName + "name-------------" + bean.getClass().getName() );
        return bean;
    }


}

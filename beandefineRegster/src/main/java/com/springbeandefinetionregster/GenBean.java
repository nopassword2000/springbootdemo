package com.springbeandefinetionregster;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class GenBean implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        BeanDefinitionBuilder beanDefinitionBuilder1 = BeanDefinitionBuilder.genericBeanDefinition(BeanDefineUser.class);
        //beanDefinitionBuilder1.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        registry.registerBeanDefinition("BeanDefineUser", beanDefinitionBuilder1.getBeanDefinition());

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyBeanPostProcessor.class);
        registry.registerBeanDefinition("MyBeanPostProcessor", beanDefinitionBuilder.getBeanDefinition());
    }
}

package com.lin.prometheus.prometheus.utils;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring工具类，方便在非spring管理环境中获取bean
 */
public class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {
    /**
     * Spring应用上下文环境
     */
   private static ConfigurableListableBeanFactory beanFactory;
   private static ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringUtils.beanFactory = configurableListableBeanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * 根据name获取bean
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) throws BeansException{
        return (T)applicationContext.getBean(name);
    }

    /**
     * 根据类类型获取bean
     * @param clazz
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException{
        return (T)applicationContext.getBean(clazz);
    }

    /**
     * BeanFactory是否有一个与给定名称的bean，有则返回true
     * @param name
     * @return
     */
    public static boolean containsBean(String name){
        return applicationContext.containsBean(name);
    }

    /**
     * 判断给定bean是否一个单例bean
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取给定bean的类型
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException{
        return applicationContext.getType(name);
    }

    /**
     * 获取给定bean名字在bean定义中的所有别名
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException{
        return applicationContext.getAliases(name);
    }

    /**
     * 获取aop代理对象
     * @param invoker
     * @param <T>
     * @return
     */
    public static <T> T getAopProxy(T invoker){
        return (T) AopContext.currentProxy();
    }

    /**
     * 获取当前环境的配置，无则返回null
     * @return
     */
    public static String[] getActiveProfiles(){
        return applicationContext.getEnvironment().getActiveProfiles();
    }

    /**
     * 获取当前环境的配置，若有多个，则返回第一个
     * @return
     */
    public static String getActiveProfile(){
        final String[] activeProfiles = getActiveProfiles();
        return activeProfiles == null || activeProfiles.length == 0 ? null : activeProfiles[0];
    }
}

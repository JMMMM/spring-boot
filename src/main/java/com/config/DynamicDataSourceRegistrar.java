package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dbconfig.DynamicDataSourceContextHolder;
import com.dbconfig.MultipleDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private DruidDataSource defaultDataSource;

    private Map<String, DataSource> otherDataSources = new HashMap<>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MultipleDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", otherDataSources);
        registry.registerBeanDefinition("multipleDataSource", beanDefinition);
    }

    @Override
    public void setEnvironment(Environment environment) {
        try {
            DataSource master = initDefaultDataSource(environment);
            DataSource slave = initOtherDataSource(environment);
            otherDataSources.put("master", master);
            otherDataSources.put("slave", slave);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DataSource initOtherDataSource(Environment environment) throws SQLException {
        Binder binder = Binder.get(environment);
        Map<String, String> bs = binder.bind("spring.db", Map.class).get();
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(bs.get("slave.url"));
        druidDataSource.setUsername(bs.get("slave.userName"));
        druidDataSource.setPassword(bs.get("slave.passwd"));
        otherSettings(druidDataSource, environment);
        druidDataSource.init();
        otherDataSources.put("slave", druidDataSource);
        return druidDataSource;
    }

    private DataSource initDefaultDataSource(Environment environment) throws SQLException {
        Binder binder = Binder.get(environment);
        Map<String, String> bs = binder.bind("spring.db", Map.class).get();
        defaultDataSource = new DruidDataSource();
        defaultDataSource.setUrl(bs.get("default.url"));
        defaultDataSource.setUsername(bs.get("default.userName"));
        defaultDataSource.setPassword(bs.get("default.passwd"));
        otherSettings(defaultDataSource, environment);
        defaultDataSource.init();
        return defaultDataSource;
    }

    private DruidDataSource otherSettings(DruidDataSource druidDataSource, Environment environment) {
        Binder binder = Binder.get(environment);
        Map<String, String> bs = binder.bind("spring.db", Map.class).get();
        druidDataSource.setDriverClassName(bs.get("driverClassName"));
        return druidDataSource;
    }

}

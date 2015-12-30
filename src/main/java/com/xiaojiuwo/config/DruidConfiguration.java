package com.xiaojiuwo.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.xiaojiuwo.dao.MyBatisGeneralDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


import java.sql.SQLException;


@Configuration
public class DruidConfiguration{

    @Autowired
    private DruidDataSource druidDataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(druidDataSource);
        sqlSessionFactory.setConfigLocation(new ClassPathResource("config/mybatis/mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        Resource mapperResource = new ClassPathResource("config/mybatis/mappers/areas_mapper.xml");
        sqlSessionFactory.setMapperLocations(new Resource[]{mapperResource});
        //sqlSessionFactory.setTypeHandlersPackage("org.horiga.study.mybatis.typehandler");

        return sqlSessionFactory.getObject();
    }

    @Bean(name="myBatisGeneralDao")
    public MyBatisGeneralDao setMyBatisSeneralDao() throws Exception {
        MyBatisGeneralDao myBatisGeneralDao = new MyBatisGeneralDao();
        myBatisGeneralDao.setSqlSessionFactory(sqlSessionFactory);

        return myBatisGeneralDao;
    }


//    @Bean
//    ServletContextTemplateResolver templateResolver(){
//        ServletContextTemplateResolver resolver=new ServletContextTemplateResolver();
//        resolver.setSuffix(".html");
//        resolver.setPrefix("/resources/templates/");
//        resolver.setTemplateMode("HTML5");
//        return resolver;
//    }
//
//    @Bean
//    SpringTemplateEngine engine(){
//        SpringTemplateEngine engine=new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver());
//        return engine;
//    }
//
//    @Bean
//    ThymeleafViewResolver viewResolver(){
//        ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(engine());
//        return viewResolver;
//    }
}
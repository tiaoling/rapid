package com.xiaojiuwo.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.xiaojiuwo.repositories.MyBatisGeneralRepository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(basePackages = {
        "com.xiaojiuwo.repositories"
})
@EnableTransactionManagement

public class DataBaseConfiguration {

    @Autowired
    private  JpaConfiguration jpaConfiguration;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(druidDataSource());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("config/mybatis/mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        Resource mapperResource = new ClassPathResource("config/mybatis/mappers/areas_mapper.xml");
        sqlSessionFactory.setMapperLocations(new Resource[]{mapperResource});
        //sqlSessionFactory.setTypeHandlersPackage("org.horiga.study.mybatis.typehandler");

        return sqlSessionFactory.getObject();
    }

    @Bean(name="myBatisGeneralRepository")
    public MyBatisGeneralRepository setMyBatisSeneralDao() throws Exception {
        MyBatisGeneralRepository myBatisGeneralRepository = new MyBatisGeneralRepository();
        myBatisGeneralRepository.setSqlSessionFactory(sqlSessionFactory());

        return myBatisGeneralRepository;
    }

//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager()  throws Exception{
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(druidDataSource());
//        return dataSourceTransactionManager;
//    }


    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource
                                                                ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.xiaojiuwo.models");

        Properties jpaProperties = new Properties();

        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", jpaConfiguration.getHibernate().getDialect());

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto",
                jpaConfiguration.getHibernate().getDdl_auto()
        );

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements


        jpaProperties.put("hibernate.ejb.naming_strategy",
                jpaConfiguration.getHibernate().getNaming_strategy()
        );

        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql",
                jpaConfiguration.getHibernate().getShow_sql()
        );

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql",
                jpaConfiguration.getHibernate().getShow_sql()
        );

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }


}
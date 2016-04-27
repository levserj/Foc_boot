package com.levserj;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Serhii Levchynskyi on 18.04.2016.
 */
@SpringBootApplication
public class Application {


    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    @Value("${spring.mvc.view.suffix}")
    private String suffix;

    @Bean
    public ViewResolver ViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        /*iewResolver.setViewClass(JstlView.class);*/
        viewResolver.setPrefix(prefix);
        viewResolver.setSuffix(suffix);
        return viewResolver;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

/*    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
*//*        sessionFactory.setPackagesToScan(new String[] { "org.baeldung.spring.persistence.model" });*//*
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource restDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(*//*env.getProperty("jdbc.driverClassName")*//*"com.mysql.jdbc.Driver");
        dataSource.setUrl(*//*env.getProperty("jdbc.url")*//*"jdbc:mysql://localhost/test");
        dataSource.setUsername(*//*env.getProperty("jdbc.user")*//*"root");
        dataSource.setPassword(*//*env.getProperty("jdbc.pass")*//*"root");

        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", *//*env.getProperty("hibernate.hbm2ddl.auto")*//*"true");
                setProperty("hibernate.dialect", *//*env.getProperty("hibernate.dialect")*//*"org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }*/


}

package com.zerob.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 2019-04-01 - jasonLee
 * JPA 설정
 * @author leejason
 * Annotation 정의
 * Configuration: Spring 환경 Class 로 지정
 * EnableTrasactionManagement: 트랜젝션을 사용하겠다는 어노테이션으로 xml버전의 <tx:annotation-driven /> 설정과 동일하다.
 * EnableJpaRepositories: JPA로 구현될 class가 존재하는 package를 지정하는 어노테이션
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "primaryEntityManagerFactory",
		transactionManagerRef = "primaryTransactionManager",
		basePackages = {"com.zerob.repository.primary"}
)
public class PrimaryJpaConfig {
    
    /**
     * 2019-04-01 - jasonLee
     * jpa와 연결할 datasource의 구현체 bean을 설정
     * 해당 시스템은 hikariCP를 이용하여 connectionPool을 구현 후 해당 hikariCP를 datasource로 사용
     * @return hikariCP config
     */
    @Bean(name = "primaryHikariConfig")
    @Primary
    public HikariConfig primaryHikariConfig() {
        
        /**
         * 반환될 HikariCPConfig Class
         */
        HikariConfig config = new HikariConfig();
        /**
         * setConnectionTestQuery: DB가 정상적으로 연결시 연결 확인을 위한 확인용 실행 쿼리
         * setDataSourceClassName: 실제 연결에 필요한 Oracle Driver
         * setMaximumPoolSize: Connection Pool의 최대 갯수
         * setMinimumIdle: Connection Pool의 최소 갯수
         * setIdleTimeout: TimeoutConnection의 유지시간
         * setDataSourceProperties: datasource에 필요한 properties를 Properties 타입으로 입력받는다.
         */
        
        config.setConnectionTestQuery("SELECT 1 FROM DUAL");
        config.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setDataSourceProperties(addPrimaryDataSourceProperties());
        
        return config;
    }
    
    /**
     * 2019-04-01 - jasonLee
     * dataSource 구현체 설정
     * hikariCP Config 를 가져와 사용한다.
     * @return
     */
    @Bean(name = "primaryDataSource")
    @Primary
    public HikariDataSource primaryDataSource() {
        /**
         * 일반적인 dataSource와 다르게 HikariCP를 사용시 HikariCP의 ConnectionPool 이 지원되는 dataSource를 사용한다.
         */
        return new HikariDataSource(primaryHikariConfig());
    }
    
    /**
     * TODO: (2019-03-27) 사용유무 판단 해야함
     * @param emf
     * @return
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        
        return transactionManager;
    }
    
    /**
     * TODO: (2019-03-27) 사용유무 판단 해야함
     * @return
     */
    @Bean(name = "primaryExceptionTranslation")
    @Primary
    public PersistenceExceptionTranslationPostProcessor primaryExceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    /**
     * 2019-04-01 - jasonLee
     * JPA에서 사용할 Entity와 물리적으로 사용할 데이터베이스 매핑
     * Entity(VO)를 물리적 데이터베이스에 맞춰 작성하지 않으면 데이터가 메모리에 적재되지 않는다.
     * @return
     */
    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        
        /**
         * 현재 서버에서 사용할 Entity와 연결될 DataSource 연결
         */
        factory.setDataSource(primaryDataSource());
        factory.setPersistenceUnitName("primaryPersistenceUnit");
        /**
         * Entity의 scan이며 해당 scan이 지정되어있지 않으면
         * persistence.xml 을 생성하여 hibernate의 연결 및 데이터 소스 관련된 부분에 대해 직접
         * xml 버전으로 파일을 생성해 주어야 한다.
         */
        factory.setPackagesToScan("com.zerob.entity.primary");
        
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        factory.setJpaVendorAdapter(adapter);
        factory.setJpaProperties(addPrimaryHibernateProperties());
        
        return factory;
    }
    
    /**
     * 2019-04-01 - jasonLee
     * DataSource 에 구성되는 Properties를 별도로 관리 처리
     * @return
     */
    @Bean(name = "addPrimaryDataSourceProperties")
    @Primary
    Properties addPrimaryDataSourceProperties() {
        Properties properties = new Properties();
        
        /**
         * db 접속 주소
         */
        properties.setProperty("url", "jdbc:oracle:thin:@39.115.145.229:1521:orcl");
        /**
         * db 접속 사용자 아이디
         */
        properties.setProperty("user", "auctionwini");
        /**
         * db 접속 사용자 비밀번호
         */
        properties.setProperty("password", "auction");
        
        return properties;
    }
    
    /**
     * 2019-04-01 - jasonLee
     * Jpa Properties 추가 구성
     * Hibernate 옵션기능으로 구동에 상관없는 추가적인 옵션만 추가 적용 처리함.
     * @return
     */
    @Bean(name = "addPrimaryHibernateProperties")
    @Primary
    Properties addPrimaryHibernateProperties() {
        Properties properties = new Properties();
        
        /**
         * hibernate로 조회시 조회에 해당하는 메모리 쿼리 로그 표시 여부
         * (물리적으로 쿼리를 입력하여 조회하는 것은 아니지만 직관적으로 로그만보고 어떤 쿼리가 동작했는지에 대해 확인이 가능하다)
         */
        properties.setProperty("hibernate.show_sql", "true");
        /**
         * hibernate 유저 코멘트를 확인 가능 여부 표시
         */
        properties.setProperty("hibernate.use_sql_comments", "true");
        
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        
        return properties;
    }
    
}

package com.bysssss.goinmul.api.config;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@EnableConfigurationProperties
@EnableTransactionManagement
@MapperScan(basePackages={"com.bysssss.goinmul.api.model.mysql2.*"}, sqlSessionFactoryRef="sqlSessionFactory2", annotationClass=Mapper.class)
@Configuration
public class Mybatis2Config {
	@Bean(name="dataSource2")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.mysql2")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
	
	@Bean(name="sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
        //configuration.setJdbcTypeForNull(JdbcType.NULL);
		//configuration.setUseGeneratedKeys(true);
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper2/*.xml"));
		//sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[] {new EncDecTypeHandler(), new HashTypeHandler()});
		//sqlSessionFactoryBean.setTypeHandlersPackage("com.bysssss.goinmul.api.model.mysql.typehandler");
		sqlSessionFactoryBean.setConfiguration(configuration);
		
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		return sqlSessionFactory;
    }
	
	@Bean(name="sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
    	SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
	
	/*
	@Bean(name="dataSourceTransactionManager2")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource2") DataSource dataSource) {
    	DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }*/
}

package com.threshold.cfgs;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.threshold.properties.Constants;

@Configuration
public class SpringJDBCConfiguration {
	
	@Bean
	@Primary
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	    @Bean
	 	@Primary
	 	@DependsOn("propertySourcesPlaceholderConfigurer")
	 	Constants constants(
	 			             @Value("${mysql_user:admin}") String MYSQL_USER
	 			            ,@Value("${mysql_password:The@1234}") String MYSQL_PASSWORD
	 			            ,@Value("${mysql_port:3306}") int MYSQL_PORT
	 			            ,@Value("${mysql_host: localhost}") String MYSQL_HOST
	 			            ,@Value("${mysql_instance_db_name:spring_jdbc}") String INSTANCE_DB_NAME        
	 	) {
	 		return new Constants(MYSQL_USER,MYSQL_PASSWORD,MYSQL_PORT,MYSQL_HOST, INSTANCE_DB_NAME);
	 	}
 
    
    
    
	@Bean
	@Primary
	@DependsOn({"constants"})
    public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://"+Constants.MYSQL_HOST+":"+Constants.MYSQL_PORT+"/"+Constants.INSTANCE_DB_NAME);
        dataSource.setUsername(Constants.MYSQL_USER);
        dataSource.setPassword(Constants.MYSQL_PASSWORD);
        //MySQL database we are using
      /*  dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jdbc");
        dataSource.setUsername("admin");
        dataSource.setPassword("The@1234");*/
        return dataSource;
    }
 
    @Bean
    @Primary
	@DependsOn("dataSource")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
    
  
    
}
 
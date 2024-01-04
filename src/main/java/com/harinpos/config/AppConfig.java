package com.harinpos.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.harinpos.product.*;
import com.harinpos.stock.StockDao;
import com.harinpos.user.*;
import com.harinpos.sales.*;

@Configuration
@ComponentScan({ "com.harinpos.user", "com.harinpos.product" })
public class AppConfig {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/harin_pos?characterEncoding=utf8&serverTimezone=UTC");
		dataSource.setUsername("harin");
		dataSource.setPassword("2154");
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(10);
		dataSource.setTestWhileIdle(true);
		dataSource.setMinEvictableIdleTimeMillis(60000 * 3);
		dataSource.setTimeBetweenEvictionRunsMillis(10 * 1000);

		return dataSource;
	}

	@Bean
	public UserDao userDao() {
		return new UserDao(dataSource());
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public UserService userService() {
		return new UserService(userDao());
	}

	@Bean
	public ProductDao productDao() {
		return new ProductDao(dataSource());
	}
	
	@Bean
	public StockDao stockDao() {
		return new StockDao(dataSource());
	}
	
	@Bean
	public SaleDao saleDao() {
		return new SaleDao(dataSource());
	}
}

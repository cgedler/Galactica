/*
 * File name: AppConfig.java
 * Copyright (C) 2023 Christopher Gedler <cgedler@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.galactica.app.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * A configurer that can be used to configure the default operation.
 *
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages="com.galactica.app")
public class AppConfig implements WebMvcConfigurer {
    
    @Autowired
    private Environment env;
    
    private Logger myLogger=Logger.getLogger(getClass().getName());

    @Bean
    public ViewResolver getViewResolver(){
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    	resolver.setPrefix("/WEB-INF/views/");
    	resolver.setSuffix(".jsp");
    	return resolver;
    	
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    	registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
    	registry.addResourceHandler("/images/**").addResourceLocations("/resources/css/images/");
    	registry.addResourceHandler("/data/**").addResourceLocations("/resources/data/");
    	registry.addResourceHandler("/icons/**").addResourceLocations("/resources/icons/");
    	registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
    }

    private int getPropertiePool(String namePropertie) {
    	String propertieValue = env.getProperty(namePropertie);
    	int toIntPropertieValue = Integer.parseInt(propertieValue);
    	return toIntPropertieValue;
    	
    }
    
    
    // Security-Users Bean
    @Bean(name="dataSource")
    public DataSource getDataSource()  {
    	ComboPooledDataSource securityDataSource = new ComboPooledDataSource(); 
		try {
			// Set connection Properties
            securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
            securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
            securityDataSource.setUser(env.getProperty("jdbc.user"));
            securityDataSource.setPassword(env.getProperty("jdbc.password"));
            // Set connection pool Properties
            securityDataSource.setInitialPoolSize(getPropertiePool("connection.pool.initialPoolSize"));
            securityDataSource.setMinPoolSize(getPropertiePool("connection.pool.minPollSize"));
            securityDataSource.setMaxPoolSize(getPropertiePool("connection.pool.maxPoolSize"));
            securityDataSource.setMaxIdleTime(getPropertiePool("connection.pool.maxIdleTime"));
      	} catch (PropertyVetoException e) {
            e.printStackTrace();
	}
		return securityDataSource;
		
  }	
        
}

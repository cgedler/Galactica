/*
 * File name: AppConfigSecurity.java
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

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * A configurer that can be used to configure the default operation.
 *
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Configuration
@EnableWebSecurity
public class AppConfigSecurity extends WebSecurityConfigurerAdapter  {
    
    @Autowired
    private DataSource getDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication().dataSource(getDataSource);	
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
            .antMatchers("/").hasRole("usuario")
            .antMatchers("/administracion/**").hasRole("administrador")
            .antMatchers("/contabilidad/**").hasRole("contador")
            .antMatchers("/gerencia/**").hasRole("gerencia")
            .antMatchers("/rrhh/**").hasRole("rrhh")
            .antMatchers("/sistemas/**").hasRole("sistema")
            .antMatchers("/tersoreria/**").hasRole("tesorero")
            .antMatchers("/usuarios/**").hasRole("usuarios")
            .and().formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/user-authentication")
            .permitAll()
            .and().logout().permitAll()
            .and().exceptionHandling().accessDeniedPage("/access-denied");	
    }
	
}

/*
 * File name: AppInfo.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * A configurer that can be used to configure the default operation.
 *
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@PropertySource("classpath:application.properties")
public class AppInfo {

	@Autowired
    private Environment env;
	
	private final String COMPANY = env.getProperty("company");
	private final String RIF = env.getProperty("rif");
	private final String ADDRESS = env.getProperty("address");
	private final String CITY = env.getProperty("city");
	private final String POSTAL = env.getProperty("postal");
	private final String SYSTEM = env.getProperty("system");
	
	public String getCompany() {
		return COMPANY;
	}
	public String getRif() {
		return RIF;
	}
	public String getAddress() {
		return ADDRESS;
	}
	public String getCity() {
		return CITY;
	}
	public String getPostal() {
		return POSTAL;
	}
	public String getSystem() {
		return SYSTEM;
	}
	
}

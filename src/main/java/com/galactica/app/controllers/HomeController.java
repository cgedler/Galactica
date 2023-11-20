/*
 * File name: HomeController.java
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

package com.galactica.app.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller is the handler class that takes in all the requests and renders the correct
 * response.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Controller
@PropertySource("classpath:application.properties")
public class HomeController {
    
    @Autowired
    private Environment env;
	
    @RequestMapping(value="/")
    public ModelAndView showHome(HttpServletResponse response, ModelMap model) throws IOException {
	String systemName = env.getProperty("system");
	model.addAttribute("systemName", systemName);
	return new ModelAndView("home");
    }
	
    @GetMapping("/administracion")
    public String showAdministracion(ModelMap model) {
	String systemName = env.getProperty("system");
	model.addAttribute("systemName", systemName);
	return "administracion";
    }
	
    @GetMapping("/contabilidad")
    public String showContabilidad(ModelMap model) {
	String systemName = env.getProperty("system");
	model.addAttribute("systemName", systemName);
	return "contabilidad";
    }
	
    @GetMapping("/rrhh")
    public String showRrhh(ModelMap model) {
	String systemName = env.getProperty("system");
	model.addAttribute("systemName", systemName);
	return "rrhh";
    }
	
    @GetMapping("/sistemas")
    public String showSistemas(ModelMap model) {
	String systemName = env.getProperty("system");
	model.addAttribute("systemName", systemName);
	return "sistemas";
    }
	
    @GetMapping("/tersoreria")
    public String showTersoreria(ModelMap model) {
    	String systemName = env.getProperty("system");
	model.addAttribute("systemName", systemName);
	return "tersoreria";
    }
	
    @GetMapping("/access-denied")
    public String showAccessDenied() {
	return "access-denied";
    }
    
    @GetMapping(value = "/help")
    public void showHelp(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
      
        InputStream inputStream = new FileInputStream(new File(getClass().getResource("/Manual.pdf").getFile()));
        //InputStream inputStream = new FileInputStream(new File("C:/Manual.pdf"));
        //C:\Program Files (x86)\Apache Software Foundation\Tomcat 9.0\webapps\Base\WEB-INF\classes
        int nRead;
        while ((nRead = inputStream.read()) != -1) {
            response.getWriter().write(nRead);
        }
    } 
    
    //@GetMapping("/help")	   
    //public String showHelp(HttpServletResponse response) {
    //	return "help";
    //}

}

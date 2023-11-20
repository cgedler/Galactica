/*
 * File name: UsersController.java
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

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.galactica.app.dao.impl.UsersDAOimpl;
import com.galactica.app.models.Users;
import com.galactica.app.poa.Audit;

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
@RequestMapping("/usuarios/")
public class UsersController {
    
    @Autowired
    private Environment env;
    
    @Autowired
    UsersDAOimpl UsersRepository;
    
    @Autowired
	Audit AuditRepository;
   
    @GetMapping(value="list")
    public String showList(ModelMap model, @ModelAttribute("message") String message) {
        model.addAttribute("systemName", env.getProperty("system"));
        ArrayList<Users> listUsers = new ArrayList<Users>();
        listUsers = UsersRepository.getUsers();
        model.addAttribute("listado", listUsers);
        model.addAttribute("message", message);
        return "/usuarios/list";
    }
    
    @RequestMapping(value="create", method=RequestMethod.GET)
    public String showCreate(ModelMap model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Users theUser = new Users();
        model.addAttribute("user", theUser);
        return "/usuarios/create";
    }
    
    @PostMapping(value="add")
    public String doAdd(@ModelAttribute("user") Users theUser, RedirectAttributes attributes) {
    	UsersRepository.insertNewUser(theUser);
    	AuditRepository.afterInsertNew(theUser);
    	attributes.addFlashAttribute("message", "C");
        return "redirect:/usuarios/list";
    }
    
    @GetMapping(value="detail")
    public String showDetail(@RequestParam("username") String username, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Users theUser = new Users();
        theUser = UsersRepository.getUser(username);
        model.addAttribute("user", theUser);
        return "/usuarios/detail";
    }
    
    @GetMapping(value="edit")
	public String showEdit(@RequestParam("username") String username, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Users theUser = new Users();
        theUser = UsersRepository.getUser(username);
        model.addAttribute("user", theUser);
        return "/usuarios/edit";
	}
    
    @PostMapping(value="keep")
    public String doKeep(@ModelAttribute("user") Users theUser, RedirectAttributes attributes) {
    	Users oldUser = new Users();
    	oldUser = UsersRepository.getUser(theUser.getUsername());
    	AuditRepository.beforeSave(oldUser);
    	UsersRepository.saveUser(theUser);
    	AuditRepository.afterSave(theUser);
    	attributes.addFlashAttribute("message", "U");
        return "redirect:/usuarios/list";
    }
    
    @PostMapping(value="eliminate")
	public String doEliminate(@RequestParam("username") String username, RedirectAttributes attributes) {
    	Users theUser = new Users();
        theUser = UsersRepository.getUser(username);
        AuditRepository.beforeEliminate(theUser);
    	UsersRepository.eliminateUser(username);
        attributes.addFlashAttribute("message", "D");
        return "redirect:/usuarios/list";
	}
    
}

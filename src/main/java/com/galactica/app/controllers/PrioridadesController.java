/*
 * File name: PrioridadesController.java
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

import com.galactica.app.dao.impl.PriorityDAOimpl;
import com.galactica.app.models.Priority;
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
@RequestMapping("/prioridades/")
public class PrioridadesController {
	
	@Autowired
    private Environment env;
    
    @Autowired
    PriorityDAOimpl PrioritysRepository;
    
    @Autowired
	Audit AuditRepository;
    
    @GetMapping(value="list")
    public String showList(ModelMap model, @ModelAttribute("message") String message) {
        model.addAttribute("systemName", env.getProperty("system"));
        ArrayList<Priority> listPrioritys = new ArrayList<Priority>();
        listPrioritys = PrioritysRepository.getPrioritys();
        model.addAttribute("listado", listPrioritys);
        model.addAttribute("message", message);
        return "/prioridades/list";
    }
    
    @RequestMapping(value="create", method=RequestMethod.GET)
    public String showCreate(ModelMap model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Priority thePriority = new Priority();
        model.addAttribute("priority", thePriority);
        return "/prioridades/create";
    }
    
    @PostMapping(value="add")
    public String doAdd(@ModelAttribute("Priority") Priority thePriority, RedirectAttributes attributes) {
    	PrioritysRepository.insertNewPriority(thePriority);
    	AuditRepository.afterInsertNew(thePriority);
    	attributes.addFlashAttribute("message", "C");
        return "redirect:/prioridades/list";
    }
    
    @GetMapping(value="detail")
    public String showDetail(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Priority thePriority = new Priority();
        thePriority = PrioritysRepository.getPriority(id);
        model.addAttribute("priority", thePriority);
        return "/prioridades/detail";
    }
    
    @GetMapping(value="edit")
	public String showEdit(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Priority thePriority = new Priority();
        thePriority = PrioritysRepository.getPriority(id);
        model.addAttribute("priority", thePriority);
        return "/prioridades/edit";
	}
    
    @PostMapping(value="keep")
    public String doKeep(@ModelAttribute("Priority") Priority thePriority, RedirectAttributes attributes) {
    	Priority oldPriority = new Priority();
    	oldPriority = PrioritysRepository.getPriority(thePriority.getId());
    	AuditRepository.beforeSave(oldPriority);
    	PrioritysRepository.savePriority(thePriority);
    	AuditRepository.afterSave(thePriority);
    	attributes.addFlashAttribute("message", "U");
        return "redirect:/prioridades/list";
    }
    
    @PostMapping(value="eliminate")
	public String doEliminate(@RequestParam("id") int id, RedirectAttributes attributes) {
    	Priority thePriority = new Priority();
        thePriority = PrioritysRepository.getPriority(id);
        AuditRepository.beforeEliminate(thePriority);
    	PrioritysRepository.eliminatePriority(id);
        attributes.addFlashAttribute("message", "D");
        return "redirect:/prioridades/list";
	}
    
}

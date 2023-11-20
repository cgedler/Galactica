/*
 * File name: CalificacionesController.java
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

import com.galactica.app.dao.impl.QualificationDAOimpl;
import com.galactica.app.models.Qualification;
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
@RequestMapping("/calificaciones/")
public class CalificacionesController {
	
	@Autowired
    private Environment env;
    
    @Autowired
    QualificationDAOimpl QualificationsRepository;
    
	@Autowired
	Audit AuditRepository;
    
    //@RequestMapping(value="list", method=RequestMethod.GET)
    @GetMapping(value="list")
    public String showList(ModelMap model, @ModelAttribute("message") String message) {
        model.addAttribute("systemName", env.getProperty("system"));
        ArrayList<Qualification> listQualifications = new ArrayList<Qualification>();
        listQualifications = QualificationsRepository.getQualifications();
        model.addAttribute("listado", listQualifications);
        model.addAttribute("message", message);
        return "/calificaciones/list";
    }
    
    @RequestMapping(value="create", method=RequestMethod.GET)
    public String showCreate(ModelMap model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Qualification theQualification = new Qualification();
        model.addAttribute("qualification", theQualification);
        return "/calificaciones/create";
    }
    
    @PostMapping(value="add")
    public String doAdd(@ModelAttribute("Qualification") Qualification theQualification, RedirectAttributes attributes) {
    	QualificationsRepository.insertNewQualification(theQualification);
    	AuditRepository.afterInsertNew(theQualification);
    	attributes.addFlashAttribute("message", "C");
        return "redirect:/calificaciones/list";
    }
    
    @GetMapping(value="detail")
    public String showDetail(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Qualification theQualification = new Qualification();
        theQualification = QualificationsRepository.getQualification(id);
        model.addAttribute("qualification", theQualification);
        return "/calificaciones/detail";
    }
    
    @GetMapping(value="edit")
	public String showEdit(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Qualification theQualification = new Qualification();
        theQualification = QualificationsRepository.getQualification(id); //return data
        model.addAttribute("qualification", theQualification);
        return "/calificaciones/edit";
	}
    
    @PostMapping(value="keep")
    public String doKeep(@ModelAttribute("Qualification") Qualification theQualification, RedirectAttributes attributes) {
    	Qualification oldQualification = new Qualification();
    	oldQualification = QualificationsRepository.getQualification(theQualification.getId());
    	AuditRepository.beforeSave(oldQualification);
    	QualificationsRepository.saveQualification(theQualification);
    	AuditRepository.afterSave(theQualification);
    	attributes.addFlashAttribute("message", "U");
        return "redirect:/calificaciones/list";
    }
    
    @PostMapping(value="eliminate")
	public String doEliminate(@RequestParam("id") int id, RedirectAttributes attributes) {
        Qualification theQualification = new Qualification();
        theQualification = QualificationsRepository.getQualification(id);
        AuditRepository.beforeEliminate(theQualification);
        QualificationsRepository.eliminateQualification(id);
        attributes.addFlashAttribute("message", "D");
        return "redirect:/calificaciones/list";
	}
    
}

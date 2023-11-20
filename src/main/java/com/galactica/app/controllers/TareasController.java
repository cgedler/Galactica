/*
 * File name: TareasController.java
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

import com.galactica.app.dao.impl.DepartmentDAOimpl;
import com.galactica.app.dao.impl.TaskDAOimpl;
import com.galactica.app.models.Department;
import com.galactica.app.models.Task;
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
@RequestMapping("/tareas/")
public class TareasController {
	
	@Autowired
    private Environment env;
    
    @Autowired
    TaskDAOimpl TasksRepository;
    
    @Autowired
    DepartmentDAOimpl DepartmentsRepository;
    
    @Autowired
	Audit AuditRepository;
   
    @GetMapping(value="list")
    public String showList(ModelMap model, @ModelAttribute("message") String message) {
        model.addAttribute("systemName", env.getProperty("system"));
        ArrayList<Task> listTasks = new ArrayList<Task>();
        listTasks = TasksRepository.getTasks();
        model.addAttribute("listado", listTasks);
        model.addAttribute("message", message);
        return "/tareas/list";
    }
    
    @RequestMapping(value="create", method=RequestMethod.GET)
    public String showCreate(ModelMap model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Task theTask = new Task();
        model.addAttribute("task", theTask);
        ArrayList<Department> listDepartments = new ArrayList<Department>();
        listDepartments = DepartmentsRepository.getDepartments();
        model.addAttribute("listado", listDepartments);
        return "/tareas/create";
    }
    
    @PostMapping(value="add")
    public String doAdd(@ModelAttribute("task") Task theTask, RedirectAttributes attributes) {
    	TasksRepository.insertNewTask(theTask);
    	AuditRepository.afterInsertNew(theTask);
    	attributes.addFlashAttribute("message", "C");
        return "redirect:/tareas/list";
    }
    
    @GetMapping(value="detail")
    public String showDetail(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Task theTask = new Task();
        theTask = TasksRepository.getTask(id);
        model.addAttribute("task", theTask);
        return "/tareas/detail";
    }
    
    @GetMapping(value="edit")
	public String showEdit(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Task theTask = new Task();
        theTask = TasksRepository.getTask(id);
        model.addAttribute("task", theTask);
        ArrayList<Department> listDepartments = new ArrayList<Department>();
        listDepartments = DepartmentsRepository.getDepartments();
        model.addAttribute("listado", listDepartments);
        return "/tareas/edit";
	}
    
    @PostMapping(value="keep")
    public String doKeep(@ModelAttribute("task") Task theTask, RedirectAttributes attributes) {
    	Task oldTask = new Task();
    	oldTask = TasksRepository.getTask(theTask.getId());
    	AuditRepository.beforeSave(oldTask);
    	TasksRepository.saveTask(theTask);
    	AuditRepository.afterSave(theTask);
    	attributes.addFlashAttribute("message", "U");
        return "redirect:/tareas/list";
    }
    
    @PostMapping(value="eliminate")
	public String doEliminate(@RequestParam("id") int id, RedirectAttributes attributes) {
    	Task theTask = new Task();
        theTask = TasksRepository.getTask(id);
        AuditRepository.beforeEliminate(theTask);
    	TasksRepository.eliminateTask(id);
        attributes.addFlashAttribute("message", "D");
        return "redirect:/tareas/list";
	}
    
}

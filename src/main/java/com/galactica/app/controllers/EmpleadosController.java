/*
 * File name: EmpleadosController.java
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
import com.galactica.app.dao.impl.EmployeeDAOimpl;
import com.galactica.app.dao.impl.UsersDAOimpl;
import com.galactica.app.models.Department;
import com.galactica.app.models.Employee;
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
@RequestMapping("/empleados/")
public class EmpleadosController {
	
	@Autowired
    private Environment env;
    
    @Autowired
    EmployeeDAOimpl EmployeesRepository;
    
    @Autowired
    DepartmentDAOimpl DepartmentsRepository;
    
    @Autowired
    UsersDAOimpl UsersRepository;
    
    @Autowired
	Audit AuditRepository;
   
    @GetMapping(value="list")
    public String showList(ModelMap model, @ModelAttribute("message") String message) {
        model.addAttribute("systemName", env.getProperty("system"));
        ArrayList<Employee> listEmployees = new ArrayList<Employee>();
        listEmployees = EmployeesRepository.getEmployees();
        model.addAttribute("listado", listEmployees);
        model.addAttribute("message", message);
        return "/empleados/list";
    }
    
    @RequestMapping(value="create", method=RequestMethod.GET)
    public String showCreate(ModelMap model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Employee theEmployee = new Employee();
        model.addAttribute("employee", theEmployee);
        ArrayList<Department> listDepartments = new ArrayList<Department>();
        listDepartments = DepartmentsRepository.getDepartments();
        model.addAttribute("listado", listDepartments);
        ArrayList<Users> listUsers = new ArrayList<Users>();
        listUsers = UsersRepository.getUsers();
        model.addAttribute("usuarios", listUsers);
        return "/empleados/create";
    }
    
    @PostMapping(value="add")
    public String doAdd(@ModelAttribute("employee") Employee theEmployee, RedirectAttributes attributes) {
    	EmployeesRepository.insertNewEmployee(theEmployee);
    	AuditRepository.afterInsertNew(theEmployee);
    	attributes.addFlashAttribute("message", "C");
        return "redirect:/empleados/list";
    }
    
    @GetMapping(value="detail")
    public String showDetail(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Employee theEmployee = new Employee();
        theEmployee = EmployeesRepository.getEmployee(id);
        model.addAttribute("employee", theEmployee);
        return "/empleados/detail";
    }
    
    @GetMapping(value="edit")
	public String showEdit(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Employee theEmployee = new Employee();
        theEmployee = EmployeesRepository.getEmployee(id); //return data
        model.addAttribute("employee", theEmployee);
        ArrayList<Department> listDepartments = new ArrayList<Department>();
        listDepartments = DepartmentsRepository.getDepartments();
        model.addAttribute("listado", listDepartments);
        ArrayList<Users> listUsers = new ArrayList<Users>();
        listUsers = UsersRepository.getUsers();
        model.addAttribute("usuarios", listUsers);
        return "/empleados/edit";
	}
    
    @PostMapping(value="keep")
    public String doKeep(@ModelAttribute("employee") Employee theEmployee, RedirectAttributes attributes) {
    	Employee oldEmployee = new Employee();
    	oldEmployee = EmployeesRepository.getEmployee(theEmployee.getId());
    	AuditRepository.beforeSave(oldEmployee);
    	EmployeesRepository.saveEmployee(theEmployee);
    	AuditRepository.afterSave(theEmployee);
    	attributes.addFlashAttribute("message", "U");
        return "redirect:/empleados/list";
    }
    
    @PostMapping(value="eliminate")
	public String doEliminate(@RequestParam("id") int id, RedirectAttributes attributes) {
    	Employee theEmployee = new Employee();
        theEmployee = EmployeesRepository.getEmployee(id);
        AuditRepository.beforeEliminate(theEmployee);
    	EmployeesRepository.eliminateEmployee(id);
        attributes.addFlashAttribute("message", "D");
        return "redirect:/empleados/list";
	}
    
}

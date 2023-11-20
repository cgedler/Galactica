/*
 * File name: Audit.java
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

package com.galactica.app.poa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galactica.app.dao.impl.DepartmentDAOimpl;
import com.galactica.app.dao.impl.EmployeeDAOimpl;
import com.galactica.app.dao.impl.PriorityDAOimpl;
import com.galactica.app.dao.impl.QualificationDAOimpl;
import com.galactica.app.dao.impl.TaskDAOimpl;
import com.galactica.app.dao.impl.TraksDAOimpl;
import com.galactica.app.dao.impl.UsersDAOimpl;
import com.galactica.app.models.Department;
import com.galactica.app.models.Employee;
import com.galactica.app.models.Priority;
import com.galactica.app.models.Qualification;
import com.galactica.app.models.Task;
import com.galactica.app.models.Traks;
import com.galactica.app.models.Users;

@Repository
public class Audit {
	
	@Autowired
	EmployeeDAOimpl EmployeesRepository;
	
	@Autowired
	TraksDAOimpl TraksRepository;
	
	@Autowired
    UsersDAOimpl UsersRepository;
	
	@Autowired
    DepartmentDAOimpl DepartmentsRepository;
	 
	@Autowired
	TaskDAOimpl TasksRepository;
	 
	@Autowired
	PriorityDAOimpl PrioritysRepository;
	
	@Autowired
	QualificationDAOimpl QualificationRepository;
	
	public void afterInsertNew(Object args) {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ((UserDetails)principal).getUsername();
			int UserId = EmployeesRepository.getEmployeeId(username);	
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			String jsonObject = "";
			if(args instanceof Users) {
				Users tempObject = (Users)args;
				jsonObject = mapper.writeValueAsString(tempObject);
			}
			if(args instanceof Department) {
				Department tempObject = (Department)args;
				jsonObject = mapper.writeValueAsString(tempObject);
			}
			if(args instanceof Employee) {
				Employee tempObject = (Employee)args;
				jsonObject = mapper.writeValueAsString(tempObject);
			}
			if(args instanceof Priority) {
				Priority tempObject = (Priority)args;
				jsonObject = mapper.writeValueAsString(tempObject);
			}
			if(args instanceof Qualification) {
				Qualification tempObject = (Qualification)args;
				jsonObject = mapper.writeValueAsString(tempObject);
			}
			if(args instanceof Task) {
				Task tempObject = (Task)args;
				jsonObject = mapper.writeValueAsString(tempObject);
			}
            Traks theTrak = new Traks(UserId, "INSERT", jsonObject);
	        TraksRepository.recordTraks(theTrak);
		} catch(Exception e) {
    		e.printStackTrace();
    	}
		
	}
	
	public void beforeSave(Object args) {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ((UserDetails)principal).getUsername();
			int UserId = EmployeesRepository.getEmployeeId(username);	
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			String jsonObject = "";
			Object theObject = null;
				if(args instanceof Users) {
					Users tempObject = (Users)args;
					theObject = new Users();
					theObject = UsersRepository.getUser(tempObject.getUsername());
				}
				if(args instanceof Department) {
					Department tempObject = (Department)args;
					theObject = new Department();
					theObject = DepartmentsRepository.getDepartment(tempObject.getId());
				}
				if(args instanceof Employee) {
					Employee tempObject = (Employee)args;
					theObject = new Employee();
					theObject = EmployeesRepository.getEmployee(tempObject.getId());
				}
				if(args instanceof Priority) {
					Priority tempObject = (Priority)args;
					theObject = new Priority();
					theObject = PrioritysRepository.getPriority(tempObject.getId());
				}
				if(args instanceof Qualification) {
					Qualification tempObject = (Qualification)args;
					theObject = new Qualification();
					theObject = QualificationRepository.getQualification(tempObject.getId());
				}
				if(args instanceof Task) {
					Task tempObject = (Task)args;
					theObject = new Task();
					theObject = TasksRepository.getTask(tempObject.getId());
				}
			
			jsonObject = mapper.writeValueAsString(theObject);
            Traks theTrak = new Traks(UserId, "BEFORE-UPDATE", jsonObject);
	        TraksRepository.recordTraks(theTrak);
		} catch(Exception e) {
    		e.printStackTrace();
    	}
		
	}
	
	public void afterSave(Object args) {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ((UserDetails)principal).getUsername();
			int UserId = EmployeesRepository.getEmployeeId(username);	
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
		    String jsonObject = mapper.writeValueAsString(args);
            Traks theTrak = new Traks(UserId, "AFTER-UPDATE", jsonObject);
	        TraksRepository.recordTraks(theTrak);
		} catch(Exception e) {
    		e.printStackTrace();
    	}
		
	}	
			
	public void beforeEliminate(Object args) {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ((UserDetails)principal).getUsername();
			int UserId = EmployeesRepository.getEmployeeId(username);	
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			String jsonObject = "";
			Object theObject = null;
			if(args instanceof Users) {
				Users tempObject = (Users)args;
				theObject = new Users();
				theObject = UsersRepository.getUser(tempObject.getUsername());
			}
			if(args instanceof Department) {
				Department tempObject = (Department)args;
				theObject = new Department();
				theObject = DepartmentsRepository.getDepartment(tempObject.getId());
			}
			if(args instanceof Employee) {
				Employee tempObject = (Employee)args;
				theObject = new Employee();
				theObject = EmployeesRepository.getEmployee(tempObject.getId());
			}
			if(args instanceof Priority) {
				Priority tempObject = (Priority)args;
				theObject = new Priority();
				theObject = PrioritysRepository.getPriority(tempObject.getId());
			}
			if(args instanceof Qualification) {
				Qualification tempObject = (Qualification)args;
				theObject = new Qualification();
				theObject = QualificationRepository.getQualification(tempObject.getId());
			}
			if(args instanceof Task) {
				Task tempObject = (Task)args;
				theObject = new Task();
				theObject = TasksRepository.getTask(tempObject.getId());
			}
			jsonObject = mapper.writeValueAsString(theObject);
            Traks theTrak = new Traks(UserId, "BEFORE-ELIMINATE", jsonObject);
	        TraksRepository.recordTraks(theTrak);
		} catch(Exception e) {
    		e.printStackTrace();
    	}
		
	}

}

/*
 * File name: TesoreriaController.java
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import com.galactica.app.dao.impl.DepartmentDAOimpl;
import com.galactica.app.dao.impl.EmployeeDAOimpl;
import com.galactica.app.dao.impl.PriorityDAOimpl;
import com.galactica.app.dao.impl.QualificationDAOimpl;
import com.galactica.app.dao.impl.RequestDAOimpl;
import com.galactica.app.dao.impl.RequestReportDAOimpl;
import com.galactica.app.dao.impl.TaskDAOimpl;
import com.galactica.app.models.Department;
import com.galactica.app.models.DepartmentSelect;
import com.galactica.app.models.EmployeeSelect;
import com.galactica.app.models.Priority;
import com.galactica.app.models.Qualification;
import com.galactica.app.models.Request;
import com.galactica.app.models.RequestQualification;
import com.galactica.app.models.Task;
import com.galactica.app.models.QualifyValue;

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
@RequestMapping("/tersoreria/")
public class TesoreriaController {
	
	@Autowired
    private Environment env;
    
	@Autowired
	RequestDAOimpl RequestsRepository;
	
	@Autowired
    DepartmentDAOimpl DepartmentsRepository;
	
	@Autowired
	EmployeeDAOimpl EmployeesRepository;
	 
	@Autowired
	TaskDAOimpl TasksRepository;
	 
	@Autowired
	PriorityDAOimpl PrioritysRepository;
	
	@Autowired
	QualificationDAOimpl QualificationRepository;
	
	@Autowired
	RequestReportDAOimpl RequestReportRepository;
	
	
	/**
	* DEPRECATED
	* 
	* This method is used to return a view that is composed of several queries.
	* I will do a refactoring of this method as soon as it is available.
	* 
	*/
	@GetMapping(value="list")
    public String showList(ModelMap model) {
    	try {
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("systemName", env.getProperty("system"));
           
            int toStart = RequestReportRepository.getReportByDepartment(5, 'C');
            model.addAttribute("toStart", toStart);
        
            int inProcess = RequestReportRepository.getReportByDepartment(5, 'I');
            model.addAttribute("inProcess", inProcess);
        
            float sumReqPerc = toStart + inProcess;
            float toStartPerc = (toStart*100)/sumReqPerc; 
            float inProcessPerc = (inProcess*100)/sumReqPerc;
        
            model.addAttribute("inProcessPerc", inProcessPerc);
            model.addAttribute("toStartPerc", toStartPerc);
        
            String [] labelMonths = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            String jsonLabelMonths = mapper.writeValueAsString(labelMonths);
            String jsonLabelMonthsReplace = jsonLabelMonths.replace("\"", "\'");
            model.addAttribute("labelMonths", jsonLabelMonthsReplace);
        
            LocalDate now = LocalDate.now();
            int thisYear = now.getYear();
            int lastYear = thisYear - 1;
            int lastTwoYears = thisYear - 2;
            
            int[] valuesThisYear = RequestReportRepository.getReportByDepartmentYear(5, 'F', thisYear);
            String valuesMonthsThisYear = Arrays.toString(valuesThisYear);
            model.addAttribute("valuesMonthsThisYear", valuesMonthsThisYear);
            
            int[] valuesLastYear = RequestReportRepository.getReportByDepartmentYear(5, 'F', lastYear);
            String valuesMonthsLastYear = Arrays.toString(valuesLastYear);
            model.addAttribute("valuesMonthsLastYear", valuesMonthsLastYear);
            
            int[] valuesLastTwoYears = RequestReportRepository.getReportByDepartmentYear(5, 'F', lastTwoYears);
            String valuesMonthsLastTwoYears = Arrays.toString(valuesLastTwoYears);
            model.addAttribute("valuesMonthsLastTwoYears", valuesMonthsLastTwoYears);
        
            model.addAttribute("thisYear", Integer.toString(thisYear));
            model.addAttribute("lastYear", Integer.toString(lastYear));
            model.addAttribute("lastTwoYears", Integer.toString(lastTwoYears));
                    
            ArrayList<Department> listDepartments = new ArrayList<Department>();
            listDepartments = DepartmentsRepository.getDepartments();
              
            ArrayList<DepartmentSelect> listDepartmentsValues = new ArrayList<DepartmentSelect>();
            
            for(Department elemento:listDepartments){
            	
            	DepartmentSelect tempDepartmentValue = new DepartmentSelect();
            	
            	int[] valuesByDepartmentYear = RequestReportRepository.getReportByDepartmentYear(elemento.getId(), 'F', thisYear);
            	int id = elemento.getId();
            	String description = elemento.getDescription();
            	tempDepartmentValue.set_id(id);
            	tempDepartmentValue.set_description(description);
            	tempDepartmentValue.set_values(valuesByDepartmentYear);            	
            	listDepartmentsValues.add(tempDepartmentValue);
	        }
            String jsonListDepartmentsValues = mapper.writeValueAsString(listDepartmentsValues);
            model.addAttribute("ListDepartmentsValues", jsonListDepartmentsValues);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        return "/tersoreria/list";
    }
    
    @GetMapping(value="myreqtostart")
    public String showMyReqToStart(ModelMap model, @ModelAttribute("message") String message) {
        model.addAttribute("systemName", env.getProperty("system"));
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = ((UserDetails)principal).getUsername();
    	int UserId = EmployeesRepository.getEmployeeId(username);
        
    	ArrayList<Request> listReqToStart = new ArrayList<Request>();
        listReqToStart = RequestsRepository.getReqSender(UserId, 'C');
        model.addAttribute("listReqToStart", listReqToStart);
 
        model.addAttribute("message", message);
        return "/tersoreria/myreqtostart";
    }
    
    @GetMapping(value="myreqinprogress")
    public String showMyReqInProgress(ModelMap model) {
        model.addAttribute("systemName", env.getProperty("system"));
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = ((UserDetails)principal).getUsername();
    	int UserId = EmployeesRepository.getEmployeeId(username);
        
        ArrayList<Request> listReqInProgress = new ArrayList<Request>();
        listReqInProgress = RequestsRepository.getReqSender(UserId, 'I');
        model.addAttribute("listReqInProgress", listReqInProgress);
        return "/tersoreria/myreqinprogress";
    }
    
    @GetMapping(value="myreqfinished")
    public String showMyReqFinished(ModelMap model) {
        model.addAttribute("systemName", env.getProperty("system"));
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = ((UserDetails)principal).getUsername();
    	int UserId = EmployeesRepository.getEmployeeId(username);
        
  
        ArrayList<Request> listReqFinished = new ArrayList<Request>();
        listReqFinished = RequestsRepository.getReqSender(UserId, 'E');
        model.addAttribute("listReqFinished", listReqFinished);
        return "/tersoreria/myreqfinished";
    }
    
    @GetMapping(value="mytaskstostart")
    public String showMyTasksToStart(ModelMap model, @ModelAttribute("message") String message) {
        model.addAttribute("systemName", env.getProperty("system"));
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = ((UserDetails)principal).getUsername();
    	int UserId = EmployeesRepository.getEmployeeId(username);
        
    	ArrayList<Request> listTasksToStart = new ArrayList<Request>();
        listTasksToStart = RequestsRepository.getReqReceiver(UserId, 'C');
        model.addAttribute("listTasksToStart", listTasksToStart);
 
        model.addAttribute("message", message);
        return "/tersoreria/mytaskstostart";
    }
    
    @GetMapping(value="mytasksinprogress")
    public String showMyTasksInProgress(ModelMap model) {
        model.addAttribute("systemName", env.getProperty("system"));
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = ((UserDetails)principal).getUsername();
    	int UserId = EmployeesRepository.getEmployeeId(username);
        
        ArrayList<Request> listTasksInProgress = new ArrayList<Request>();
        listTasksInProgress = RequestsRepository.getReqReceiver(UserId, 'I');
        model.addAttribute("listTasksInProgress", listTasksInProgress);
        return "/tersoreria/mytasksinprogress";
    }
    
    @RequestMapping(value="create", method=RequestMethod.GET)
    public String showCreate(Model model) {
    	try {
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("systemName", env.getProperty("system"));
            Request theRequest = new Request();
            model.addAttribute("request", theRequest);
            ArrayList<Department> listDepartments = new ArrayList<Department>();
            listDepartments = DepartmentsRepository.getDepartments();
            model.addAttribute("departments", listDepartments);
            ArrayList<EmployeeSelect> listEmployees = new ArrayList<EmployeeSelect>();
            listEmployees = EmployeesRepository.getEmployeeSelect();      
            String jsonEmployees = mapper.writeValueAsString(listEmployees);
            model.addAttribute("employees", jsonEmployees);
            ArrayList<Task> listTasks = new ArrayList<Task>();
            listTasks = TasksRepository.getTasksSelect();
            String jsonTasks = mapper.writeValueAsString(listTasks);
            model.addAttribute("tasks", jsonTasks);
            ArrayList<Priority> listPrioritys = new ArrayList<Priority>();
            listPrioritys = PrioritysRepository.getPrioritys();
            model.addAttribute("prioritys", listPrioritys);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "/tersoreria/create";
    }
    
    @PostMapping(value="add")
    public String doAdd(@ModelAttribute("request") Request theRequest, RedirectAttributes attributes) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = ((UserDetails)principal).getUsername();
    	int UserId = EmployeesRepository.getEmployeeId(username);
    	RequestsRepository.keepNewRequest(theRequest, UserId);//
    	attributes.addFlashAttribute("message", "C");
        return "redirect:/tersoreria/myreqtostart";
    }
    
    @GetMapping(value="detail")
    public String showDetail(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
        Request theRequest = new Request();
        theRequest = RequestsRepository.getReqDetails(id);
        model.addAttribute("req", theRequest);
        return "/tersoreria/detail";
    }
    
    @GetMapping(value="edittaskinprogress")
	public String showEditTaskInProgress(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
    	Request theRequest = new Request();
        theRequest = RequestsRepository.getReqDetails(id);
        theRequest.setComments("");
        model.addAttribute("req", theRequest); 
        return "/tersoreria/edittaskinprogress";
	}
    
    @PostMapping(value="keeptaskinprogress")
    public String doKeepTaskInProgress(@ModelAttribute("Request") Request theRequest, RedirectAttributes attributes) {
    	RequestsRepository.keepRequestStop(theRequest);
    	attributes.addFlashAttribute("message", "U");
        return "redirect:/tersoreria/mytasksinprogress";
    }
    
    @GetMapping(value="edittasktostart")
	public String showEditTaskToStart(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
    	Request theRequest = new Request();
        theRequest = RequestsRepository.getReqDetails(id);
        theRequest.setComments("");
        model.addAttribute("req", theRequest);
        return "/tersoreria/edittasktostart";
	}
    
    @PostMapping(value="keeptasktostart")
    public String doKeepTaskToStart(@ModelAttribute("Request") Request theRequest, RedirectAttributes attributes) {
    	RequestsRepository.keepRequestStart(theRequest);
    	attributes.addFlashAttribute("message", "U");
        return "redirect:/tersoreria/mytaskstostart";
    }
    
    @RequestMapping(value="qualify", method=RequestMethod.GET)
	public String showEditQualify(@RequestParam("id") int id, Model model) {
    	model.addAttribute("systemName", env.getProperty("system"));
    	int requestId = id;
    	model.addAttribute("requestid", requestId);
        ArrayList<Qualification> listQualification = new ArrayList<Qualification>();
        listQualification = QualificationRepository.getQualifications();
        model.addAttribute("qualifications", listQualification);
        RequestQualification theQualify = new RequestQualification();
        model.addAttribute("qualify", theQualify);
        return "/tersoreria/qualify";
	}
    
    @PostMapping(value="keepqualify")
    public String doKeepQualify(@ModelAttribute("Qualify") RequestQualification theQualify, RedirectAttributes attributes) {
    	RequestsRepository.keepQualify(theQualify);
    	attributes.addFlashAttribute("message", "U");
        return "redirect:/tersoreria/myreqfinished";
    }
    
    @GetMapping(value="myqualify")
    public String showMyQualify(ModelMap model) {
    	try {
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("systemName", env.getProperty("system"));
            
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails)principal).getUsername();
            int UserId = EmployeesRepository.getEmployeeId(username);
            
            ArrayList<RequestQualification> listMyQualifys = new ArrayList<RequestQualification>();
            listMyQualifys = RequestsRepository.getMyQualifys(UserId);
            model.addAttribute("listMyQualifys", listMyQualifys);
            
            ArrayList<QualifyValue> valuesQualifys = new ArrayList<QualifyValue>();
            valuesQualifys = RequestsRepository.getMyQualifyCount(UserId);
            String jsonValuesQualifys = mapper.writeValueAsString(valuesQualifys);
            model.addAttribute("ValuesQualifys", jsonValuesQualifys);
            
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        return "/tersoreria/myqualify";
    }
    
}

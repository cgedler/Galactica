/*
 * File name: EmployeeDAO.java
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

package com.galactica.app.dao;

import java.util.List;

import com.galactica.app.models.Employee;
import com.galactica.app.models.EmployeeSelect;

/**
 * In an application the Data Access Object (DAO) is a part of Data access layer. It is an object
 * that provides an interface to some type of persistence mechanism. By mapping application calls to
 * the persistence layer, DAO provides some specific data operations without exposing details of the
 * database. This isolation supports the Single responsibility principle. It separates what data
 * accesses the application needs, in terms of domain-specific objects and data types (the public
 * interface of the DAO), from how these needs can be satisfied with a specific DBMS, database
 * schema, etc.
 *
 * <p>Any change in the way data is stored and retrieved will not change the client code as the
 * client will be using interface and need not worry about exact source.
 *
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
public interface EmployeeDAO {
    
	/**
	   * Get all Employees.
	   *
	   * @return a list of all the Employees.
	   */
	public List<Employee> getEmployees();
    
	/**
	   * Get all EmployeeSelect.
	   *
	   * @return a list of all the EmployeeSelect.
	   */
	public List<EmployeeSelect> getEmployeeSelect();
    
    /**
     * Get Employee as Optional by id.
     *
     * @param id unique identifier of the Employee.
     * @return an optional with employee if a employee with unique identifier <code>id</code> exists.
     */
    public Employee getEmployee(int id);
    
    /**
     * Get Employee ID as Optional by username.
     *
     * @param username unique identifier of the web user.
     * @return an optional with employee if a employee with unique identifier <code>username</code> exists.
     */
    public int getEmployeeId(String username);
    
    /**
     * Add a Employee.
     *
     * @param theEmployee the Employee to be added.
     */
    public void insertNewEmployee(Employee theEmployee);
    
    /**
     * Update a Employee.
     *
     * @param theEmployee the Employee to be updated.
     */
    public void saveEmployee(Employee theEmployee);
    
    /**
     * Delete a Employee.
     *
     * @param id unique identifier of the Employee to be deleted.
     */
    public void eliminateEmployee(int id);
    
}

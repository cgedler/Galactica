/*
 * File name: Employee.java
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

package com.galactica.app.models;

/**
 * Employee entity.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
public class Employee {
    
	private int _id;
    private String _name;
    private String _lastname;
    private boolean _active;
    private int _departmentid;
    private String _department;
    private String _username;
	
    /**
     * Constructor.
     */
	public Employee() {
		
	}

	/**
     * Constructor.
     */
	public Employee(int id, String name, String lastname, boolean active, int departmentid) {
		super();
		this._id = id;
		this._name = name;
		this._lastname = lastname;
		this._active = active;
		this._departmentid = departmentid;
	}
    
	/**
     * Constructor.
     */
	public Employee(int id, String name, String lastname, boolean active, String username, String department, int departmentid) {
		super();
		this._id = id;
		this._name = name;
		this._lastname = lastname;
		this._active = active;
        this._username = username;
		this._department = department;
        this._departmentid = departmentid;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getLastname() {
		return _lastname;
	}

	public void setLastname(String lastname) {
		this._lastname = lastname;
	}
    
	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		this._active = active;
	}

	public int getDepartmentid() {
		return _departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this._departmentid = departmentid;
	}
	
	public String getDepartment() {
		return _department;
	}

	public void setDepartment(String department) {
		this._department = department;
	}
    
    public String getUsername() {
    	return _username;
    }
    
    public void setUsername(String username) {
    	this._username = username;
    }
    
}

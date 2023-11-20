/*
 * File name: Task.java
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
 * Task entity.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
public class Task {

	private int _id;
    private String _description;
    private int _departmentid;
    private String _department;
    
    /**
     * Constructor.
     */
    public Task() {
    	
    }

    /**
     * Constructor.
     */
	public Task(int id, String description, int departmentid) {
		super();
		this._id = id;
		this._description = description;
		this._departmentid = departmentid;
	}

	/**
     * Constructor.
     */
	public Task(int id, String description, String department) {
		super();
		this._id = id;
		this._description = description;
		this._department = department;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
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

	@Override
	public String toString() {
		return "Task [_id=" + _id + ", _description=" + _description + ", _departmentid=" + _departmentid
				+ ", _department=" + _department + "]";
	}
    
}

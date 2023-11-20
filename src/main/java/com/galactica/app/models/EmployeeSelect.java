/*
 * File name: EmployeeSelect.java
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
 * EmployeeSelect entity.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
public class EmployeeSelect {
    
	private int _id;
    private String _fullname;
    private int _departmentid;
	
    /**
     * Constructor.
     */
	public EmployeeSelect() {
		
	}

	/**
     * Constructor.
     */
    public EmployeeSelect(int id, String fullname, int departmentid) {
		super();
		this._id = id;
		this._fullname = fullname;
		this._departmentid = departmentid;
	}
    
    public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}
    
    public String getFullname() {
		return _fullname;
	}

	public void setFullname(String fullname) {
		this._fullname = fullname;
	}
    
    public int getDepartmentid() {
		return _departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this._departmentid = departmentid;
	}

}

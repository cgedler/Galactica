/*
 * File name: DepartmentSelect.java
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

import java.util.Arrays;

/**
 * DepartmentSelect entity.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
public class DepartmentSelect {

	private int _id;
    private String _description;
    private int[] _values;
    
    /**
     * Constructor.
     */
    public DepartmentSelect() {
		super();
	}

    /**
     * Constructor.
     */
	public DepartmentSelect(int _id, String _description, int[] _values) {
		super();
		this._id = _id;
		this._description = _description;
		this._values = _values;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public int[] get_values() {
		return _values;
	}

	public void set_values(int[] _values) {
		this._values = _values;
	}

	@Override
	public String toString() {
		return "DepartmentSelect [_id=" + _id + ", _description=" + _description + ", _values="
				+ Arrays.toString(_values) + "]";
	}
    
}

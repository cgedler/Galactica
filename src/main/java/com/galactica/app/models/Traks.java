/*
 * File name: Traks.java
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
 * Traks entity.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
public class Traks {

	private int _id;
	private String _transaction;
	private String _comments;
	
	/**
     * Constructor.
     */
	public Traks() {
		
	}
	
	/**
     * Constructor.
     */
	public Traks(int id, String transaction, String comments) {
		super();
		this._id = id;
		this._transaction = transaction;
		this._comments = comments;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int id) {
		this._id = id;
	}

	public String get_transaction() {
		return _transaction;
	}

	public void set_transaction(String transaction) {
		this._transaction = transaction;
	}

	public String get_comments() {
		return _comments;
	}

	public void set_comments(String comments) {
		this._comments = comments;
	}
	
}

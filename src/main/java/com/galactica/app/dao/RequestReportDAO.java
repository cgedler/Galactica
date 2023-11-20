/*
 * File name: RequestReportDAO.java
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
public interface RequestReportDAO {
    
	/**
     * Get user as Optional by username.
     *
     * @param username unique identifier of the user.
     * @return an optional with user if a user with unique identifier <code>username</code> exists.
     */
	public int getReportByDepartment(int departmentid, char status);
	
	// All Requests by sender show all with any status
	// if status is closed can do qualification and comments
	/**
     * Get user as Optional by username.
     *
     * @param username unique identifier of the user.
     * @return an optional with user if a user with unique identifier <code>username</code> exists.
     */
	public int[] getReportByDepartmentYear(int departmentid, char status, int year);
	
}

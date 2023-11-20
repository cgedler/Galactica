/*
 * File name: RequestDAO.java
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

import com.galactica.app.models.Request;
import com.galactica.app.models.RequestQualification;
import com.galactica.app.models.QualifyValue;

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
public interface RequestDAO {
    
	/**
	   * Get all Requests by department.
	   *
	   * @param departmentid unique identifier of the Department.
	   * @return a list of all Requests by department.
	   */
	public List<Request> getReqDepartment(int departmentid);
	
	/**
	   * Get all Requests by sender.
	   *
	   * @param userId unique identifier of the User.
	   * @param status filter requests by status.
	   * @return a list of all Requests by user and status.
	   */
	public List<Request> getReqSender(int userId, char status); //
	
	/**
	   * Get all Requests by receiver.
	   *
	   * @param userId unique identifier of the User.
	   * @param status filter requests by status.
	   * @return a list of all Requests by user and status.
	   */
	public List<Request> getReqReceiver(int userId, char status); //
	
	/**
     * Get details of the Request as Optional by id.
     *
     * @param id unique identifier of the Request.
     * @return a Request object optional with id if a request with unique identifier <code>id</code> exists.
     */
	public Request getReqDetails(int id); //
    
	/**
     * Add a Request.
     *
     * @param theRequest the object to be added.
     * @param userId unique identifier of the User.
     */
    public void keepNewRequest(Request theRequest, int userId); //
    
    /**
     * Update a Request (put the Request started).
     *
     * @param theRequest the object to be update.
     */
    public void keepRequestStart(Request theRequest); //
    
    /**
     * Update a Request (put the Request stopped).
     *
     * @param theRequest the object to be update.
     */
    public void keepRequestStop(Request theRequest); //
    
    /**
     * Add a RequestQualification.
     *
     * @param theQualify the object to be added.
     */
    public void keepQualify(RequestQualification theQualify); //
    
    /**
	   * Get all RequestsQualification by user.
	   *
	   * @param userId unique identifier of the User.
	   * @return a list of all RequestsQualification by user.
	   */
 	public List<RequestQualification> getMyQualifys(int userId); //
 	
 	/**
	   * Get a count of Qualify by user.
	   *
	   * @param userId unique identifier of the User.
	   * @return a list of all QualifyValue by user.
	   */
	public List<QualifyValue> getMyQualifyCount(int userId); //
 	
    
}

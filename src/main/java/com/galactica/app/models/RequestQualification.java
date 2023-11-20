/*
 * File name: RequestQualification.java
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

import java.util.Date;

/**
 * RequestQualification entity.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
public class RequestQualification {
	
	private int _id;
	private String _comments;
	private int _qualificationid;
	private Date _creation;
	private String _qualification;
	private int _receiverid;
    private String _receiver;
    
    /**
     * Constructor.
     */
	public RequestQualification() {
		
	}
	
	/**
     * Constructor.
     */
	public RequestQualification(int id, String comments, int qualificationid) {
		super();
		this._id = id;
		this._comments = comments;
		this._qualificationid = qualificationid;
	}

	/**
     * Constructor.
     */
	public RequestQualification(int id, String comments, int qualificationid, Date creation, String qualification,
			int receiverid, String receiver) {
		super();
		this._id = id;
		this._comments = comments;
		this._qualificationid = qualificationid;
		this._creation = creation;
		this._qualification = qualification;
		this._receiverid = receiverid;
		this._receiver = receiver;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		this._comments = comments;
	}

	public int getQualificationid() {
		return _qualificationid;
	}

	public void setQualificationid(int qualificationid) {
		this._qualificationid = qualificationid;
	}

	public Date getCreation() {
		return _creation;
	}

	public void setCreation(Date creation) {
		this._creation = creation;
	}

	public String getQualification() {
		return _qualification;
	}

	public void setQualification(String qualification) {
		this._qualification = qualification;
	}

	public int getReceiverid() {
		return _receiverid;
	}

	public void setReceiverid(int receiverid) {
		this._receiverid = receiverid;
	}

	public String getReceiver() {
		return _receiver;
	}

	public void setReceiver(String receiver) {
		this._receiver = receiver;
	}
	
}

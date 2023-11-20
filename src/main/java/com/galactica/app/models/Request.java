/*
 * File name: Request.java
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

import java.sql.Timestamp;
import java.util.Date;

/**
 * Request entity.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
public class Request {
    
	private int _id;
	private Date _creation;
    private Timestamp _startactivity;
    private Timestamp _endactivity;
	private String _comments;
    private boolean _active;
    private int _taskid;
    private String _task;
    private int _priorityid;
    private String _priority;
    private int _senderid;
    private String _sender;
    private int _receiverid;
    private String _receiver;
    private int _departmentid;
    private String _department;
    
    /**
     * Constructor.
     */
    public Request() {
    	
    }

    /**
     * Constructor.
     */
	public Request(int id, Date creation, Timestamp startactivity, Timestamp endactivity, String comments,
			boolean active, int taskid, String task, int priorityid, String priority, int senderid, String sender, int receiverid,
			String receiver, int departmentid, String department) {
		super();
		this._id = id;
		this._creation = creation;
		this._startactivity = startactivity;
		this._endactivity = endactivity;
		this._comments = comments;
		this._active = active;
		this._taskid = taskid;
		this._task = task;
		this._priorityid = priorityid;
		this._priority = priority;
		this._senderid = senderid;
		this._sender = sender;
		this._receiverid = receiverid;
		this._receiver = receiver;
		this._departmentid = departmentid;
		this._department = department;
	}
	
	/**
     * Constructor.
     */
	public Request(int id, Date creation, Timestamp startactivity, Timestamp endactivity, String comments,
			boolean active, String task, String priority, String sender, String receiver, String department) {
		super();
		this._id = id;
		this._creation = creation;
		this._startactivity = startactivity;
		this._endactivity = endactivity;
		this._comments = comments;
		this._active = active;
		this._task = task;
		this._priority = priority;
		this._sender = sender;
		this._receiver = receiver;
		this._department = department;
	}

	/**
     * Constructor.
     */
	public Request(int id, Date creation, Timestamp startactivity, Timestamp endactivity, String comments,
			boolean active, int taskid, int priorityid, int senderid, int receiverid, int departmentid) {
		super();
		this._id = id;
		this._creation = creation;
		this._startactivity = startactivity;
		this._endactivity = endactivity;
		this._comments = comments;
		this._active = active;
		this._taskid = taskid;
		this._priorityid = priorityid;
		this._senderid = senderid;
		this._receiverid = receiverid;
		this._departmentid = departmentid;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public Date getCreation() {
		return _creation;
	}

	public void setCreation(Date creation) {
		this._creation = creation;
	}

	public Timestamp getStartactivity() {
		return _startactivity;
	}

	public void setStartactivity(Timestamp startactivity) {
		this._startactivity = startactivity;
	}

	public Timestamp getEndactivity() {
		return _endactivity;
	}

	public void setEndactivity(Timestamp endactivity) {
		this._endactivity = endactivity;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		this._comments = comments;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		this._active = active;
	}

	public int getTaskid() {
		return _taskid;
	}

	public void setTaskid(int taskid) {
		this._taskid = taskid;
	}

	public String getTask() {
		return _task;
	}

	public void setTask(String task) {
		this._task = task;
	}

	public int getPriorityid() {
		return _priorityid;
	}

	public void setPriorityid(int priorityid) {
		this._priorityid = priorityid;
	}

	public String getPriority() {
		return _priority;
	}

	public void setPriority(String priority) {
		this._priority = priority;
	}

	public int getSenderid() {
		return _senderid;
	}

	public void setSenderid(int senderid) {
		this._senderid = senderid;
	}

	public String getSender() {
		return _sender;
	}

	public void setSender(String sender) {
		this._sender = sender;
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
    
}

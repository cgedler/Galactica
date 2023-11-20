/*
 * File name: RequestDAOimpl.java
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

package com.galactica.app.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.galactica.app.dao.RequestDAO;
import com.galactica.app.models.Request;
import com.galactica.app.models.RequestQualification;
import com.galactica.app.models.QualifyValue;

/**
 * An implementation of {@link RequestDAO} that persists Request in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class RequestDAOimpl implements RequestDAO {

	@Autowired
    private DataSource getDataSource;
	
	@Override
    public ArrayList<Request> getReqDepartment(int departmentid) {
        ArrayList<Request> listRequests = new ArrayList<Request>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, creation, startactivity, endactivity, comments, active, task, priority, sender, receiver, departmentid,"
            		+ "department FROM view_request WHERE departmentid=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, departmentid);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date creation = rs.getTimestamp("creation");
                Timestamp startactivity = rs.getTimestamp("startactivity");
                Timestamp endactivity = rs.getTimestamp("endactivity");
                String comments = rs.getString("comments");;
                boolean active = rs.getBoolean("active");
                String task = rs.getString("task");;
                String priority = rs.getString("priority");;
                String sender = rs.getString("sender");;
                String receiver = rs.getString("receiver");;
                String department = rs.getString("department");
                Request tempRequest = new Request(id, creation, startactivity, endactivity, comments, active, task, priority, sender, receiver, department);
                listRequests.add(tempRequest);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listRequests;
    }

	@Override
    public ArrayList<Request> getReqSender(int userId, char status) {
        ArrayList<Request> listRequests = new ArrayList<Request>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, creation, startactivity, endactivity, comments, active, task, priority, sender, receiver, departmentid,"
            		+ "department FROM view_request WHERE active='true' AND senderid=? AND status=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, userId);
            p.setString(2, String.valueOf(status));
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date creation = rs.getTimestamp("creation");
                Timestamp startactivity = rs.getTimestamp("startactivity");
                Timestamp endactivity = rs.getTimestamp("endactivity");
                String comments = rs.getString("comments");;
                boolean active = rs.getBoolean("active");
                String task = rs.getString("task");;
                String priority = rs.getString("priority");;
                String sender = rs.getString("sender");;
                String receiver = rs.getString("receiver");;
                String department = rs.getString("department");
                Request tempRequest = new Request(id, creation, startactivity, endactivity, comments, active, task, priority, sender, receiver, department);
                listRequests.add(tempRequest);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listRequests;
    }
	
	@Override
	public ArrayList<Request> getReqReceiver(int userId, char status) {
		ArrayList<Request> listRequests = new ArrayList<Request>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, creation, startactivity, endactivity, comments, active, task, priority, sender, receiver, departmentid,"
            		+ "department FROM view_request WHERE active='true' AND receiverid=? AND status=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, userId);
            p.setString(2, String.valueOf(status));
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date creation = rs.getTimestamp("creation");
                Timestamp startactivity = rs.getTimestamp("startactivity");
                Timestamp endactivity = rs.getTimestamp("endactivity");
                String comments = rs.getString("comments");;
                boolean active = rs.getBoolean("active");
                String task = rs.getString("task");;
                String priority = rs.getString("priority");;
                String sender = rs.getString("sender");;
                String receiver = rs.getString("receiver");;
                String department = rs.getString("department");
                Request tempRequest = new Request(id, creation, startactivity, endactivity, comments, active, task, priority, sender, receiver, department);
                listRequests.add(tempRequest);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listRequests;
	}

	@Override
	public ArrayList<RequestQualification> getMyQualifys(int userId) {
		ArrayList<RequestQualification> listQualifys = new ArrayList<RequestQualification>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, creation, comments, qualificationid, qualification, receiverid, receiver"
            		+ " FROM view_requestqualification WHERE receiverid=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date creation = rs.getTimestamp("creation");
                String comments = rs.getString("comments");
                int qualificationid = rs.getInt("qualificationid"); 
                String qualification = rs.getString("qualification");
                int receiverid = rs.getInt("receiverid");
                String receiver = rs.getString("receiver");
                RequestQualification tempQualify = new RequestQualification(id, comments, qualificationid, creation, qualification, receiverid, receiver);
                listQualifys.add(tempQualify);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listQualifys;
	}
	
	@Override
	public ArrayList<QualifyValue> getMyQualifyCount(int userId) {
		ArrayList<QualifyValue> valuesQualifys = new ArrayList<QualifyValue>();
		try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT qualificationid, COUNT(id) AS result FROM view_requestqualification WHERE receiverid=?"
            		+ "	GROUP BY qualificationid";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	 int id = rs.getInt("qualificationid");
            	 int value = rs.getInt("result");
            	 QualifyValue tempQualifyValue = new QualifyValue(id, value);
            	 valuesQualifys.add(tempQualifyValue);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
		return valuesQualifys;
	}
	
    @Override
    public Request getReqDetails(int id) {
    	Request theRequest = new Request();
    	try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, creation, startactivity, endactivity, comments, active, taskid, task, priorityid, priority,"
            		+ "senderid, sender, receiverid, receiver, departmentid, department FROM view_request WHERE id=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Date creation = rs.getTimestamp("creation");
                Timestamp startactivity = rs.getTimestamp("startactivity");
                Timestamp endactivity = rs.getTimestamp("endactivity");
                String comments = rs.getString("comments");
                boolean active = rs.getBoolean("active");
                int taskid = rs.getInt("taskid");
                String task = rs.getString("task");
                int priorityid = rs.getInt("priorityid");
                String priority = rs.getString("priority");
                int senderid = rs.getInt("senderid");
                String sender = rs.getString("sender");
                int receiverid = rs.getInt("receiverid");
                String receiver = rs.getString("receiver");
                int departmentid = rs.getInt("departmentid");
                String department = rs.getString("department");
                theRequest.setId(id);
                theRequest.setCreation(creation);
                theRequest.setStartactivity(startactivity);
                theRequest.setEndactivity(endactivity);
                theRequest.setComments(comments);
                theRequest.setActive(active);
                theRequest.setTaskid(taskid);
                theRequest.setTask(task);
                theRequest.setPriorityid(priorityid);
                theRequest.setPriority(priority);
                theRequest.setSenderid(senderid);
                theRequest.setSender(sender);
                theRequest.setReceiverid(receiverid);
                theRequest.setReceiver(receiver);
                theRequest.setDepartmentid(departmentid);
                theRequest.setDepartment(department);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }    	
    	return theRequest;
    }
     
    @Override
    public void keepRequestStart(Request theRequest) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spupdate_reqstart(?, ?)}");
			miStatement.setInt(1, theRequest.getId());
			miStatement.setString(2, theRequest.getComments());
			miStatement.execute();
	        conn.commit();
	        miStatement.close();
	        conn.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        	 try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
    @Override
    public void keepRequestStop(Request theRequest) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spupdate_reqstop(?, ?)}");
			miStatement.setInt(1, theRequest.getId());
			miStatement.setString(2, theRequest.getComments());
			miStatement.execute();
	        conn.commit();
	        miStatement.close();
	        conn.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        	 try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
    @Override
    public void keepQualify(RequestQualification theQualify) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spinsert_requestqualification(?, ?, ?)}");
			miStatement.setInt(1, theQualify.getId());
			miStatement.setString(2, theQualify.getComments());
			miStatement.setInt(3, theQualify.getQualificationid());
			miStatement.execute();
	        conn.commit();
	        miStatement.close();
	        conn.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        	 try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
 	@Override
	public void keepNewRequest(Request theRequest, int userId) {
		Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spinsert_requests(?, ?, ?, ?, ?, ?)}");
			miStatement.setString(1, theRequest.getComments());
			miStatement.setInt(2, theRequest.getTaskid());
			miStatement.setInt(3, theRequest.getPriorityid());
			miStatement.setInt(4, userId);
			miStatement.setInt(5, theRequest.getReceiverid());
			miStatement.setInt(6, theRequest.getDepartmentid());
	        miStatement.execute();
	        conn.commit();
	        miStatement.close();
	        conn.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        	 try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}

}

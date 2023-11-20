/*
 * File name: TaskDAOimpl.java
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
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.galactica.app.dao.TaskDAO;
import com.galactica.app.models.Task;

/**
 * An implementation of {@link TaskDAO} that persists Task in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class TaskDAOimpl implements TaskDAO {

	@Autowired
    private DataSource getDataSource;
	
	@Override
    public ArrayList<Task> getTasks() {
        ArrayList<Task> listTasks = new ArrayList<Task>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, description, department FROM view_tasks_departments";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String department = rs.getString("department");
                Task tempTask = new Task(id, description, department);
                listTasks.add(tempTask);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listTasks;
    }
    
    @Override
    public ArrayList<Task> getTasksSelect() {
        ArrayList<Task> listTasks = new ArrayList<Task>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, description, departmentid FROM view_tasks_departments";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                int departmentid = rs.getInt("departmentid");
                Task tempTask = new Task(id, description, departmentid);
                listTasks.add(tempTask);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listTasks;
    }

    @Override
    public Task getTask(int id) {
    	Task theTask = new Task();
    	try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, description, departmentid, department FROM view_tasks_departments WHERE id=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                String department = rs.getString("department");
                theTask.setId(id);
                theTask.setDescription(description);
                theTask.setDepartment(department);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }    	
    	return theTask;
    }

    @Override
    public void saveTask(Task theTask) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spupdate_tasks(?, ?, ?)}");
			miStatement.setInt(1, theTask.getId());
	        miStatement.setString(2, theTask.getDescription());
            miStatement.setInt(3, theTask.getDepartmentid());
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
	public void insertNewTask(Task theTask) {
		Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spinsert_tasks(?, ?)}");
	        miStatement.setString(1, theTask.getDescription());
            miStatement.setInt(2, theTask.getDepartmentid());
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
    public void eliminateTask(int id) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
        	miStatement = conn.prepareCall("{call spdelete_tasks(?)}");
			miStatement.setInt(1, id);
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

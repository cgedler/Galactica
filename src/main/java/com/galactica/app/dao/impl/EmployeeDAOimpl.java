/*
 * File name: EmployeeDAOimpl.java
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

import com.galactica.app.dao.EmployeeDAO;
import com.galactica.app.models.Employee;
import com.galactica.app.models.EmployeeSelect;

/**
 * An implementation of {@link EmployeeDAO} that persists Employee in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class EmployeeDAOimpl implements EmployeeDAO {

	@Autowired
    private DataSource getDataSource;
	
	@Override
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> listEmployees = new ArrayList<Employee>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, name, lastname, active, username, department, departmentid FROM view_employees_departments";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                boolean active = rs.getBoolean("active");
                String username = rs.getString("username");
                String department = rs.getString("department");
                int departmentid = rs.getInt("departmentid");
                Employee tempEmployee = new Employee(id, name, lastname, active, username, department, departmentid);
                listEmployees.add(tempEmployee);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listEmployees;
    }

    @Override
    public ArrayList<EmployeeSelect> getEmployeeSelect(){
        ArrayList<EmployeeSelect> listEmployees = new ArrayList<EmployeeSelect>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, name, lastname, departmentid FROM view_employees_departments";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("name") + ' ' + rs.getString("lastname");
                int departmentid = rs.getInt("departmentid");
                EmployeeSelect tempEmployee = new EmployeeSelect(id, fullname, departmentid);
                listEmployees.add(tempEmployee);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listEmployees;
    }

    @Override
    public Employee getEmployee(int id) {
    	Employee theEmployee = new Employee();
    	try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, name, lastname, active, department, departmentid FROM view_employees_departments WHERE id=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                boolean active = rs.getBoolean("active");
                String department = rs.getString("department");
                int departmentid = rs.getInt("departmentid");
                theEmployee.setId(id);
                theEmployee.setName(name);
                theEmployee.setLastname(lastname);
                theEmployee.setActive(active);
                theEmployee.setDepartmentid(departmentid);
                theEmployee.setDepartment(department);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }    	
    	return theEmployee;
    }
    
    @Override
    public int getEmployeeId(String username) {
    	int theEmployeeId = 0;
    	try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id FROM view_employees_departments WHERE username=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setString(1, username);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	theEmployeeId = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }    	
    	return theEmployeeId;
    }
    
    @Override
    public void saveEmployee(Employee theEmployee) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spupdate_employees(?, ?, ?, ?, ?, ?)}");
			miStatement.setInt(1, theEmployee.getId());
            miStatement.setString(2, theEmployee.getName());
            miStatement.setString(3, theEmployee.getLastname());
	        miStatement.setBoolean(4, theEmployee.isActive());
            miStatement.setInt(5, theEmployee.getDepartmentid());
            miStatement.setString(6, theEmployee.getUsername());
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
	public void insertNewEmployee(Employee theEmployee) {
		Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spinsert_employees(?, ?, ?, ?, ?, ?)}");
			miStatement.setInt(1, theEmployee.getId());
            miStatement.setString(2, theEmployee.getName());
            miStatement.setString(3, theEmployee.getLastname());
	        miStatement.setBoolean(4, theEmployee.isActive());
            miStatement.setInt(5, theEmployee.getDepartmentid());
            miStatement.setString(6, theEmployee.getUsername());
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
    public void eliminateEmployee(int id) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
        	miStatement = conn.prepareCall("{call spdelete_employees(?)}");
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

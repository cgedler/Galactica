/*
 * File name: DepartmentDAOimpl.java
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

import com.galactica.app.dao.DepartmentDAO;
import com.galactica.app.models.Department;

/**
 * An implementation of {@link DepartmentDAO} that persists Department in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class DepartmentDAOimpl implements DepartmentDAO {
	
	@Autowired
    private DataSource getDataSource;
	
	@Autowired
	TraksDAOimpl TraksRepository;
	
	@Override
    public ArrayList<Department> getDepartments() {
        ArrayList<Department> listDepartments = new ArrayList<Department>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, description FROM departments";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                Department tempDepartment = new Department(id, description);
                listDepartments.add(tempDepartment);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listDepartments;
    }

    @Override
    public Department getDepartment(int id) {
    	Department theDepartment = new Department();
    	try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, description FROM departments WHERE id=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                theDepartment.setId(id);
                theDepartment.setDescription(description);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }    	
    	return theDepartment;
    }

    @Override
    public void saveDepartment(Department theDepartment) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spupdate_departments(?, ?)}");
			miStatement.setInt(1, theDepartment.getId());
	        miStatement.setString(2, theDepartment.getDescription());
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
	public void insertNewDepartment(Department theDepartment) {
		Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spinsert_departments(?, ?)}");
			miStatement.setInt(1, theDepartment.getId());
	        miStatement.setString(2, theDepartment.getDescription());
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
    public void eliminateDepartment(int id) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
        	miStatement = conn.prepareCall("{call spdelete_departments(?)}");
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

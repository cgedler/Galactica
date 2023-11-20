/*
 * File name: PriorityDAOimpl.java
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

import com.galactica.app.dao.PriorityDAO;
import com.galactica.app.models.Priority;

/**
 * An implementation of {@link PriorityDAO} that persists Priority in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class PriorityDAOimpl implements PriorityDAO {

	@Autowired
    private DataSource getDataSource;
	
	@Override
    public ArrayList<Priority> getPrioritys() {
        ArrayList<Priority> listPrioritys = new ArrayList<Priority>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, description FROM priority";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                Priority tempPriority = new Priority(id, description);
                listPrioritys.add(tempPriority);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listPrioritys;
    }

    @Override
    public Priority getPriority(int id) {
    	Priority thePriority = new Priority();
    	try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, description FROM priority WHERE id=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                thePriority.setId(id);
                thePriority.setDescription(description);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }    	
    	return thePriority;
    }

    @Override
    public void savePriority(Priority thePriority) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spupdate_priority(?, ?)}");
			miStatement.setInt(1, thePriority.getId());
	        miStatement.setString(2, thePriority.getDescription());
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
	public void insertNewPriority(Priority thePriority) {
		Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spinsert_priority(?, ?)}");
			miStatement.setInt(1, thePriority.getId());
	        miStatement.setString(2, thePriority.getDescription());
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
    public void eliminatePriority(int id) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
        	miStatement = conn.prepareCall("{call spdelete_priority(?)}");
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

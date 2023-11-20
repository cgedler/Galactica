/*
 * File name: QualificationDAOimpl.java
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

import com.galactica.app.dao.QualificationDAO;
import com.galactica.app.models.Qualification;

/**
 * An implementation of {@link QualificationDAO} that persists Qualification in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class QualificationDAOimpl implements QualificationDAO {

	@Autowired
    private DataSource getDataSource;
	
	@Override
    public ArrayList<Qualification> getQualifications() {
        ArrayList<Qualification> listQualifications = new ArrayList<Qualification>();
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, qualification FROM qualification";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String qualification = rs.getString("qualification");
                Qualification tempQualification = new Qualification(id, qualification);
                listQualifications.add(tempQualification);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listQualifications;
    }

    @Override
    public Qualification getQualification(int id) {
    	Qualification theQualification = new Qualification();
    	try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT id, qualification FROM qualification WHERE id=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String qualification = rs.getString("qualification");
                theQualification.setId(id);
                theQualification.setQualification(qualification);
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }    	
    	return theQualification;
    }

    @Override
    public void saveQualification(Qualification theQualification) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spupdate_qualification(?, ?)}");
			miStatement.setInt(1, theQualification.getId());
	        miStatement.setString(2, theQualification.getQualification());
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
	public void insertNewQualification(Qualification theQualification) {
		Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spinsert_qualification(?, ?)}");
			miStatement.setInt(1, theQualification.getId());
	        miStatement.setString(2, theQualification.getQualification());
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
    public void eliminateQualification(int id) {
        Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
        	miStatement = conn.prepareCall("{call spdelete_qualification(?)}");
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

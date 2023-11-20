/*
 * File name: UsersDAOimpl.java
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

import com.galactica.app.dao.UsersDAO;
import com.galactica.app.models.Users;

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

/**
 * An implementation of {@link UsersDAO} that persists Users in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class UsersDAOimpl implements UsersDAO {

    @Autowired
    private DataSource getDataSource;
    
    @Override
    public ArrayList<Users> getUsers() {
        ArrayList<Users> listUsers = new ArrayList<Users>();
        ResultSet rsB = null;
        try (Connection conn = getDataSource.getConnection())
        {
            String  queryA = "SELECT username, enabled FROM users WHERE enabled='true'";
            Statement sA = conn.createStatement();
            ResultSet rsA = sA.executeQuery(queryA);
            while (rsA.next()) {
                String username = rsA.getString("username");
                ArrayList<String> authority = new ArrayList<String>();
                String  queryB = "SELECT authority FROM authorities WHERE username=?";
                PreparedStatement p = conn.prepareStatement(queryB);
                p.setString(1, username);
                rsB = p.executeQuery();
                while(rsB.next()) {
                    authority.add(rsB.getString("authority"));
                }
                boolean enabled = rsA.getBoolean("enabled");
                Users tempUser = new Users(username, enabled, authority);
                listUsers.add(tempUser);
            }
            rsA.close();
            rsB.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public Users getUser(String username) {
    	Users theUser = new Users();
    	ResultSet rsB = null;
    	try (Connection conn = getDataSource.getConnection())
        {
            String  queryA = "SELECT username, enabled FROM users WHERE username=?";
            PreparedStatement pA = conn.prepareStatement(queryA);
            pA.setString(1, username);
            ResultSet rsA = pA.executeQuery();
            while (rsA.next()) {
                ArrayList<String> authority = new ArrayList<String>();
                String  queryB = "SELECT authority FROM authorities WHERE username=?";
                PreparedStatement p = conn.prepareStatement(queryB);
                p.setString(1, username);
                rsB = p.executeQuery();
                while(rsB.next()) {
                    authority.add(rsB.getString("authority"));
                }
                boolean enabled = rsA.getBoolean("enabled");
                theUser.setUsername(username);
                theUser.setEnabled(enabled);
                theUser.setAuthority(authority);
            }
            rsA.close();
            rsB.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }    	
    	return theUser;
    }

    @Override
    public void saveUser(Users theUser) {
        Connection conn = null;
		CallableStatement miStatementA = null;
		CallableStatement miStatementB = null;
		CallableStatement miStatementC = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatementA = conn.prepareCall("{call spupdate_users(?, ?, ?)}");
			miStatementA.setString(1, theUser.getUsername());
	        miStatementA.setString(2, theUser.getPassword());
	        miStatementA.setBoolean(3, theUser.isEnabled());
	        miStatementA.execute();
	        //clean
        	miStatementC = conn.prepareCall("{call spdelete_authorities(?)}");
        	miStatementC.setString(1, theUser.getUsername());
        	miStatementC.execute();
	        for(String elemento:theUser.getAuthority()){
	        	//insert
	        	miStatementB = conn.prepareCall("{call spinsert_authorities(?, ?)}");
				miStatementB.setString(1, theUser.getUsername());
		        miStatementB.setString(2, elemento);
		        miStatementB.execute();	            
	        }
	        conn.commit();
	        miStatementA.close();
	        miStatementB.close();
	        miStatementC.close();
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
	public void insertNewUser(Users theUser) {
		Connection conn = null;
		CallableStatement miStatementA = null;
		CallableStatement miStatementB = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatementA = conn.prepareCall("{call spinsert_users(?, ?, ?)}");
			miStatementA.setString(1, theUser.getUsername());
	        miStatementA.setString(2, theUser.getPassword());
	        miStatementA.setBoolean(3, true);
	        miStatementA.execute();
	        for(String elemento:theUser.getAuthority()){
	            //System.out.println("Permiso : " + elemento);	            
	            miStatementB = conn.prepareCall("{call spinsert_authorities(?, ?)}");
				miStatementB.setString(1, theUser.getUsername());
		        miStatementB.setString(2, elemento);
		        miStatementB.execute();	            
	        }
	        conn.commit();
	        miStatementA.close();
	        miStatementB.close();
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
    public void eliminateUser(String username) {
        Connection conn = null;
		CallableStatement miStatementA = null;
		CallableStatement miStatementC = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
        	miStatementC = conn.prepareCall("{call spdelete_authorities(?)}");
        	miStatementC.setString(1, username);
        	miStatementC.execute();
        	miStatementA = conn.prepareCall("{call spdelete_users(?)}");
			miStatementA.setString(1, username);
	        miStatementA.execute();
	        conn.commit();
	        miStatementA.close();
	        miStatementC.close();
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

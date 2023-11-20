/*
 * File name: TraksDAOimpl.java
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
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.galactica.app.dao.TraksDAO;
import com.galactica.app.models.Traks;

/**
 * An implementation of {@link TraksDAO} that persists Traks in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class TraksDAOimpl implements TraksDAO {
	
	@Autowired
    private DataSource getDataSource;

	@Override
	public void recordTraks(Traks theTraks) {
		Connection conn = null;
		CallableStatement miStatement = null;
		try
		{
			conn = getDataSource.getConnection();
			conn.setAutoCommit(false);
			miStatement = conn.prepareCall("{call spinsert_traks(?)}");
			String comment = Integer.toString(theTraks.get_id()) + ' ' + theTraks.get_transaction() + ' ' + theTraks.get_comments(); 
			miStatement.setString(1, comment);
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

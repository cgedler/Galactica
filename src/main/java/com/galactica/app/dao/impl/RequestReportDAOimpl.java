package com.galactica.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.galactica.app.dao.RequestReportDAO;

/**
 * An implementation of {@link RequestReportDAO} that return data of RequestReport in RDBMS.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since 2023
 * 
 */
@Repository
public class RequestReportDAOimpl implements RequestReportDAO {
	
	@Autowired
    private DataSource getDataSource;

	@Override
	public int getReportByDepartment(int departmentid, char status) {
		int result = 0;
        try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT COUNT(id) AS result FROM view_request WHERE departmentid=? AND status=?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, departmentid);
            p.setString(2, String.valueOf(status));
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	result = rs.getInt("result");
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
        return result;
	}

	@Override
	public int[] getReportByDepartmentYear(int departmentid, char status, int year) {
		int[] result = new int[12];
		try (Connection conn = getDataSource.getConnection())
        {
            String  query = "SELECT DATE_PART('month', creation) AS month, COUNT(id) AS count FROM public.view_request"
            		+ " WHERE DATE_PART('YEAR', creation)=? AND departmentid=? AND status=? GROUP BY DATE_PART('month', creation)";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, year);
            p.setInt(2, departmentid);
            p.setString(3, String.valueOf(status));
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	if (rs.getInt("month")==1) {
            		result[0] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==2) {
            		result[1] = rs.getInt("count");
            	}		
            	else if(rs.getInt("month")==3) {
            		result[2] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==4) {
            		result[3] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==5) {
            		result[4] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==6) {
            		result[5] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==7) {
            		result[6] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==8) {
            		result[7] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==9) {
            		result[8] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==10) {
            		result[9] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==11) {
            		result[10] = rs.getInt("count");
            	}
            	else if(rs.getInt("month")==12) {
            		result[11] = rs.getInt("count");
            	}
            }
            rs.close();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
		return result;
	}

}
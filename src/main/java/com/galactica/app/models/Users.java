/*
 * File name: Users.java
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

import java.util.List;

/**
* User entity.
* 
* @author Christopher Gedler <cgedler@gmail.com>
* @version 1.0
* @since 2023
* 
*/
public class Users {
    
    private String _username;
    private String _password;
    private boolean _enabled;
    private List<String> _authoritys;
	
    /**
     * Constructor.
     */
    public Users() {
    	
    }

    /**
     * Constructor.
     */
    public Users(String username, String password, boolean enabled) {
    	super();
    	this._username = username;
    	this._password = password;
    	this._enabled = enabled;
    }
   
    /**
     * Constructor.
     */
    public Users(String username, boolean enabled, List<String> authority) {
    	super();
    	this._username = username;
    	this._enabled = enabled;
    	this._authoritys = authority;
    }

    public String getUsername() {
    	return _username;
    }

    public void setUsername(String username) {
    	this._username = username;
    }

    public String getPassword() {
    	return _password;
    }

    public void setPassword(String password) {
    	this._password = password;
    }

    public boolean isEnabled() {
    	return _enabled;
    }

    public void setEnabled(boolean enabled) {
    	this._enabled = enabled;
    }
 
    public List<String> getAuthority() {
    	return _authoritys;
    }

    public void setAuthority(List<String> authoritys) {
    	this._authoritys = authoritys;
    }

	@Override
	public String toString() {
		return "Users [_username=" + _username + ", _password=" + _password + ", _enabled=" + _enabled
				+ ", _authoritys=" + _authoritys + "]";
	}

}

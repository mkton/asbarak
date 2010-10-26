package org.ow2.asbarak.user;

import org.ow2.asbarak.user.AsbarakUsersImpl.AsbarakUser;

public interface AsbarakUsersService {
	public AsbarakUser getUser(String login, String pwd);
}

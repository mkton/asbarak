package org.ow2.asbarak.user;

import org.osoa.sca.annotations.Service;
import org.ow2.asbarak.user.AsbarakUsersImpl.AsbarakUser;

@Service
public interface AsbarakUsersService {
	public AsbarakUser getUser(String login, String pwd);
	
	public AsbarakUser getUser(Integer id);
}

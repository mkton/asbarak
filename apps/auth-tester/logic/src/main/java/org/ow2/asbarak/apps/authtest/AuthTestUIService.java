package org.ow2.asbarak.apps.authtest;

import org.osoa.sca.annotations.Service;

@Service
public interface AuthTestUIService {
	public String getUserInformations(Integer id);
}

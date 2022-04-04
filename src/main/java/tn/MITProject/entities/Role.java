package tn.MITProject.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ADMIN,AGENT,COMPANYCLIENT,PARTICULARCLIENT,EXPERT;
	
	@Override
	public String getAuthority() {

		return "ROLE_" + name();

		}

}

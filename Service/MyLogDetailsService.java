package tn.MITProject.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Log;

@Service
public class MyLogDetailsService implements UserDetailsService {
	
	@Autowired
	private LogService logService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Log log = logService.findLogByLogEmail(email);
		if (log == null) {
            throw new UsernameNotFoundException(email +"Not Found");
        }
		List<GrantedAuthority> authorities = getUserAuthority(log);
		return new User(log.getEmail(), log.getPassword(), log.getActive(), true, true, true, authorities);
				
	}
	private List<GrantedAuthority> getUserAuthority(Log log){
        /*List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(log.getRole().getAuthority()));
        return authorities;*/
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority(log.getRole().getAuthority()));      
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;  
	}

}

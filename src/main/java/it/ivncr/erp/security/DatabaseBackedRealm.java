package it.ivncr.erp.security;


import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.security.SecurityService;
import it.ivncr.erp.service.utente.UtenteService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class DatabaseBackedRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		// Null username is invalid.
		//
		if (username == null) {
			throw new AccountException(
					"Null usernames are not allowed by this realm.");
		}

		// Lookup user.
		//
		UtenteService us = ServiceFactory.createService("Utente");
		Utente utente = us.retrieveByUsername(username);
		if (utente == null) {
			throw new UnknownAccountException(String.format("No account has been found for user [%s].", username));

		}
		
		// Check if the user is enabled.
		//
		if(!utente.getAttivo()) {
			throw new DisabledAccountException(String.format("Account for user [%s] has been disabled.", username));
		}
		
		// Extract digested password informations.
		//
		String digest = utente.getDigest();
		String salt = utente.getSalt();
		Integer iterations = utente.getIterazioni();

		// Create authentication info.
		//
		String realm = getName();
		SaltedWithIterationAuthenticationInfo info = 
				new SaltedWithIterationAuthenticationInfo(username, digest, realm);
		
		// Set up digest info.
		//
		info.setIterations(iterations);
		info.setSalt(salt);

		// Set up user details as a secondary principal.
		//
		info.addPrincipal(utente, realm);
		
		// Always clean up cached authorization after a login.
		//
		clearCachedAuthorizationInfo(info.getPrincipals());

		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		// Null usernames are invalid.
		//
		if (principals == null) {
			throw new AuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}

		// Extract username from principal.
		//
		String username = (String) getAvailablePrincipal(principals);

		// Look up roles.
		//
		SecurityService ss = ServiceFactory.createService("Security");
		List<Ruolo> rolesList = ss.getRoles(username);
		Set<String> roles = new HashSet<String>();
		for (Ruolo r : rolesList)
			roles.add(r.getNome());

		// Look up permissions.
		//
		List<Permesso> permissionsList = ss.getPermissions(username);
		Set<String> permissions = new HashSet<String>();
		for (Permesso p : permissionsList)
			permissions.add(p.getPermesso());

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		info.setStringPermissions(permissions);		
		
		return info;
	}

}

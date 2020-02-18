package br.com.zen.catalogo.jwt.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by fan.jin on 2017-04-04.
 */

public class AnonAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -6569560710366380609L;

	public AnonAuthentication() {
        super( null );
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        return true;
    }


}

/**
 * 
 */
package com.mom.webapp.services;

import com.mom.webapp.dto.CustomerDto;
import com.mom.webapp.exceptions.EmailAlreadyExistsException;
import com.mom.webapp.model.Credentials;
import com.mom.webapp.model.Customer;
import com.mom.webapp.model.EmailVerifToken;

/**
 * @author Brehima
 *
 */
public interface UserService {
	
 public Customer registerNewUser(CustomerDto userDto) throws EmailAlreadyExistsException;

public void createUserEmailVerifToken(Credentials user, String token);

public String validateEmailVerifToken(String token);

public Credentials getUserCredentials(String token);

public Credentials findUserCredentialsByEmail(String userEmail);

public void createUserPasswordResetToken(Credentials user, String token);

public EmailVerifToken generateNewEmailVerifToken(String existingToken);

Customer getCurrentAuthenticatedUser();
}

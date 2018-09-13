/**
 * 
 */
package com.mom.webapp.services.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mom.webapp.dao.CredentialsRepository;
import com.mom.webapp.dao.CustomerRepository;
import com.mom.webapp.dao.EmailVerifTokenRepository;
import com.mom.webapp.dao.PasswordResetTokenRepository;
import com.mom.webapp.dao.RoleRepository;
import com.mom.webapp.dto.AddressDto;
import com.mom.webapp.dto.CustomerDto;
import com.mom.webapp.exceptions.EmailAlreadyExistsException;
import com.mom.webapp.model.Credentials;
import com.mom.webapp.model.Customer;
import com.mom.webapp.model.EmailVerifToken;
import com.mom.webapp.model.PasswordResetToken;
import com.mom.webapp.model.Role;
import com.mom.webapp.services.UserService;
import com.mom.webapp.utils.ModelMapperUtil;

/**
 * @author Brehima
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final String TOKEN_INVALID = "invalid";
	private static final String TOKEN_EXPIRED = "expired";
	private static final String TOKEN_VALID = "valid";
	@Autowired
    private CustomerRepository repository;
	@Autowired
    private CredentialsRepository credentialsRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
    private EmailVerifTokenRepository tokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;
    @Autowired
	private ModelMapperUtil modelMapperUtil;


//    @Autowired
//    private SessionRegistry sessionRegistry;
	/* (non-Javadoc)
	 * @see com.mom.webapp.services.UserService#registerNewUser(com.mom.webapp.dto.UserDto)
	 */
	@Override
	public Customer registerNewUser(CustomerDto customerDto) throws EmailAlreadyExistsException {
		if (emailAlreadyExists(customerDto.getEmail())) {
			LOGGER.debug("Un compte existe déjà avec ce mail : "+customerDto.getEmail());
            throw new EmailAlreadyExistsException("There is an account with that email address:"  + customerDto.getEmail());
        }
		Role role = roleRepository.findByAuthority("ROLE_USER");
        Credentials credentials = new Credentials();
        credentials.setRoles(new HashSet<Role>(Arrays.asList(role)));
        credentials.setUsername(customerDto.getEmail());
        credentials.setPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));
        credentials.setEnabled(false);
        credentials.setLocked(false);
        Customer customer = new Customer();
        customer.setCredentials(credentials);
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setGender(customerDto.getGender());
        customer.setEmail(customerDto.getEmail());
        Date currentDate = new Date();
        customer.setCreationDate(currentDate);
        customer.setLastUpdate(currentDate);
        customer.setPseudonym(customerDto.getPseudonym());
        
        return repository.save(customer);       
	}
	private boolean emailAlreadyExists(String email) {
        Customer cutomer = repository.findByEmail(email);
        if (cutomer != null) {
            return true;
        }
        return false;
    }
	@Override
	public void createUserEmailVerifToken(Credentials user, String token) {
		final EmailVerifToken userToken = new EmailVerifToken(token, user);
	    tokenRepository.save(userToken);
		
	}
	@Override
	public String validateEmailVerifToken(String token) {
		 final EmailVerifToken emailverifToken = tokenRepository.findByToken(token);
       if (emailverifToken == null) {
           return TOKEN_INVALID;
       }

       final Credentials userCredentials = emailverifToken.getUserCredentials();
       final Calendar cal = Calendar.getInstance();
       if ((emailverifToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
           tokenRepository.delete(emailverifToken);
           return TOKEN_EXPIRED;
       }

       userCredentials.setEnabled(true);
       //tokenRepository.delete(verificationToken);
       credentialsRepository.save(userCredentials);
       return TOKEN_VALID;
	}
	@Override
	public Credentials getUserCredentials(String verifToken) {
		final EmailVerifToken token = tokenRepository.findByToken(verifToken);
        if (token != null) {
            return token.getUserCredentials();
        }
        return null;
	}
	@Override
	public Credentials findUserCredentialsByEmail(String userEmail) {
		return credentialsRepository.findByUsername(userEmail);
	}
	@Override
	public void createUserPasswordResetToken(Credentials user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
      passwordTokenRepository.save(myToken);
		
	}
	@Override
	public EmailVerifToken generateNewEmailVerifToken(String existingToken) {
		EmailVerifToken vToken = tokenRepository.findByToken(existingToken);
	    vToken.updateToken(UUID.randomUUID().toString());
	    vToken = tokenRepository.save(vToken);
	    return vToken;
	}
	@Override
	public Customer getCurrentAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = null;
		 if(auth != null && auth.isAuthenticated()) {
//			 UserDetails userDetails = (UserDetails)auth.getCredentials();
			 Object principal = auth.getPrincipal();
			 String username = "";
			 if (principal instanceof UserDetails) {
				 username = ((UserDetails)principal).getUsername();
				 } else {
				 username = principal.toString();
				 }
			 Credentials cred = findUserCredentialsByEmail(username);
			 if(cred != null) {
				 customer = cred.getCustomer();
			 }
		 }
		 return customer;
	}
//
//    @Override
//    public VerificationToken getVerificationToken(final String VerificationToken) {
//        return tokenRepository.findByToken(VerificationToken);
//    }
//
//    @Override
//    public void saveRegisteredUser(final User user) {
//        repository.save(user);
//    }
//
//    @Override
//    public void deleteUser(final User user) {
//        final VerificationToken verificationToken = tokenRepository.findByUser(user);
//
//        if (verificationToken != null) {
//            tokenRepository.delete(verificationToken);
//        }
//
//        final PasswordResetToken passwordToken = passwordTokenRepository.findByUser(user);
//
//        if (passwordToken != null) {
//            passwordTokenRepository.delete(passwordToken);
//        }
//
//        repository.delete(user);
//    }
//
//    @Override
//    public void createVerificationTokenForUser(final User user, final String token) {
//        final VerificationToken myToken = new VerificationToken(token, user);
//        tokenRepository.save(myToken);
//    }
//
//    @Override
//    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
//        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
//        vToken.updateToken(UUID.randomUUID().toString());
//        vToken = tokenRepository.save(vToken);
//        return vToken;
//    }
//
//    @Override
//    public void createPasswordResetTokenForUser(final User user, final String token) {
//        final PasswordResetToken myToken = new PasswordResetToken(token, user);
//        passwordTokenRepository.save(myToken);
//    }
//
//    @Override
//    public User findUserByEmail(final String email) {
//        return repository.findByEmail(email);
//    }
//
//    @Override
//    public PasswordResetToken getPasswordResetToken(final String token) {
//        return passwordTokenRepository.findByToken(token);
//    }
//
//    @Override
//    public User getUserByPasswordResetToken(final String token) {
//        return passwordTokenRepository.findByToken(token).getUser();
//    }
//
//    @Override
//    public User getUserByID(final long id) {
//        return repository.findOne(id);
//    }
//
//    @Override
//    public void changeUserPassword(final User user, final String password) {
//        user.setPassword(passwordEncoder.encode(password));
//        repository.save(user);
//    }
//
//    @Override
//    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
//        return passwordEncoder.matches(oldPassword, user.getPassword());
//    }
//
//    @Override
//    public String validateVerificationToken(String token) {
//        final VerificationToken verificationToken = tokenRepository.findByToken(token);
//        if (verificationToken == null) {
//            return TOKEN_INVALID;
//        }
//
//        final User user = verificationToken.getUser();
//        final Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            tokenRepository.delete(verificationToken);
//            return TOKEN_EXPIRED;
//        }
//
//        user.setEnabled(true);
//        // tokenRepository.delete(verificationToken);
//        repository.save(user);
//        return TOKEN_VALID;
//    }

//    @Override
//    public String generateQRUrl(User user) throws UnsupportedEncodingException {
//        return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, user.getEmail(), user.getSecret(), APP_NAME), "UTF-8");
//    }
//
//
//    private boolean emailExist(final String email) {
//        return repository.findByEmail(email) != null;
//    }
//
//    @Override
//    public List<String> getUsersFromSessionRegistry() {
//        return sessionRegistry.getAllPrincipals().stream().filter((u) -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString).collect(Collectors.toList());
//    }

}

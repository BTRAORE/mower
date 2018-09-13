/**
 * 
 */
package com.mom.webapp.listeners;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.mom.webapp.events.CompleteRegistrationEvent;
import com.mom.webapp.model.Credentials;
import com.mom.webapp.services.UserService;

/**
 * @author Brehima
 *
 */
@Component
public class CompleteRegistrationListener implements ApplicationListener<CompleteRegistrationEvent>{
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final CompleteRegistrationEvent event) {
    	LOGGER.debug("**** Registration confirmation event received ! *******");
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final CompleteRegistrationEvent event) {
        final Credentials user = event.getUserCredentials();
        final String token = UUID.randomUUID().toString();
        service.createUserEmailVerifToken(user, token);
        LOGGER.debug("**** Registration token generated : "+token);
        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
        LOGGER.debug("**** Registration email is sent !");
    }

    //

    private final SimpleMailMessage constructEmailMessage(final CompleteRegistrationEvent event, final Credentials user, final String token) {
        final String recipientAddress = user.getUsername();
        final String subject = "MôM : Activation du compte";
        final String confirmationUrl = event.getAppUrl() + "/account/confirmRegistration?token=" + token;
        final String message = messages.getMessage("message.signup.confirmation.mail", null, event.getLocale());
        final String politeMsg = messages.getMessage("message.signup.confirmation.mail.politeMsg", null, event.getLocale());
        LOGGER.debug("**** Registration message to sent : "+message+politeMsg);
        LOGGER.debug("**** Registration message send to : "+recipientAddress);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl+"\r\n"+politeMsg);
        LOGGER.debug("**** Registration message send from : "+env.getProperty("support.email"));
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}

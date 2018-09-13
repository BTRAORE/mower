/**
 * 
 */
package com.mom.webapp.events;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.mom.webapp.model.Credentials;

/**
 * @author Brehima
 *
 */
public class CompleteRegistrationEvent extends ApplicationEvent{
	
	private final String appUrl;
    private final Locale locale;
    private final Credentials userCredentials;

    public CompleteRegistrationEvent(final Credentials userCredentials, final Locale locale, final String appUrl) {
        super(userCredentials);
        this.userCredentials = userCredentials;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public Credentials getUserCredentials() {
        return userCredentials;
    }
}

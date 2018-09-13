/**
 * 
 */
package com.mom.webapp.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Brehima
 *
 */
@Entity
public class EmailVerifToken {

	private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailVerifTokenId;

    private String token;

    @OneToOne(targetEntity = Credentials.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "credentials")
    private Credentials credentials;

    private Date expiryDate;

    public EmailVerifToken() {
        super();
    }

    public EmailVerifToken(final String token) {
        super();

        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public EmailVerifToken(final String token, final Credentials credentials) {
        super();

        this.token = token;
        this.credentials = credentials;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Long getEmailVerifTokenId() {
        return emailVerifTokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Credentials getUserCredentials() {
        return credentials;
    }

    public void setUserCredentials(final Credentials user) {
        this.credentials = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(final Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
}

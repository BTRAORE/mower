/**
 * 
 */
package com.mom.webapp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Brehima
 *
 */
@Entity
public class Customer {
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Id
 private Long customerId;
 private String gender;
 private String firstname;
 private String lastname;
 private String pseudonym;
 @Temporal(TemporalType.DATE)
 private Date birthdate;
 private String email;
 private String phoneNumber;
 @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
 @JoinColumn(name="address_id")
 private Address address;
 private String type;
 @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
 @JoinColumn(name="credentials")
 private Credentials credentials;
 @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL)
 private Set<Offer> offers;
 @Temporal(TemporalType.TIMESTAMP)
 private Date creationDate;
 
 @Temporal(TemporalType.TIMESTAMP)
 private Date lastUpdate;
/**
 * @return the customerId
 */
public Long getCustomerId() {
	return customerId;
}
/**
 * @param customerId the customerId to set
 */
public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}
/**
 * @return the firstName
 */
public String getFirstname() {
	return firstname;
}
/**
 * @param firstName the firstName to set
 */
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
/**
 * @return the lastName
 */
public String getLastname() {
	return lastname;
}
/**
 * @param lastName the lastName to set
 */
public void setLastname(String lastname) {
	this.lastname = lastname;
}
/**
 * @return the pseudoname
 */
public String getPseudonym() {
	return pseudonym;
}
/**
 * @param pseudoname the pseudoname to set
 */
public void setPseudonym(String pseudonym) {
	this.pseudonym = pseudonym;
}
/**
 * @return the birthdate
 */
public Date getBirthdate() {
	return birthdate;
}
/**
 * @param birthdate the birthdate to set
 */
public void setBirthdate(Date birthdate) {
	this.birthdate = birthdate;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return the phoneNumber
 */
public String getPhoneNumber() {
	return phoneNumber;
}
/**
 * @param phoneNumber the phoneNumber to set
 */
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
/**
 * @return the address
 */
public Address getAddress() {
	return address;
}
/**
 * @param address the address to set
 */
public void setAddress(Address address) {
	this.address = address;
}
/**
 * @return the type
 */
public String getType() {
	return type;
}
/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}
/**
 * @return the credentials
 */
public Credentials getCredentials() {
	return credentials;
}
/**
 * @param credentials the credentials to set
 */
public void setCredentials(Credentials credentials) {
	this.credentials = credentials;
}
/**
 * @return the creationDate
 */
public Date getCreationDate() {
	return creationDate;
}
/**
 * @param creationDate the creationDate to set
 */
public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
}
/**
 * @return the lastUpdate
 */
public Date getLastUpdate() {
	return lastUpdate;
}
/**
 * @param lastUpdate the lastUpdate to set
 */
public void setLastUpdate(Date lastUpdate) {
	this.lastUpdate = lastUpdate;
}
/**
 * @return the gender
 */
public String getGender() {
	return gender;
}
/**
 * @param gender the gender to set
 */
public void setGender(String gender) {
	this.gender = gender;
}
/**
 * @return the offers
 */
public Set<Offer> getOffers() {
	return offers;
}
/**
 * @param offers the offers to set
 */
public void setOffers(Set<Offer> offers) {
	this.offers = offers;
}
 
 
}

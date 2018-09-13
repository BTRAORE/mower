/**
 * 
 */
package com.mom.webapp.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mom.webapp.annotations.PasswordMatches;
import com.mom.webapp.annotations.ValidEmail;

/**
 * @author Brehima
 *
 */
@PasswordMatches
public class CustomerDto {
	
	private Long customerId;
	@NotNull
    @NotEmpty(message="{message.signup.firtname.required}")
    private String firstname;
     
    @NotNull
    @NotEmpty
    private String lastname;
    
    @NotNull
    @NotEmpty
    private String gender;
    
    private String pseudonym;
    
    @NotNull
    @NotEmpty
    private String password;
    
    @NotNull
    @NotEmpty
    private String confirmPassword;
     
    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;
    
    private Date birthdate;
    
    private String phoneNumber;
    
    private AddressDto address;
    
    private String type;

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
	 * @param pseudonym the pseudonym to set
	 */
	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudoname(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public AddressDto getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressDto address) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerDto [firstName=" + firstname + ", lastName=" + lastname + ", gender=" + gender + ", pseudonym="
				+ pseudonym + ", password=" + password + ", confirmPassword=" + confirmPassword + ", email=" + email
				+ ", birthdate=" + birthdate + ", phoneNumber=" + phoneNumber + ", address=" + address + ", type="
				+ type + "]";
	}
    
    
}

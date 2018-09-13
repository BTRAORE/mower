/**
 * 
 */
package com.mom.webapp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Brehima
 *
 */
@Entity
public class Offer {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long offerId;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	private String offerTitle;
	private String offerDescription;
	private Double offerPrice;
	@OneToOne
	@JoinColumn(name = "topic_id")
	private Topic topic;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@OneToOne
	@JoinColumn(name = "catalog_item_id")
	private ItemsCatalog item;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="address_id")
	private Address address;
	@OneToOne
	@JoinColumn(name="offer_status_id")
	private OfferStatus offerStatus;
	private String offerImgsFolder;
	private String phoneNumber;
	private String email;
	@OneToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	private Short ageFrom;
	private Short ageTo;
	@OneToOne
	@JoinColumn(name="item_state_id")
	private ItemState state;
	private Integer viewCount;
	private Boolean isOnlinePayment;
	private Double dayPrice;
	private Double weekPrice;
	private Double weekEndPrice;
	private Double monthPrice;
	@Temporal(TemporalType.TIMESTAMP)
	public Date offerCreationDate;
	@Temporal(TemporalType.TIMESTAMP)
	public Date offerLastUpdate;
	private String mainImg;
	
	public Long getOfferId() {
		return offerId;
	}
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getOfferTitle() {
		return offerTitle;
	}
	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	public Double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public Address getAddressId() {
		return address;
	}
	public void setAddressId(Address address) {
		this.address = address;
	}
	public OfferStatus getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}
	public String getOfferImgsFolder() {
		return offerImgsFolder;
	}
	public void setOfferImgsFolder(String offerImgsFolder) {
		this.offerImgsFolder = offerImgsFolder;
	}
	public Date getOfferCreationDate() {
		return offerCreationDate;
	}
	public void setOfferCreationDate(Date offerCreationDate) {
		this.offerCreationDate = offerCreationDate;
	}
	public Date getOfferLastUpdate() {
		return offerLastUpdate;
	}
	public void setOfferLastUpdate(Date offerLastUpdate) {
		this.offerLastUpdate = offerLastUpdate;
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
	 * @return the brand
	 */
	public Brand getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	/**
	 * @return the ageFrom
	 */
	public Short getAgeFrom() {
		return ageFrom;
	}
	/**
	 * @param ageFrom the ageFrom to set
	 */
	public void setAgeFrom(Short ageFrom) {
		this.ageFrom = ageFrom;
	}
	/**
	 * @return the ageTo
	 */
	public Short getAgeTo() {
		return ageTo;
	}
	/**
	 * @param ageTo the ageTo to set
	 */
	public void setAgeTo(Short ageTo) {
		this.ageTo = ageTo;
	}
	/**
	 * @return the isOnlinePayment
	 */
	public Boolean getIsOnlinePayment() {
		return isOnlinePayment;
	}
	/**
	 * @param isOnlinePayment the isOnlinePayment to set
	 */
	public void setIsOnlinePayment(Boolean isOnlinePayment) {
		this.isOnlinePayment = isOnlinePayment;
	}
	/**
	 * @return the dayPrice
	 */
	public Double getDayPrice() {
		return dayPrice;
	}
	/**
	 * @param dayPrice the dayPrice to set
	 */
	public void setDayPrice(Double dayPrice) {
		this.dayPrice = dayPrice;
	}
	/**
	 * @return the weekPrice
	 */
	public Double getWeekPrice() {
		return weekPrice;
	}
	/**
	 * @param weekPrice the weekPrice to set
	 */
	public void setWeekPrice(Double weekPrice) {
		this.weekPrice = weekPrice;
	}
	/**
	 * @return the weekEndPrice
	 */
	public Double getWeekEndPrice() {
		return weekEndPrice;
	}
	/**
	 * @param weekEndPrice the weekEndPrice to set
	 */
	public void setWeekEndPrice(Double weekEndPrice) {
		this.weekEndPrice = weekEndPrice;
	}
	/**
	 * @return the monthPrice
	 */
	public Double getMonthPrice() {
		return monthPrice;
	}
	/**
	 * @param monthPrice the monthPrice to set
	 */
	public void setMonthPrice(Double monthPrice) {
		this.monthPrice = monthPrice;
	}
	/**
	 * @return the item
	 */
	public ItemsCatalog getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(ItemsCatalog item) {
		this.item = item;
	}
	/**
	 * @return the state
	 */
	public ItemState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(ItemState state) {
		this.state = state;
	}
	/**
	 * @return the viewCount
	 */
	public Integer getViewCount() {
		return viewCount;
	}
	/**
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return the mainImg
	 */
	public String getMainImg() {
		return mainImg;
	}
	/**
	 * @param mainImg the mainImg to set
	 */
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	
	
}

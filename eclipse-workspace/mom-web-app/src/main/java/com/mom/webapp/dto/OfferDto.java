/**
 * 
 */
package com.mom.webapp.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.CollectionUtils;

/**
 * @author Brehima
 *
 */
public class OfferDto {
	
	private Long offerId;
	@NotEmpty
	@NotNull
	private String offerTitle;
	@NotEmpty
	@NotNull
	private String offerDescription;
	@NotNull
	private Long offerPrice;
	public CustomerDto customer;
	@NotNull
	private AddressDto address;
	public ItemsCatalogDto item;
	private String phoneNumber;
	private String email;
	private OfferStatusDto offerStatus;
	private BrandDto brand;
	private Short ageFrom;
	private Short ageTo;
	@NotNull
	private ItemStateDto state;
	private Boolean isOnlinePayment;
	private Integer dayPrice;
	private Integer weekPrice;
	private Integer weekEndPrice;
	private Integer monthPrice;
	private Integer viewCount;
	private Date offerLastUpdate;
	private Date offerCreationDate;
	private List<String> offerImgs;
	private String offerImgsFolder;
	private String mainImg;
	
	/**
	 * @return the offerId
	 */
	public Long getOfferId() {
		return offerId;
	}
	/**
	 * @param offerId the offerId to set
	 */
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	/**
	 * @return the offerTitle
	 */
	public String getOfferTitle() {
		return offerTitle;
	}
	/**
	 * @param offerTitle the offerTitle to set
	 */
	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}
	/**
	 * @return the offerDescription
	 */
	public String getOfferDescription() {
		return offerDescription;
	}
	/**
	 * @param offerDescription the offerDescription to set
	 */
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	/**
	 * @return the offerPrice
	 */
	public Long getOfferPrice() {
		return offerPrice;
	}
	/**
	 * @param offerPrice the offerPrice to set
	 */
	public void setOfferPrice(Long offerPrice) {
		this.offerPrice = offerPrice;
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
	 * @return the offerStatus
	 */
	public OfferStatusDto getOfferStatus() {
		return offerStatus;
	}
	/**
	 * @param offerStatus the offerStatus to set
	 */
	public void setOfferStatus(OfferStatusDto offerStatus) {
		this.offerStatus = offerStatus;
	}
	/**
	 * @return the brand
	 */
	public BrandDto getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(BrandDto brand) {
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
	 * @return the state
	 */
	public ItemStateDto getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(ItemStateDto state) {
		this.state = state;
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
	public Integer getDayPrice() {
		return dayPrice;
	}
	/**
	 * @param dayPrice the dayPrice to set
	 */
	public void setDayPrice(Integer dayPrice) {
		this.dayPrice = dayPrice;
	}
	/**
	 * @return the weekPrice
	 */
	public Integer getWeekPrice() {
		return weekPrice;
	}
	/**
	 * @param weekPrice the weekPrice to set
	 */
	public void setWeekPrice(Integer weekPrice) {
		this.weekPrice = weekPrice;
	}
	/**
	 * @return the weekEndPrice
	 */
	public Integer getWeekEndPrice() {
		return weekEndPrice;
	}
	/**
	 * @param weekEndPrice the weekEndPrice to set
	 */
	public void setWeekEndPrice(Integer weekEndPrice) {
		this.weekEndPrice = weekEndPrice;
	}
	/**
	 * @return the monthPrice
	 */
	public Integer getMonthPrice() {
		return monthPrice;
	}
	/**
	 * @param monthPrice the monthPrice to set
	 */
	public void setMonthPrice(Integer monthPrice) {
		this.monthPrice = monthPrice;
	}
	/**
	 * @return the customer
	 */
	public CustomerDto getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	/**
	 * @return the item
	 */
	public ItemsCatalogDto getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(ItemsCatalogDto item) {
		this.item = item;
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
	 * @return the offerLastUpdate
	 */
	public Date getOfferLastUpdate() {
		return offerLastUpdate;
	}
	/**
	 * @param offerLastUpdate the offerLastUpdate to set
	 */
	public void setOfferLastUpdate(Date offerLastUpdate) {
		this.offerLastUpdate = offerLastUpdate;
	}
	
	/**
	 * @return the offerCreationDate
	 */
	public Date getOfferCreationDate() {
		return offerCreationDate;
	}
	/**
	 * @param offerCreationDate the offerCreationDate to set
	 */
	public void setOfferCreationDate(Date offerCreationDate) {
		this.offerCreationDate = offerCreationDate;
	}
	
	/**
	 * @return the offerImgs
	 */
	public List<String> getOfferImgs() {
		return offerImgs;
	}
	/**
	 * @param offerImgs the offerImgs to set
	 */
	public void setOfferImgs(List<String> offerImgs) {
		this.offerImgs = offerImgs;
	}
	
	/**
	 * @return the offerImgsFolder
	 */
	public String getOfferImgsFolder() {
		return offerImgsFolder;
	}
	/**
	 * @param offerImgsFolder the offerImgsFolder to set
	 */
	public void setOfferImgsFolder(String offerImgsFolder) {
		this.offerImgsFolder = offerImgsFolder;
	}
	
	/**
	 * @return the mainImg
	 */
	public String getMainImg() {
		if(mainImg == null && !CollectionUtils.isEmpty(offerImgs)) {
			return offerImgs.get(0);
		}
		return mainImg;
	}
	/**
	 * @param mainImg the mainImg to set
	 */
	public void setMainImg(String mainImg) {
		if(mainImg == null && !CollectionUtils.isEmpty(offerImgs)) {
			this.mainImg = offerImgs.get(0);
		}else {
			this.mainImg = mainImg;
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OfferDto [offerTitle=" + offerTitle + ", offerDescription=" + offerDescription + ", offerPrice="
				+ offerPrice + ", customer=" + customer + ", address=" + address + ", item=" + item + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", offerStatus=" + offerStatus + ", brand=" + brand + ", ageFrom="
				+ ageFrom + ", ageTo=" + ageTo + ", state=" + state + ", isOnlinePayment=" + isOnlinePayment
				+ ", dayPrice=" + dayPrice + ", weekPrice=" + weekPrice + ", weekEndPrice=" + weekEndPrice
				+ ", monthPrice=" + monthPrice + ", viewCount=" + viewCount + ", offerLastUpdate=" + offerLastUpdate
				+ "]";
	}
	
	
	
	
}

package com.skytala.eCommerce.domain.accounting.relations.creditCard.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.mapper.CreditCardMapper;

public class CreditCard implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentMethodId;
private String cardType;
private String cardNumber;
private Timestamp validFromDate;
private Timestamp expireDate;
private Timestamp issueNumber;
private String companyNameOnCard;
private String titleOnCard;
private String firstNameOnCard;
private String middleNameOnCard;
private String lastNameOnCard;
private String suffixOnCard;
private String contactMechId;
private Long consecutiveFailedAuths;
private Timestamp lastFailedAuthDate;
private Long consecutiveFailedNsf;
private Timestamp lastFailedNsfDate;

public String getPaymentMethodId() {
return paymentMethodId;
}

public void setPaymentMethodId(String  paymentMethodId) {
this.paymentMethodId = paymentMethodId;
}

public String getCardType() {
return cardType;
}

public void setCardType(String  cardType) {
this.cardType = cardType;
}

public String getCardNumber() {
return cardNumber;
}

public void setCardNumber(String  cardNumber) {
this.cardNumber = cardNumber;
}

public Timestamp getValidFromDate() {
return validFromDate;
}

public void setValidFromDate(Timestamp  validFromDate) {
this.validFromDate = validFromDate;
}

public Timestamp getExpireDate() {
return expireDate;
}

public void setExpireDate(Timestamp  expireDate) {
this.expireDate = expireDate;
}

public Timestamp getIssueNumber() {
return issueNumber;
}

public void setIssueNumber(Timestamp  issueNumber) {
this.issueNumber = issueNumber;
}

public String getCompanyNameOnCard() {
return companyNameOnCard;
}

public void setCompanyNameOnCard(String  companyNameOnCard) {
this.companyNameOnCard = companyNameOnCard;
}

public String getTitleOnCard() {
return titleOnCard;
}

public void setTitleOnCard(String  titleOnCard) {
this.titleOnCard = titleOnCard;
}

public String getFirstNameOnCard() {
return firstNameOnCard;
}

public void setFirstNameOnCard(String  firstNameOnCard) {
this.firstNameOnCard = firstNameOnCard;
}

public String getMiddleNameOnCard() {
return middleNameOnCard;
}

public void setMiddleNameOnCard(String  middleNameOnCard) {
this.middleNameOnCard = middleNameOnCard;
}

public String getLastNameOnCard() {
return lastNameOnCard;
}

public void setLastNameOnCard(String  lastNameOnCard) {
this.lastNameOnCard = lastNameOnCard;
}

public String getSuffixOnCard() {
return suffixOnCard;
}

public void setSuffixOnCard(String  suffixOnCard) {
this.suffixOnCard = suffixOnCard;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public Long getConsecutiveFailedAuths() {
return consecutiveFailedAuths;
}

public void setConsecutiveFailedAuths(Long  consecutiveFailedAuths) {
this.consecutiveFailedAuths = consecutiveFailedAuths;
}

public Timestamp getLastFailedAuthDate() {
return lastFailedAuthDate;
}

public void setLastFailedAuthDate(Timestamp  lastFailedAuthDate) {
this.lastFailedAuthDate = lastFailedAuthDate;
}

public Long getConsecutiveFailedNsf() {
return consecutiveFailedNsf;
}

public void setConsecutiveFailedNsf(Long  consecutiveFailedNsf) {
this.consecutiveFailedNsf = consecutiveFailedNsf;
}

public Timestamp getLastFailedNsfDate() {
return lastFailedNsfDate;
}

public void setLastFailedNsfDate(Timestamp  lastFailedNsfDate) {
this.lastFailedNsfDate = lastFailedNsfDate;
}


public Map<String, Object> mapAttributeField() {
return CreditCardMapper.map(this);
}
}

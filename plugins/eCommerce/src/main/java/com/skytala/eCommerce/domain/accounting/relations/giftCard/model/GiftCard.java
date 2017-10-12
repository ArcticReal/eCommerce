package com.skytala.eCommerce.domain.accounting.relations.giftCard.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.mapper.GiftCardMapper;

public class GiftCard implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentMethodId;
private String cardNumber;
private String pinNumber;
private Timestamp expireDate;
private String contactMechId;

public String getPaymentMethodId() {
return paymentMethodId;
}

public void setPaymentMethodId(String  paymentMethodId) {
this.paymentMethodId = paymentMethodId;
}

public String getCardNumber() {
return cardNumber;
}

public void setCardNumber(String  cardNumber) {
this.cardNumber = cardNumber;
}

public String getPinNumber() {
return pinNumber;
}

public void setPinNumber(String  pinNumber) {
this.pinNumber = pinNumber;
}

public Timestamp getExpireDate() {
return expireDate;
}

public void setExpireDate(Timestamp  expireDate) {
this.expireDate = expireDate;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}


public Map<String, Object> mapAttributeField() {
return GiftCardMapper.map(this);
}
}

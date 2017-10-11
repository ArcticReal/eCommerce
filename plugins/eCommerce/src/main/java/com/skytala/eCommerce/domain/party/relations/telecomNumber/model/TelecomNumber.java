package com.skytala.eCommerce.domain.party.relations.telecomNumber.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.mapper.TelecomNumberMapper;

public class TelecomNumber implements Serializable{

private static final long serialVersionUID = 1L;
private String contactMechId;
private String countryCode;
private String areaCode;
private String contactNumber;
private String askForName;

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getCountryCode() {
return countryCode;
}

public void setCountryCode(String  countryCode) {
this.countryCode = countryCode;
}

public String getAreaCode() {
return areaCode;
}

public void setAreaCode(String  areaCode) {
this.areaCode = areaCode;
}

public String getContactNumber() {
return contactNumber;
}

public void setContactNumber(String  contactNumber) {
this.contactNumber = contactNumber;
}

public String getAskForName() {
return askForName;
}

public void setAskForName(String  askForName) {
this.askForName = askForName;
}


public Map<String, Object> mapAttributeField() {
return TelecomNumberMapper.map(this);
}
}

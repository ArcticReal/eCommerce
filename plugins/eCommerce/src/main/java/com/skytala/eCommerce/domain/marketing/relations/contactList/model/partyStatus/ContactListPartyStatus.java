package com.skytala.eCommerce.domain.marketing.relations.contactList.model.partyStatus;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.partyStatus.ContactListPartyStatusMapper;

public class ContactListPartyStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String contactListId;
private String partyId;
private Timestamp fromDate;
private Timestamp statusDate;
private String statusId;
private String setByUserLoginId;
private String optInVerifyCode;

public String getContactListId() {
return contactListId;
}

public void setContactListId(String  contactListId) {
this.contactListId = contactListId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getStatusDate() {
return statusDate;
}

public void setStatusDate(Timestamp  statusDate) {
this.statusDate = statusDate;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getSetByUserLoginId() {
return setByUserLoginId;
}

public void setSetByUserLoginId(String  setByUserLoginId) {
this.setByUserLoginId = setByUserLoginId;
}

public String getOptInVerifyCode() {
return optInVerifyCode;
}

public void setOptInVerifyCode(String  optInVerifyCode) {
this.optInVerifyCode = optInVerifyCode;
}


public Map<String, Object> mapAttributeField() {
return ContactListPartyStatusMapper.map(this);
}
}

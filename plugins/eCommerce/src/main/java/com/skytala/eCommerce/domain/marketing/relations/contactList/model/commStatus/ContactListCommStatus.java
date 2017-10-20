package com.skytala.eCommerce.domain.marketing.relations.contactList.model.commStatus;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.commStatus.ContactListCommStatusMapper;

public class ContactListCommStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String contactListId;
private String communicationEventId;
private String contactMechId;
private String partyId;
private Long messageId;
private String statusId;
private String changeByUserLoginId;

public String getContactListId() {
return contactListId;
}

public void setContactListId(String  contactListId) {
this.contactListId = contactListId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Long getMessageId() {
return messageId;
}

public void setMessageId(Long  messageId) {
this.messageId = messageId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}


public Map<String, Object> mapAttributeField() {
return ContactListCommStatusMapper.map(this);
}
}

package com.skytala.eCommerce.domain.party.relations.communicationEvent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.CommunicationEventMapper;

public class CommunicationEvent implements Serializable{

private static final long serialVersionUID = 1L;
private String communicationEventId;
private String communicationEventTypeId;
private String origCommEventId;
private String parentCommEventId;
private String statusId;
private String contactMechTypeId;
private String contactMechIdFrom;
private String contactMechIdTo;
private String roleTypeIdFrom;
private String roleTypeIdTo;
private String partyIdFrom;
private String partyIdTo;
private Timestamp entryDate;
private Timestamp datetimeStarted;
private Timestamp datetimeEnded;
private String subject;
private String contentMimeTypeId;
private String content;
private String note;
private String reasonEnumId;
private String contactListId;
private String headerString;
private String fromString;
private String toString;
private String ccString;
private String bccString;
private String messageId;

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}

public String getCommunicationEventTypeId() {
return communicationEventTypeId;
}

public void setCommunicationEventTypeId(String  communicationEventTypeId) {
this.communicationEventTypeId = communicationEventTypeId;
}

public String getOrigCommEventId() {
return origCommEventId;
}

public void setOrigCommEventId(String  origCommEventId) {
this.origCommEventId = origCommEventId;
}

public String getParentCommEventId() {
return parentCommEventId;
}

public void setParentCommEventId(String  parentCommEventId) {
this.parentCommEventId = parentCommEventId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getContactMechTypeId() {
return contactMechTypeId;
}

public void setContactMechTypeId(String  contactMechTypeId) {
this.contactMechTypeId = contactMechTypeId;
}

public String getContactMechIdFrom() {
return contactMechIdFrom;
}

public void setContactMechIdFrom(String  contactMechIdFrom) {
this.contactMechIdFrom = contactMechIdFrom;
}

public String getContactMechIdTo() {
return contactMechIdTo;
}

public void setContactMechIdTo(String  contactMechIdTo) {
this.contactMechIdTo = contactMechIdTo;
}

public String getRoleTypeIdFrom() {
return roleTypeIdFrom;
}

public void setRoleTypeIdFrom(String  roleTypeIdFrom) {
this.roleTypeIdFrom = roleTypeIdFrom;
}

public String getRoleTypeIdTo() {
return roleTypeIdTo;
}

public void setRoleTypeIdTo(String  roleTypeIdTo) {
this.roleTypeIdTo = roleTypeIdTo;
}

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
}

public Timestamp getEntryDate() {
return entryDate;
}

public void setEntryDate(Timestamp  entryDate) {
this.entryDate = entryDate;
}

public Timestamp getDatetimeStarted() {
return datetimeStarted;
}

public void setDatetimeStarted(Timestamp  datetimeStarted) {
this.datetimeStarted = datetimeStarted;
}

public Timestamp getDatetimeEnded() {
return datetimeEnded;
}

public void setDatetimeEnded(Timestamp  datetimeEnded) {
this.datetimeEnded = datetimeEnded;
}

public String getSubject() {
return subject;
}

public void setSubject(String  subject) {
this.subject = subject;
}

public String getContentMimeTypeId() {
return contentMimeTypeId;
}

public void setContentMimeTypeId(String  contentMimeTypeId) {
this.contentMimeTypeId = contentMimeTypeId;
}

public String getContent() {
return content;
}

public void setContent(String  content) {
this.content = content;
}

public String getNote() {
return note;
}

public void setNote(String  note) {
this.note = note;
}

public String getReasonEnumId() {
return reasonEnumId;
}

public void setReasonEnumId(String  reasonEnumId) {
this.reasonEnumId = reasonEnumId;
}

public String getContactListId() {
return contactListId;
}

public void setContactListId(String  contactListId) {
this.contactListId = contactListId;
}

public String getHeaderString() {
return headerString;
}

public void setHeaderString(String  headerString) {
this.headerString = headerString;
}

public String getFromString() {
return fromString;
}

public void setFromString(String  fromString) {
this.fromString = fromString;
}

public String getToString() {
return toString;
}

public void setToString(String  toString) {
this.toString = toString;
}

public String getCcString() {
return ccString;
}

public void setCcString(String  ccString) {
this.ccString = ccString;
}

public String getBccString() {
return bccString;
}

public void setBccString(String  bccString) {
this.bccString = bccString;
}

public String getMessageId() {
return messageId;
}

public void setMessageId(String  messageId) {
this.messageId = messageId;
}


public Map<String, Object> mapAttributeField() {
return CommunicationEventMapper.map(this);
}
}

package com.skytala.eCommerce.domain.order.relations.respondingParty.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.respondingParty.mapper.RespondingPartyMapper;

public class RespondingParty implements Serializable{

private static final long serialVersionUID = 1L;
private String respondingPartySeqId;
private String custRequestId;
private String partyId;
private String contactMechId;
private Timestamp dateSent;

public String getRespondingPartySeqId() {
return respondingPartySeqId;
}

public void setRespondingPartySeqId(String  respondingPartySeqId) {
this.respondingPartySeqId = respondingPartySeqId;
}

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public Timestamp getDateSent() {
return dateSent;
}

public void setDateSent(Timestamp  dateSent) {
this.dateSent = dateSent;
}


public Map<String, Object> mapAttributeField() {
return RespondingPartyMapper.map(this);
}
}

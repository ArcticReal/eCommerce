package com.skytala.eCommerce.domain.party.relations.partyContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyContent.mapper.PartyContentMapper;

public class PartyContent implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String contentId;
private String partyContentTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getPartyContentTypeId() {
return partyContentTypeId;
}

public void setPartyContentTypeId(String  partyContentTypeId) {
this.partyContentTypeId = partyContentTypeId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return PartyContentMapper.map(this);
}
}

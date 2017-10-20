package com.skytala.eCommerce.domain.party.relations.party.model.identification;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.identification.PartyIdentificationMapper;

public class PartyIdentification implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String partyIdentificationTypeId;
private String idValue;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getPartyIdentificationTypeId() {
return partyIdentificationTypeId;
}

public void setPartyIdentificationTypeId(String  partyIdentificationTypeId) {
this.partyIdentificationTypeId = partyIdentificationTypeId;
}

public String getIdValue() {
return idValue;
}

public void setIdValue(String  idValue) {
this.idValue = idValue;
}


public Map<String, Object> mapAttributeField() {
return PartyIdentificationMapper.map(this);
}
}

package com.skytala.eCommerce.domain.party.relations.party.model.icsAvsOverride;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.icsAvsOverride.PartyIcsAvsOverrideMapper;

public class PartyIcsAvsOverride implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String avsDeclineString;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getAvsDeclineString() {
return avsDeclineString;
}

public void setAvsDeclineString(String  avsDeclineString) {
this.avsDeclineString = avsDeclineString;
}


public Map<String, Object> mapAttributeField() {
return PartyIcsAvsOverrideMapper.map(this);
}
}

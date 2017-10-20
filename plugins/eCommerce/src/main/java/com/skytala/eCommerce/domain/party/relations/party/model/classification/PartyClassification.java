package com.skytala.eCommerce.domain.party.relations.party.model.classification;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classification.PartyClassificationMapper;

public class PartyClassification implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String partyClassificationGroupId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getPartyClassificationGroupId() {
return partyClassificationGroupId;
}

public void setPartyClassificationGroupId(String  partyClassificationGroupId) {
this.partyClassificationGroupId = partyClassificationGroupId;
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
return PartyClassificationMapper.map(this);
}
}

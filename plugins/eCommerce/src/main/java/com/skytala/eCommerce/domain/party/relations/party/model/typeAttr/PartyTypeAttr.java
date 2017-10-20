package com.skytala.eCommerce.domain.party.relations.party.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.typeAttr.PartyTypeAttrMapper;

public class PartyTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String partyTypeId;
private String attrName;
private String description;

public String getPartyTypeId() {
return partyTypeId;
}

public void setPartyTypeId(String  partyTypeId) {
this.partyTypeId = partyTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return PartyTypeAttrMapper.map(this);
}
}

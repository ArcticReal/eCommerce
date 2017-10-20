package com.skytala.eCommerce.domain.party.relations.party.model.classificationGroup;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classificationGroup.PartyClassificationGroupMapper;

public class PartyClassificationGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String partyClassificationGroupId;
private String partyClassificationTypeId;
private String parentGroupId;
private String description;

public String getPartyClassificationGroupId() {
return partyClassificationGroupId;
}

public void setPartyClassificationGroupId(String  partyClassificationGroupId) {
this.partyClassificationGroupId = partyClassificationGroupId;
}

public String getPartyClassificationTypeId() {
return partyClassificationTypeId;
}

public void setPartyClassificationTypeId(String  partyClassificationTypeId) {
this.partyClassificationTypeId = partyClassificationTypeId;
}

public String getParentGroupId() {
return parentGroupId;
}

public void setParentGroupId(String  parentGroupId) {
this.parentGroupId = parentGroupId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return PartyClassificationGroupMapper.map(this);
}
}

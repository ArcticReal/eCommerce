package com.skytala.eCommerce.domain.party.relations.party.model.classificationType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classificationType.PartyClassificationTypeMapper;

public class PartyClassificationType implements Serializable{

private static final long serialVersionUID = 1L;
private String partyClassificationTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getPartyClassificationTypeId() {
return partyClassificationTypeId;
}

public void setPartyClassificationTypeId(String  partyClassificationTypeId) {
this.partyClassificationTypeId = partyClassificationTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return PartyClassificationTypeMapper.map(this);
}
}

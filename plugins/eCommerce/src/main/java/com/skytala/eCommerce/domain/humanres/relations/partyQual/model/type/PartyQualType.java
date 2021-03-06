package com.skytala.eCommerce.domain.humanres.relations.partyQual.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper.type.PartyQualTypeMapper;

public class PartyQualType implements Serializable{

private static final long serialVersionUID = 1L;
private String partyQualTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getPartyQualTypeId() {
return partyQualTypeId;
}

public void setPartyQualTypeId(String  partyQualTypeId) {
this.partyQualTypeId = partyQualTypeId;
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
return PartyQualTypeMapper.map(this);
}
}

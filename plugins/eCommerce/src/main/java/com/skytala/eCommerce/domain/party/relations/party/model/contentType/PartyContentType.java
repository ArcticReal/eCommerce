package com.skytala.eCommerce.domain.party.relations.party.model.contentType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.contentType.PartyContentTypeMapper;

public class PartyContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String partyContentTypeId;
private String parentTypeId;
private String description;

public String getPartyContentTypeId() {
return partyContentTypeId;
}

public void setPartyContentTypeId(String  partyContentTypeId) {
this.partyContentTypeId = partyContentTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return PartyContentTypeMapper.map(this);
}
}

package com.skytala.eCommerce.domain.party.relations.commContentAssocType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.mapper.CommContentAssocTypeMapper;

public class CommContentAssocType implements Serializable{

private static final long serialVersionUID = 1L;
private String commContentAssocTypeId;
private String description;

public String getCommContentAssocTypeId() {
return commContentAssocTypeId;
}

public void setCommContentAssocTypeId(String  commContentAssocTypeId) {
this.commContentAssocTypeId = commContentAssocTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return CommContentAssocTypeMapper.map(this);
}
}

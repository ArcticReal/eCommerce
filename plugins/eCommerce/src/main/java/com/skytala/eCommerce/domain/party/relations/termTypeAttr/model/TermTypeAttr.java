package com.skytala.eCommerce.domain.party.relations.termTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.termTypeAttr.mapper.TermTypeAttrMapper;

public class TermTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String termTypeId;
private String attrName;
private String description;

public String getTermTypeId() {
return termTypeId;
}

public void setTermTypeId(String  termTypeId) {
this.termTypeId = termTypeId;
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
return TermTypeAttrMapper.map(this);
}
}

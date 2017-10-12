package com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.mapper.FinAccountTransTypeAttrMapper;

public class FinAccountTransTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountTransTypeId;
private String attrName;
private String description;

public String getFinAccountTransTypeId() {
return finAccountTransTypeId;
}

public void setFinAccountTransTypeId(String  finAccountTransTypeId) {
this.finAccountTransTypeId = finAccountTransTypeId;
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
return FinAccountTransTypeAttrMapper.map(this);
}
}

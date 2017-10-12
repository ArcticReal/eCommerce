package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.mapper.FinAccountTypeAttrMapper;

public class FinAccountTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountTypeId;
private String attrName;
private String attrValue;
private String description;

public String getFinAccountTypeId() {
return finAccountTypeId;
}

public void setFinAccountTypeId(String  finAccountTypeId) {
this.finAccountTypeId = finAccountTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getAttrValue() {
return attrValue;
}

public void setAttrValue(String  attrValue) {
this.attrValue = attrValue;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return FinAccountTypeAttrMapper.map(this);
}
}

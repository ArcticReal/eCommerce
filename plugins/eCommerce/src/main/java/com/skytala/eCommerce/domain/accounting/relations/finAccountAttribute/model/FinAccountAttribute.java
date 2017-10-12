package com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.mapper.FinAccountAttributeMapper;

public class FinAccountAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountId;
private String attrName;
private Long attrValue;
private String attrDescription;

public String getFinAccountId() {
return finAccountId;
}

public void setFinAccountId(String  finAccountId) {
this.finAccountId = finAccountId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public Long getAttrValue() {
return attrValue;
}

public void setAttrValue(Long  attrValue) {
this.attrValue = attrValue;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return FinAccountAttributeMapper.map(this);
}
}

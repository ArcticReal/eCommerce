package com.skytala.eCommerce.domain.order.relations.custRequest.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.attribute.CustRequestAttributeMapper;

public class CustRequestAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String attrName;
private Long attrValue;
private String attrDescription;

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
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
return CustRequestAttributeMapper.map(this);
}
}

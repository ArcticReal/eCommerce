package com.skytala.eCommerce.domain.order.relations.custRequest.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.typeAttr.CustRequestTypeAttrMapper;

public class CustRequestTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestTypeId;
private String attrName;
private String description;

public String getCustRequestTypeId() {
return custRequestTypeId;
}

public void setCustRequestTypeId(String  custRequestTypeId) {
this.custRequestTypeId = custRequestTypeId;
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
return CustRequestTypeAttrMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.costComponent.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.typeAttr.CostComponentTypeAttrMapper;

public class CostComponentTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String costComponentTypeId;
private String attrName;
private String description;

public String getCostComponentTypeId() {
return costComponentTypeId;
}

public void setCostComponentTypeId(String  costComponentTypeId) {
this.costComponentTypeId = costComponentTypeId;
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
return CostComponentTypeAttrMapper.map(this);
}
}

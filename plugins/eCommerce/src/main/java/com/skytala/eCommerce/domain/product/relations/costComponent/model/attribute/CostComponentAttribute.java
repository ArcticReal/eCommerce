package com.skytala.eCommerce.domain.product.relations.costComponent.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.attribute.CostComponentAttributeMapper;

public class CostComponentAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String costComponentId;
private String attrName;
private String attrValue;
private String attrDescription;

public String getCostComponentId() {
return costComponentId;
}

public void setCostComponentId(String  costComponentId) {
this.costComponentId = costComponentId;
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

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return CostComponentAttributeMapper.map(this);
}
}

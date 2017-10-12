package com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.mapper.DataResourceTypeAttrMapper;

public class DataResourceTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceTypeId;
private String attrName;
private String description;

public String getDataResourceTypeId() {
return dataResourceTypeId;
}

public void setDataResourceTypeId(String  dataResourceTypeId) {
this.dataResourceTypeId = dataResourceTypeId;
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
return DataResourceTypeAttrMapper.map(this);
}
}

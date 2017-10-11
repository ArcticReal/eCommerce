package com.skytala.eCommerce.domain.order.relations.returnItemType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.returnItemType.mapper.ReturnItemTypeMapper;

public class ReturnItemType implements Serializable{

private static final long serialVersionUID = 1L;
private String returnItemTypeId;
private String parentTypeId;
private String description;

public String getReturnItemTypeId() {
return returnItemTypeId;
}

public void setReturnItemTypeId(String  returnItemTypeId) {
this.returnItemTypeId = returnItemTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ReturnItemTypeMapper.map(this);
}
}

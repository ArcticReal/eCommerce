package com.skytala.eCommerce.domain.orderType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.orderType.mapper.OrderTypeMapper;

public class OrderType implements Serializable{

private static final long serialVersionUID = 1L;
private String orderTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getOrderTypeId() {
return orderTypeId;
}

public void setOrderTypeId(String  orderTypeId) {
this.orderTypeId = orderTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return OrderTypeMapper.map(this);
}
}

package com.skytala.eCommerce.domain.quantityBreakType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.quantityBreakType.mapper.QuantityBreakTypeMapper;

public class QuantityBreakType implements Serializable{

private static final long serialVersionUID = 1L;
private String quantityBreakTypeId;
private String description;

public String getQuantityBreakTypeId() {
return quantityBreakTypeId;
}

public void setQuantityBreakTypeId(String  quantityBreakTypeId) {
this.quantityBreakTypeId = quantityBreakTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return QuantityBreakTypeMapper.map(this);
}
}

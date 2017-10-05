package com.skytala.eCommerce.domain.supplierPrefOrder.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.supplierPrefOrder.mapper.SupplierPrefOrderMapper;

public class SupplierPrefOrder implements Serializable{

private static final long serialVersionUID = 1L;
private String supplierPrefOrderId;
private String description;

public String getSupplierPrefOrderId() {
return supplierPrefOrderId;
}

public void setSupplierPrefOrderId(String  supplierPrefOrderId) {
this.supplierPrefOrderId = supplierPrefOrderId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SupplierPrefOrderMapper.map(this);
}
}

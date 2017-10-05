package com.skytala.eCommerce.domain.productMaintType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.productMaintType.mapper.ProductMaintTypeMapper;

public class ProductMaintType implements Serializable{

private static final long serialVersionUID = 1L;
private String productMaintTypeId;
private String description;
private String parentTypeId;

public String getProductMaintTypeId() {
return productMaintTypeId;
}

public void setProductMaintTypeId(String  productMaintTypeId) {
this.productMaintTypeId = productMaintTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}


public Map<String, Object> mapAttributeField() {
return ProductMaintTypeMapper.map(this);
}
}

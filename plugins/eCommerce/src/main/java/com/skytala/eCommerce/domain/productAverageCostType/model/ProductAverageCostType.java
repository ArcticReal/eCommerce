package com.skytala.eCommerce.domain.productAverageCostType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.productAverageCostType.mapper.ProductAverageCostTypeMapper;

public class ProductAverageCostType implements Serializable{

private static final long serialVersionUID = 1L;
private String productAverageCostTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getProductAverageCostTypeId() {
return productAverageCostTypeId;
}

public void setProductAverageCostTypeId(String  productAverageCostTypeId) {
this.productAverageCostTypeId = productAverageCostTypeId;
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
return ProductAverageCostTypeMapper.map(this);
}
}

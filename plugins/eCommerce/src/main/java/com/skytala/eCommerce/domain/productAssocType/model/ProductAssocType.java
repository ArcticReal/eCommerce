package com.skytala.eCommerce.domain.productAssocType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.productAssocType.mapper.ProductAssocTypeMapper;

public class ProductAssocType implements Serializable{

private static final long serialVersionUID = 1L;
private String productAssocTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getProductAssocTypeId() {
return productAssocTypeId;
}

public void setProductAssocTypeId(String  productAssocTypeId) {
this.productAssocTypeId = productAssocTypeId;
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
return ProductAssocTypeMapper.map(this);
}
}

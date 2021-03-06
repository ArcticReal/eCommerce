package com.skytala.eCommerce.domain.product.relations.product.model.categoryContentType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryContentType.ProductCategoryContentTypeMapper;

public class ProductCategoryContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String prodCatContentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getProdCatContentTypeId() {
return prodCatContentTypeId;
}

public void setProdCatContentTypeId(String  prodCatContentTypeId) {
this.prodCatContentTypeId = prodCatContentTypeId;
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
return ProductCategoryContentTypeMapper.map(this);
}
}

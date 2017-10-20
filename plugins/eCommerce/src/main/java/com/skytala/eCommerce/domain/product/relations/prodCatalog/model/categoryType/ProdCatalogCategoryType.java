package com.skytala.eCommerce.domain.product.relations.prodCatalog.model.categoryType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.categoryType.ProdCatalogCategoryTypeMapper;

public class ProdCatalogCategoryType implements Serializable{

private static final long serialVersionUID = 1L;
private String prodCatalogCategoryTypeId;
private String parentTypeId;
private String description;

public String getProdCatalogCategoryTypeId() {
return prodCatalogCategoryTypeId;
}

public void setProdCatalogCategoryTypeId(String  prodCatalogCategoryTypeId) {
this.prodCatalogCategoryTypeId = prodCatalogCategoryTypeId;
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
return ProdCatalogCategoryTypeMapper.map(this);
}
}

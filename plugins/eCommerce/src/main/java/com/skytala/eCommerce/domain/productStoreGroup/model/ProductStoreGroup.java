package com.skytala.eCommerce.domain.productStoreGroup.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.productStoreGroup.mapper.ProductStoreGroupMapper;

public class ProductStoreGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreGroupId;
private String productStoreGroupTypeId;
private String primaryParentGroupId;
private String productStoreGroupName;
private String description;

public String getProductStoreGroupId() {
return productStoreGroupId;
}

public void setProductStoreGroupId(String  productStoreGroupId) {
this.productStoreGroupId = productStoreGroupId;
}

public String getProductStoreGroupTypeId() {
return productStoreGroupTypeId;
}

public void setProductStoreGroupTypeId(String  productStoreGroupTypeId) {
this.productStoreGroupTypeId = productStoreGroupTypeId;
}

public String getPrimaryParentGroupId() {
return primaryParentGroupId;
}

public void setPrimaryParentGroupId(String  primaryParentGroupId) {
this.primaryParentGroupId = primaryParentGroupId;
}

public String getProductStoreGroupName() {
return productStoreGroupName;
}

public void setProductStoreGroupName(String  productStoreGroupName) {
this.productStoreGroupName = productStoreGroupName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreGroupMapper.map(this);
}
}

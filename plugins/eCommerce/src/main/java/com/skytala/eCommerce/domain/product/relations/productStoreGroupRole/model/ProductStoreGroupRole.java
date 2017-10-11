package com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.mapper.ProductStoreGroupRoleMapper;

public class ProductStoreGroupRole implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreGroupId;
private String partyId;
private String roleTypeId;

public String getProductStoreGroupId() {
return productStoreGroupId;
}

public void setProductStoreGroupId(String  productStoreGroupId) {
this.productStoreGroupId = productStoreGroupId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreGroupRoleMapper.map(this);
}
}

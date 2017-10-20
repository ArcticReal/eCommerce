package com.skytala.eCommerce.domain.product.relations.product.model.categoryGlAccount;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryGlAccount.ProductCategoryGlAccountMapper;

public class ProductCategoryGlAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryId;
private String organizationPartyId;
private String glAccountTypeId;
private String glAccountId;

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getGlAccountTypeId() {
return glAccountTypeId;
}

public void setGlAccountTypeId(String  glAccountTypeId) {
this.glAccountTypeId = glAccountTypeId;
}

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}


public Map<String, Object> mapAttributeField() {
return ProductCategoryGlAccountMapper.map(this);
}
}

package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.category;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.category.TaxAuthorityCategoryMapper;

public class TaxAuthorityCategory implements Serializable{

private static final long serialVersionUID = 1L;
private String taxAuthGeoId;
private String taxAuthPartyId;
private String productCategoryId;

public String getTaxAuthGeoId() {
return taxAuthGeoId;
}

public void setTaxAuthGeoId(String  taxAuthGeoId) {
this.taxAuthGeoId = taxAuthGeoId;
}

public String getTaxAuthPartyId() {
return taxAuthPartyId;
}

public void setTaxAuthPartyId(String  taxAuthPartyId) {
this.taxAuthPartyId = taxAuthPartyId;
}

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}


public Map<String, Object> mapAttributeField() {
return TaxAuthorityCategoryMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.supplierProductFeature.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.mapper.SupplierProductFeatureMapper;

public class SupplierProductFeature implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String productFeatureId;
private String description;
private String uomId;
private String idCode;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}

public String getIdCode() {
return idCode;
}

public void setIdCode(String  idCode) {
this.idCode = idCode;
}


public Map<String, Object> mapAttributeField() {
return SupplierProductFeatureMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.productStorePromoAppl.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productStorePromoAppl.mapper.ProductStorePromoApplMapper;

public class ProductStorePromoAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreId;
private String productPromoId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;
private Boolean manualOnly;

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public Boolean getManualOnly() {
return manualOnly;
}

public void setManualOnly(Boolean  manualOnly) {
this.manualOnly = manualOnly;
}


public Map<String, Object> mapAttributeField() {
return ProductStorePromoApplMapper.map(this);
}
}

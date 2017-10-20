package com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRollup.ProductStoreGroupRollupMapper;

public class ProductStoreGroupRollup implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreGroupId;
private String parentGroupId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getProductStoreGroupId() {
return productStoreGroupId;
}

public void setProductStoreGroupId(String  productStoreGroupId) {
this.productStoreGroupId = productStoreGroupId;
}

public String getParentGroupId() {
return parentGroupId;
}

public void setParentGroupId(String  parentGroupId) {
this.parentGroupId = parentGroupId;
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


public Map<String, Object> mapAttributeField() {
return ProductStoreGroupRollupMapper.map(this);
}
}

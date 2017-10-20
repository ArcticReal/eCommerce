package com.skytala.eCommerce.domain.product.relations.product.model.featureGroupAppl;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureGroupAppl.ProductFeatureGroupApplMapper;

public class ProductFeatureGroupAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String productFeatureGroupId;
private String productFeatureId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getProductFeatureGroupId() {
return productFeatureGroupId;
}

public void setProductFeatureGroupId(String  productFeatureGroupId) {
this.productFeatureGroupId = productFeatureGroupId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
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
return ProductFeatureGroupApplMapper.map(this);
}
}

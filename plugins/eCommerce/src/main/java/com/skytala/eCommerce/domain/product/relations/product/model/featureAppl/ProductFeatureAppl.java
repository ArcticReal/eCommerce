package com.skytala.eCommerce.domain.product.relations.product.model.featureAppl;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureAppl.ProductFeatureApplMapper;

public class ProductFeatureAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String productFeatureId;
private String productFeatureApplTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;
private BigDecimal amount;
private BigDecimal recurringAmount;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public String getProductFeatureApplTypeId() {
return productFeatureApplTypeId;
}

public void setProductFeatureApplTypeId(String  productFeatureApplTypeId) {
this.productFeatureApplTypeId = productFeatureApplTypeId;
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

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public BigDecimal getRecurringAmount() {
return recurringAmount;
}

public void setRecurringAmount(BigDecimal  recurringAmount) {
this.recurringAmount = recurringAmount;
}


public Map<String, Object> mapAttributeField() {
return ProductFeatureApplMapper.map(this);
}
}

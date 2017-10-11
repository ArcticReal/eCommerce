package com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.mapper.ProductCostComponentCalcMapper;

public class ProductCostComponentCalc implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String costComponentTypeId;
private String costComponentCalcId;
private Timestamp fromDate;
private Long sequenceNum;
private Timestamp thruDate;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getCostComponentTypeId() {
return costComponentTypeId;
}

public void setCostComponentTypeId(String  costComponentTypeId) {
this.costComponentTypeId = costComponentTypeId;
}

public String getCostComponentCalcId() {
return costComponentCalcId;
}

public void setCostComponentCalcId(String  costComponentCalcId) {
this.costComponentCalcId = costComponentCalcId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return ProductCostComponentCalcMapper.map(this);
}
}

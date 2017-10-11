package com.skytala.eCommerce.domain.product.relations.productPromoCond.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productPromoCond.mapper.ProductPromoCondMapper;

public class ProductPromoCond implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoId;
private String productPromoRuleId;
private String productPromoCondSeqId;
private String inputParamEnumId;
private String operatorEnumId;
private String condValue;
private String otherValue;

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}

public String getProductPromoRuleId() {
return productPromoRuleId;
}

public void setProductPromoRuleId(String  productPromoRuleId) {
this.productPromoRuleId = productPromoRuleId;
}

public String getProductPromoCondSeqId() {
return productPromoCondSeqId;
}

public void setProductPromoCondSeqId(String  productPromoCondSeqId) {
this.productPromoCondSeqId = productPromoCondSeqId;
}

public String getInputParamEnumId() {
return inputParamEnumId;
}

public void setInputParamEnumId(String  inputParamEnumId) {
this.inputParamEnumId = inputParamEnumId;
}

public String getOperatorEnumId() {
return operatorEnumId;
}

public void setOperatorEnumId(String  operatorEnumId) {
this.operatorEnumId = operatorEnumId;
}

public String getCondValue() {
return condValue;
}

public void setCondValue(String  condValue) {
this.condValue = condValue;
}

public String getOtherValue() {
return otherValue;
}

public void setOtherValue(String  otherValue) {
this.otherValue = otherValue;
}


public Map<String, Object> mapAttributeField() {
return ProductPromoCondMapper.map(this);
}
}

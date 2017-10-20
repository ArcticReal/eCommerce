package com.skytala.eCommerce.domain.product.relations.product.model.priceCond;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceCond.ProductPriceCondMapper;

public class ProductPriceCond implements Serializable{

private static final long serialVersionUID = 1L;
private String productPriceRuleId;
private String productPriceCondSeqId;
private String inputParamEnumId;
private String operatorEnumId;
private String condValue;

public String getProductPriceRuleId() {
return productPriceRuleId;
}

public void setProductPriceRuleId(String  productPriceRuleId) {
this.productPriceRuleId = productPriceRuleId;
}

public String getProductPriceCondSeqId() {
return productPriceCondSeqId;
}

public void setProductPriceCondSeqId(String  productPriceCondSeqId) {
this.productPriceCondSeqId = productPriceCondSeqId;
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


public Map<String, Object> mapAttributeField() {
return ProductPriceCondMapper.map(this);
}
}

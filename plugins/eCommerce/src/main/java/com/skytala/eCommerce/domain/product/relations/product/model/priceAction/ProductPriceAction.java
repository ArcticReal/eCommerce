package com.skytala.eCommerce.domain.product.relations.product.model.priceAction;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceAction.ProductPriceActionMapper;

public class ProductPriceAction implements Serializable{

private static final long serialVersionUID = 1L;
private String productPriceRuleId;
private String productPriceActionSeqId;
private String productPriceActionTypeId;
private BigDecimal amount;
private String rateCode;

public String getProductPriceRuleId() {
return productPriceRuleId;
}

public void setProductPriceRuleId(String  productPriceRuleId) {
this.productPriceRuleId = productPriceRuleId;
}

public String getProductPriceActionSeqId() {
return productPriceActionSeqId;
}

public void setProductPriceActionSeqId(String  productPriceActionSeqId) {
this.productPriceActionSeqId = productPriceActionSeqId;
}

public String getProductPriceActionTypeId() {
return productPriceActionTypeId;
}

public void setProductPriceActionTypeId(String  productPriceActionTypeId) {
this.productPriceActionTypeId = productPriceActionTypeId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getRateCode() {
return rateCode;
}

public void setRateCode(String  rateCode) {
this.rateCode = rateCode;
}


public Map<String, Object> mapAttributeField() {
return ProductPriceActionMapper.map(this);
}
}

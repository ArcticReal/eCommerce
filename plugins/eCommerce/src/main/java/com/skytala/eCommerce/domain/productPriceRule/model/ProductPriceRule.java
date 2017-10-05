package com.skytala.eCommerce.domain.productPriceRule.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.productPriceRule.mapper.ProductPriceRuleMapper;

public class ProductPriceRule implements Serializable{

private static final long serialVersionUID = 1L;
private String productPriceRuleId;
private String ruleName;
private String description;
private Boolean isSale;
private Timestamp fromDate;
private Timestamp thruDate;

public String getProductPriceRuleId() {
return productPriceRuleId;
}

public void setProductPriceRuleId(String  productPriceRuleId) {
this.productPriceRuleId = productPriceRuleId;
}

public String getRuleName() {
return ruleName;
}

public void setRuleName(String  ruleName) {
this.ruleName = ruleName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Boolean getIsSale() {
return isSale;
}

public void setIsSale(Boolean  isSale) {
this.isSale = isSale;
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


public Map<String, Object> mapAttributeField() {
return ProductPriceRuleMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.marketInterest.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.marketInterest.mapper.MarketInterestMapper;

public class MarketInterest implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryId;
private String partyClassificationGroupId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getPartyClassificationGroupId() {
return partyClassificationGroupId;
}

public void setPartyClassificationGroupId(String  partyClassificationGroupId) {
this.partyClassificationGroupId = partyClassificationGroupId;
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
return MarketInterestMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.mapper.ProductPromoCodeEmailMapper;

public class ProductPromoCodeEmail implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoCodeId;
private String emailAddress;

public String getProductPromoCodeId() {
return productPromoCodeId;
}

public void setProductPromoCodeId(String  productPromoCodeId) {
this.productPromoCodeId = productPromoCodeId;
}

public String getEmailAddress() {
return emailAddress;
}

public void setEmailAddress(String  emailAddress) {
this.emailAddress = emailAddress;
}


public Map<String, Object> mapAttributeField() {
return ProductPromoCodeEmailMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.productPromoContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productPromoContent.mapper.ProductPromoContentMapper;

public class ProductPromoContent implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoId;
private String contentId;
private String productPromoContentTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getProductPromoContentTypeId() {
return productPromoContentTypeId;
}

public void setProductPromoContentTypeId(String  productPromoContentTypeId) {
this.productPromoContentTypeId = productPromoContentTypeId;
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
return ProductPromoContentMapper.map(this);
}
}

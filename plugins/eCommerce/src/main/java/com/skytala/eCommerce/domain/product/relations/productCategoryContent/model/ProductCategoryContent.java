package com.skytala.eCommerce.domain.product.relations.productCategoryContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productCategoryContent.mapper.ProductCategoryContentMapper;

public class ProductCategoryContent implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryId;
private String contentId;
private String prodCatContentTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Timestamp purchaseFromDate;
private Timestamp purchaseThruDate;
private Long useCountLimit;
private BigDecimal useDaysLimit;

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getProdCatContentTypeId() {
return prodCatContentTypeId;
}

public void setProdCatContentTypeId(String  prodCatContentTypeId) {
this.prodCatContentTypeId = prodCatContentTypeId;
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

public Timestamp getPurchaseFromDate() {
return purchaseFromDate;
}

public void setPurchaseFromDate(Timestamp  purchaseFromDate) {
this.purchaseFromDate = purchaseFromDate;
}

public Timestamp getPurchaseThruDate() {
return purchaseThruDate;
}

public void setPurchaseThruDate(Timestamp  purchaseThruDate) {
this.purchaseThruDate = purchaseThruDate;
}

public Long getUseCountLimit() {
return useCountLimit;
}

public void setUseCountLimit(Long  useCountLimit) {
this.useCountLimit = useCountLimit;
}

public BigDecimal getUseDaysLimit() {
return useDaysLimit;
}

public void setUseDaysLimit(BigDecimal  useDaysLimit) {
this.useDaysLimit = useDaysLimit;
}


public Map<String, Object> mapAttributeField() {
return ProductCategoryContentMapper.map(this);
}
}

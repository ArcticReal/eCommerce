package com.skytala.eCommerce.domain.product.relations.product.model.priceChange;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceChange.ProductPriceChangeMapper;

public class ProductPriceChange implements Serializable{

private static final long serialVersionUID = 1L;
private String productPriceChangeId;
private String productId;
private String productPriceTypeId;
private String productPricePurposeId;
private String currencyUomId;
private String productStoreGroupId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal price;
private BigDecimal oldPrice;
private Timestamp changedDate;
private String changedByUserLogin;

public String getProductPriceChangeId() {
return productPriceChangeId;
}

public void setProductPriceChangeId(String  productPriceChangeId) {
this.productPriceChangeId = productPriceChangeId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductPriceTypeId() {
return productPriceTypeId;
}

public void setProductPriceTypeId(String  productPriceTypeId) {
this.productPriceTypeId = productPriceTypeId;
}

public String getProductPricePurposeId() {
return productPricePurposeId;
}

public void setProductPricePurposeId(String  productPricePurposeId) {
this.productPricePurposeId = productPricePurposeId;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getProductStoreGroupId() {
return productStoreGroupId;
}

public void setProductStoreGroupId(String  productStoreGroupId) {
this.productStoreGroupId = productStoreGroupId;
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

public BigDecimal getPrice() {
return price;
}

public void setPrice(BigDecimal  price) {
this.price = price;
}

public BigDecimal getOldPrice() {
return oldPrice;
}

public void setOldPrice(BigDecimal  oldPrice) {
this.oldPrice = oldPrice;
}

public Timestamp getChangedDate() {
return changedDate;
}

public void setChangedDate(Timestamp  changedDate) {
this.changedDate = changedDate;
}

public String getChangedByUserLogin() {
return changedByUserLogin;
}

public void setChangedByUserLogin(String  changedByUserLogin) {
this.changedByUserLogin = changedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return ProductPriceChangeMapper.map(this);
}
}

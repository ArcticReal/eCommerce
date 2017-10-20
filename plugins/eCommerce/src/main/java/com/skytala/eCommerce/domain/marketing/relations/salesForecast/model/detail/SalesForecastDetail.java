package com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.detail;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.detail.SalesForecastDetailMapper;

public class SalesForecastDetail implements Serializable{

private static final long serialVersionUID = 1L;
private String salesForecastId;
private String salesForecastDetailId;
private BigDecimal amount;
private String quantityUomId;
private BigDecimal quantity;
private String productId;
private String productCategoryId;

public String getSalesForecastId() {
return salesForecastId;
}

public void setSalesForecastId(String  salesForecastId) {
this.salesForecastId = salesForecastId;
}

public String getSalesForecastDetailId() {
return salesForecastDetailId;
}

public void setSalesForecastDetailId(String  salesForecastDetailId) {
this.salesForecastDetailId = salesForecastDetailId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getQuantityUomId() {
return quantityUomId;
}

public void setQuantityUomId(String  quantityUomId) {
this.quantityUomId = quantityUomId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}


public Map<String, Object> mapAttributeField() {
return SalesForecastDetailMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.mapper.ProductCalculatedInfoMapper;

public class ProductCalculatedInfo implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private BigDecimal totalQuantityOrdered;
private Long totalTimesViewed;
private BigDecimal averageCustomerRating;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public BigDecimal getTotalQuantityOrdered() {
return totalQuantityOrdered;
}

public void setTotalQuantityOrdered(BigDecimal  totalQuantityOrdered) {
this.totalQuantityOrdered = totalQuantityOrdered;
}

public Long getTotalTimesViewed() {
return totalTimesViewed;
}

public void setTotalTimesViewed(Long  totalTimesViewed) {
this.totalTimesViewed = totalTimesViewed;
}

public BigDecimal getAverageCustomerRating() {
return averageCustomerRating;
}

public void setAverageCustomerRating(BigDecimal  averageCustomerRating) {
this.averageCustomerRating = averageCustomerRating;
}


public Map<String, Object> mapAttributeField() {
return ProductCalculatedInfoMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.productKeyword.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productKeyword.mapper.ProductKeywordMapper;

public class ProductKeyword implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String keyword;
private String keywordTypeId;
private Long relevancyWeight;
private String statusId;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getKeyword() {
return keyword;
}

public void setKeyword(String  keyword) {
this.keyword = keyword;
}

public String getKeywordTypeId() {
return keywordTypeId;
}

public void setKeywordTypeId(String  keywordTypeId) {
this.keywordTypeId = keywordTypeId;
}

public Long getRelevancyWeight() {
return relevancyWeight;
}

public void setRelevancyWeight(Long  relevancyWeight) {
this.relevancyWeight = relevancyWeight;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}


public Map<String, Object> mapAttributeField() {
return ProductKeywordMapper.map(this);
}
}

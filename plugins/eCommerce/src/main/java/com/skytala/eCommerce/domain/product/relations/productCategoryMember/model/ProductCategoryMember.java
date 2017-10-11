package com.skytala.eCommerce.domain.product.relations.productCategoryMember.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.mapper.ProductCategoryMemberMapper;

public class ProductCategoryMember implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryId;
private String productId;
private Timestamp fromDate;
private Timestamp thruDate;
private String comments;
private Long sequenceNum;
private BigDecimal quantity;

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
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

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}


public Map<String, Object> mapAttributeField() {
return ProductCategoryMemberMapper.map(this);
}
}

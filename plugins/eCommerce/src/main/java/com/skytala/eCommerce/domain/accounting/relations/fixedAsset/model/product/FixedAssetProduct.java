package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.product;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.product.FixedAssetProductMapper;

public class FixedAssetProduct implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private String productId;
private String fixedAssetProductTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String comments;
private Long sequenceNum;
private BigDecimal quantity;
private String quantityUomId;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getFixedAssetProductTypeId() {
return fixedAssetProductTypeId;
}

public void setFixedAssetProductTypeId(String  fixedAssetProductTypeId) {
this.fixedAssetProductTypeId = fixedAssetProductTypeId;
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

public String getQuantityUomId() {
return quantityUomId;
}

public void setQuantityUomId(String  quantityUomId) {
this.quantityUomId = quantityUomId;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetProductMapper.map(this);
}
}

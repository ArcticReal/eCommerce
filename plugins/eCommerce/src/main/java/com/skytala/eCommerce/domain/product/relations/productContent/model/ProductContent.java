package com.skytala.eCommerce.domain.product.relations.productContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productContent.mapper.ProductContentMapper;

public class ProductContent implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String contentId;
private String productContentTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Timestamp purchaseFromDate;
private Timestamp purchaseThruDate;
private Long useCountLimit;
private Long useTime;
private String useTimeUomId;
private String useRoleTypeId;
private Long sequenceNum;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getProductContentTypeId() {
return productContentTypeId;
}

public void setProductContentTypeId(String  productContentTypeId) {
this.productContentTypeId = productContentTypeId;
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

public Long getUseTime() {
return useTime;
}

public void setUseTime(Long  useTime) {
this.useTime = useTime;
}

public String getUseTimeUomId() {
return useTimeUomId;
}

public void setUseTimeUomId(String  useTimeUomId) {
this.useTimeUomId = useTimeUomId;
}

public String getUseRoleTypeId() {
return useRoleTypeId;
}

public void setUseRoleTypeId(String  useRoleTypeId) {
this.useRoleTypeId = useRoleTypeId;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ProductContentMapper.map(this);
}
}

package com.skytala.eCommerce.domain.product.relations.product.model.maint;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.maint.ProductMaintMapper;

public class ProductMaint implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String productMaintSeqId;
private String productMaintTypeId;
private String maintName;
private String maintTemplateWorkEffortId;
private BigDecimal intervalQuantity;
private String intervalUomId;
private String intervalMeterTypeId;
private Long repeatCount;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductMaintSeqId() {
return productMaintSeqId;
}

public void setProductMaintSeqId(String  productMaintSeqId) {
this.productMaintSeqId = productMaintSeqId;
}

public String getProductMaintTypeId() {
return productMaintTypeId;
}

public void setProductMaintTypeId(String  productMaintTypeId) {
this.productMaintTypeId = productMaintTypeId;
}

public String getMaintName() {
return maintName;
}

public void setMaintName(String  maintName) {
this.maintName = maintName;
}

public String getMaintTemplateWorkEffortId() {
return maintTemplateWorkEffortId;
}

public void setMaintTemplateWorkEffortId(String  maintTemplateWorkEffortId) {
this.maintTemplateWorkEffortId = maintTemplateWorkEffortId;
}

public BigDecimal getIntervalQuantity() {
return intervalQuantity;
}

public void setIntervalQuantity(BigDecimal  intervalQuantity) {
this.intervalQuantity = intervalQuantity;
}

public String getIntervalUomId() {
return intervalUomId;
}

public void setIntervalUomId(String  intervalUomId) {
this.intervalUomId = intervalUomId;
}

public String getIntervalMeterTypeId() {
return intervalMeterTypeId;
}

public void setIntervalMeterTypeId(String  intervalMeterTypeId) {
this.intervalMeterTypeId = intervalMeterTypeId;
}

public Long getRepeatCount() {
return repeatCount;
}

public void setRepeatCount(Long  repeatCount) {
this.repeatCount = repeatCount;
}


public Map<String, Object> mapAttributeField() {
return ProductMaintMapper.map(this);
}
}

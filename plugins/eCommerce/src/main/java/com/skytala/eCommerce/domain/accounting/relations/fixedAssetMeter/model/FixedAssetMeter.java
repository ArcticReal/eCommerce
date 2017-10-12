package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.mapper.FixedAssetMeterMapper;

public class FixedAssetMeter implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private String productMeterTypeId;
private Timestamp readingDate;
private BigDecimal meterValue;
private String readingReasonEnumId;
private String maintHistSeqId;
private String workEffortId;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getProductMeterTypeId() {
return productMeterTypeId;
}

public void setProductMeterTypeId(String  productMeterTypeId) {
this.productMeterTypeId = productMeterTypeId;
}

public Timestamp getReadingDate() {
return readingDate;
}

public void setReadingDate(Timestamp  readingDate) {
this.readingDate = readingDate;
}

public BigDecimal getMeterValue() {
return meterValue;
}

public void setMeterValue(BigDecimal  meterValue) {
this.meterValue = meterValue;
}

public String getReadingReasonEnumId() {
return readingReasonEnumId;
}

public void setReadingReasonEnumId(String  readingReasonEnumId) {
this.readingReasonEnumId = readingReasonEnumId;
}

public String getMaintHistSeqId() {
return maintHistSeqId;
}

public void setMaintHistSeqId(String  maintHistSeqId) {
this.maintHistSeqId = maintHistSeqId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetMeterMapper.map(this);
}
}

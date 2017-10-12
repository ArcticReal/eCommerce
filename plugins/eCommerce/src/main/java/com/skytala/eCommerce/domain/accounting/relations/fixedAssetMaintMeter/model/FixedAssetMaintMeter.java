package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.mapper.FixedAssetMaintMeterMapper;

public class FixedAssetMaintMeter implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private String maintHistSeqId;
private String productMeterTypeId;
private BigDecimal meterValue;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getMaintHistSeqId() {
return maintHistSeqId;
}

public void setMaintHistSeqId(String  maintHistSeqId) {
this.maintHistSeqId = maintHistSeqId;
}

public String getProductMeterTypeId() {
return productMeterTypeId;
}

public void setProductMeterTypeId(String  productMeterTypeId) {
this.productMeterTypeId = productMeterTypeId;
}

public BigDecimal getMeterValue() {
return meterValue;
}

public void setMeterValue(BigDecimal  meterValue) {
this.meterValue = meterValue;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetMaintMeterMapper.map(this);
}
}

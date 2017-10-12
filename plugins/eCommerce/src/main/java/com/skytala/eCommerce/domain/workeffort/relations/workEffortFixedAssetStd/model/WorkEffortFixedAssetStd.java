package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetStd.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetStd.mapper.WorkEffortFixedAssetStdMapper;

public class WorkEffortFixedAssetStd implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String fixedAssetTypeId;
private BigDecimal estimatedQuantity;
private BigDecimal estimatedDuration;
private BigDecimal estimatedCost;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getFixedAssetTypeId() {
return fixedAssetTypeId;
}

public void setFixedAssetTypeId(String  fixedAssetTypeId) {
this.fixedAssetTypeId = fixedAssetTypeId;
}

public BigDecimal getEstimatedQuantity() {
return estimatedQuantity;
}

public void setEstimatedQuantity(BigDecimal  estimatedQuantity) {
this.estimatedQuantity = estimatedQuantity;
}

public BigDecimal getEstimatedDuration() {
return estimatedDuration;
}

public void setEstimatedDuration(BigDecimal  estimatedDuration) {
this.estimatedDuration = estimatedDuration;
}

public BigDecimal getEstimatedCost() {
return estimatedCost;
}

public void setEstimatedCost(BigDecimal  estimatedCost) {
this.estimatedCost = estimatedCost;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortFixedAssetStdMapper.map(this);
}
}

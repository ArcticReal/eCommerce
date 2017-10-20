package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandard;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.goodStandard.WorkEffortGoodStandardMapper;

public class WorkEffortGoodStandard implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String productId;
private String workEffortGoodStdTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String statusId;
private BigDecimal estimatedQuantity;
private BigDecimal estimatedCost;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getWorkEffortGoodStdTypeId() {
return workEffortGoodStdTypeId;
}

public void setWorkEffortGoodStdTypeId(String  workEffortGoodStdTypeId) {
this.workEffortGoodStdTypeId = workEffortGoodStdTypeId;
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

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public BigDecimal getEstimatedQuantity() {
return estimatedQuantity;
}

public void setEstimatedQuantity(BigDecimal  estimatedQuantity) {
this.estimatedQuantity = estimatedQuantity;
}

public BigDecimal getEstimatedCost() {
return estimatedCost;
}

public void setEstimatedCost(BigDecimal  estimatedCost) {
this.estimatedCost = estimatedCost;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortGoodStandardMapper.map(this);
}
}

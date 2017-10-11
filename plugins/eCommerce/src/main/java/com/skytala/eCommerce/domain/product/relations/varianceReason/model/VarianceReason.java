package com.skytala.eCommerce.domain.product.relations.varianceReason.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.varianceReason.mapper.VarianceReasonMapper;

public class VarianceReason implements Serializable{

private static final long serialVersionUID = 1L;
private String varianceReasonId;
private String description;

public String getVarianceReasonId() {
return varianceReasonId;
}

public void setVarianceReasonId(String  varianceReasonId) {
this.varianceReasonId = varianceReasonId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return VarianceReasonMapper.map(this);
}
}

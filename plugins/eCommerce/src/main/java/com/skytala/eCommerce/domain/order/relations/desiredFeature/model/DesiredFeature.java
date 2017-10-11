package com.skytala.eCommerce.domain.order.relations.desiredFeature.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.mapper.DesiredFeatureMapper;

public class DesiredFeature implements Serializable{

private static final long serialVersionUID = 1L;
private String desiredFeatureId;
private String requirementId;
private String productFeatureId;
private Boolean optionalInd;

public String getDesiredFeatureId() {
return desiredFeatureId;
}

public void setDesiredFeatureId(String  desiredFeatureId) {
this.desiredFeatureId = desiredFeatureId;
}

public String getRequirementId() {
return requirementId;
}

public void setRequirementId(String  requirementId) {
this.requirementId = requirementId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public Boolean getOptionalInd() {
return optionalInd;
}

public void setOptionalInd(Boolean  optionalInd) {
this.optionalInd = optionalInd;
}


public Map<String, Object> mapAttributeField() {
return DesiredFeatureMapper.map(this);
}
}

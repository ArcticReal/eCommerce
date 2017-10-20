package com.skytala.eCommerce.domain.order.relations.custRequest.model.resolution;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.resolution.CustRequestResolutionMapper;

public class CustRequestResolution implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestResolutionId;
private String custRequestTypeId;
private String description;

public String getCustRequestResolutionId() {
return custRequestResolutionId;
}

public void setCustRequestResolutionId(String  custRequestResolutionId) {
this.custRequestResolutionId = custRequestResolutionId;
}

public String getCustRequestTypeId() {
return custRequestTypeId;
}

public void setCustRequestTypeId(String  custRequestTypeId) {
this.custRequestTypeId = custRequestTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return CustRequestResolutionMapper.map(this);
}
}

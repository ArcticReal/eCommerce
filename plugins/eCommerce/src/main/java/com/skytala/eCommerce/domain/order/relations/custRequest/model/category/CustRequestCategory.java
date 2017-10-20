package com.skytala.eCommerce.domain.order.relations.custRequest.model.category;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.category.CustRequestCategoryMapper;

public class CustRequestCategory implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestCategoryId;
private String custRequestTypeId;
private String description;

public String getCustRequestCategoryId() {
return custRequestCategoryId;
}

public void setCustRequestCategoryId(String  custRequestCategoryId) {
this.custRequestCategoryId = custRequestCategoryId;
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
return CustRequestCategoryMapper.map(this);
}
}

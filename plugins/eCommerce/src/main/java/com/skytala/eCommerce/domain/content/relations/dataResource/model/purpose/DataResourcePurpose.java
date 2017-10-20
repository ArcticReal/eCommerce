package com.skytala.eCommerce.domain.content.relations.dataResource.model.purpose;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.purpose.DataResourcePurposeMapper;

public class DataResourcePurpose implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private String contentPurposeTypeId;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public String getContentPurposeTypeId() {
return contentPurposeTypeId;
}

public void setContentPurposeTypeId(String  contentPurposeTypeId) {
this.contentPurposeTypeId = contentPurposeTypeId;
}


public Map<String, Object> mapAttributeField() {
return DataResourcePurposeMapper.map(this);
}
}

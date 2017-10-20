package com.skytala.eCommerce.domain.content.relations.dataResource.model.other;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.other.OtherDataResourceMapper;

public class OtherDataResource implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private byte[] dataResourceContent;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public byte[] getDataResourceContent() {
return dataResourceContent;
}

public void setDataResourceContent(byte[]  dataResourceContent) {
this.dataResourceContent = dataResourceContent;
}


public Map<String, Object> mapAttributeField() {
return OtherDataResourceMapper.map(this);
}
}

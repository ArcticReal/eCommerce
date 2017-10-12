package com.skytala.eCommerce.domain.content.relations.electronicText.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.electronicText.mapper.ElectronicTextMapper;

public class ElectronicText implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private String textData;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public String getTextData() {
return textData;
}

public void setTextData(String  textData) {
this.textData = textData;
}


public Map<String, Object> mapAttributeField() {
return ElectronicTextMapper.map(this);
}
}

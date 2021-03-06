package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper.ProdConfItemContentMapper;

public class ProdConfItemContent implements Serializable{

private static final long serialVersionUID = 1L;
private String configItemId;
private String contentId;
private String confItemContentTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getConfigItemId() {
return configItemId;
}

public void setConfigItemId(String  configItemId) {
this.configItemId = configItemId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getConfItemContentTypeId() {
return confItemContentTypeId;
}

public void setConfItemContentTypeId(String  confItemContentTypeId) {
this.confItemContentTypeId = confItemContentTypeId;
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


public Map<String, Object> mapAttributeField() {
return ProdConfItemContentMapper.map(this);
}
}

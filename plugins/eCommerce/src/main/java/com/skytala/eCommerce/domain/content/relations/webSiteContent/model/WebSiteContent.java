package com.skytala.eCommerce.domain.content.relations.webSiteContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.webSiteContent.mapper.WebSiteContentMapper;

public class WebSiteContent implements Serializable{

private static final long serialVersionUID = 1L;
private String webSiteId;
private String contentId;
private String webSiteContentTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getWebSiteId() {
return webSiteId;
}

public void setWebSiteId(String  webSiteId) {
this.webSiteId = webSiteId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getWebSiteContentTypeId() {
return webSiteContentTypeId;
}

public void setWebSiteContentTypeId(String  webSiteContentTypeId) {
this.webSiteContentTypeId = webSiteContentTypeId;
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
return WebSiteContentMapper.map(this);
}
}

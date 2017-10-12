package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.mapper.WebSitePathAliasMapper;

public class WebSitePathAlias implements Serializable{

private static final long serialVersionUID = 1L;
private String webSiteId;
private String pathAlias;
private Timestamp fromDate;
private Timestamp thruDate;
private String aliasTo;
private String contentId;
private String mapKey;

public String getWebSiteId() {
return webSiteId;
}

public void setWebSiteId(String  webSiteId) {
this.webSiteId = webSiteId;
}

public String getPathAlias() {
return pathAlias;
}

public void setPathAlias(String  pathAlias) {
this.pathAlias = pathAlias;
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

public String getAliasTo() {
return aliasTo;
}

public void setAliasTo(String  aliasTo) {
this.aliasTo = aliasTo;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getMapKey() {
return mapKey;
}

public void setMapKey(String  mapKey) {
this.mapKey = mapKey;
}


public Map<String, Object> mapAttributeField() {
return WebSitePathAliasMapper.map(this);
}
}

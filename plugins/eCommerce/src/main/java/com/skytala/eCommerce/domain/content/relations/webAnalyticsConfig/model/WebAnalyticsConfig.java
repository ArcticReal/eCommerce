package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.mapper.WebAnalyticsConfigMapper;

public class WebAnalyticsConfig implements Serializable{

private static final long serialVersionUID = 1L;
private String webSiteId;
private String webAnalyticsTypeId;
private String webAnalyticsCode;

public String getWebSiteId() {
return webSiteId;
}

public void setWebSiteId(String  webSiteId) {
this.webSiteId = webSiteId;
}

public String getWebAnalyticsTypeId() {
return webAnalyticsTypeId;
}

public void setWebAnalyticsTypeId(String  webAnalyticsTypeId) {
this.webAnalyticsTypeId = webAnalyticsTypeId;
}

public String getWebAnalyticsCode() {
return webAnalyticsCode;
}

public void setWebAnalyticsCode(String  webAnalyticsCode) {
this.webAnalyticsCode = webAnalyticsCode;
}


public Map<String, Object> mapAttributeField() {
return WebAnalyticsConfigMapper.map(this);
}
}

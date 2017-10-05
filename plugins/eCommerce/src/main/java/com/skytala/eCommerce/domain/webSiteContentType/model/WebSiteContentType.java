package com.skytala.eCommerce.domain.webSiteContentType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.webSiteContentType.mapper.WebSiteContentTypeMapper;

public class WebSiteContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String webSiteContentTypeId;
private String description;
private String parentTypeId;
private Boolean hasTable;

public String getWebSiteContentTypeId() {
return webSiteContentTypeId;
}

public void setWebSiteContentTypeId(String  webSiteContentTypeId) {
this.webSiteContentTypeId = webSiteContentTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}


public Map<String, Object> mapAttributeField() {
return WebSiteContentTypeMapper.map(this);
}
}

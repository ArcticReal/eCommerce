package com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.mapper.MimeTypeHtmlTemplateMapper;

public class MimeTypeHtmlTemplate implements Serializable{

private static final long serialVersionUID = 1L;
private String mimeTypeId;
private String templateLocation;

public String getMimeTypeId() {
return mimeTypeId;
}

public void setMimeTypeId(String  mimeTypeId) {
this.mimeTypeId = mimeTypeId;
}

public String getTemplateLocation() {
return templateLocation;
}

public void setTemplateLocation(String  templateLocation) {
this.templateLocation = templateLocation;
}


public Map<String, Object> mapAttributeField() {
return MimeTypeHtmlTemplateMapper.map(this);
}
}

package com.skytala.eCommerce.domain.mimeType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.mimeType.mapper.MimeTypeMapper;

public class MimeType implements Serializable{

private static final long serialVersionUID = 1L;
private String mimeTypeId;
private String description;

public String getMimeTypeId() {
return mimeTypeId;
}

public void setMimeTypeId(String  mimeTypeId) {
this.mimeTypeId = mimeTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return MimeTypeMapper.map(this);
}
}

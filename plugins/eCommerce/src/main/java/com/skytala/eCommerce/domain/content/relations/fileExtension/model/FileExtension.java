package com.skytala.eCommerce.domain.content.relations.fileExtension.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.fileExtension.mapper.FileExtensionMapper;

public class FileExtension implements Serializable{

private static final long serialVersionUID = 1L;
private String fileExtensionId;
private String mimeTypeId;

public String getFileExtensionId() {
return fileExtensionId;
}

public void setFileExtensionId(String  fileExtensionId) {
this.fileExtensionId = fileExtensionId;
}

public String getMimeTypeId() {
return mimeTypeId;
}

public void setMimeTypeId(String  mimeTypeId) {
this.mimeTypeId = mimeTypeId;
}


public Map<String, Object> mapAttributeField() {
return FileExtensionMapper.map(this);
}
}

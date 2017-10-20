package com.skytala.eCommerce.domain.content.relations.dataResource.model.image;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.image.ImageDataResourceMapper;

public class ImageDataResource implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private byte[] imageData;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public byte[] getImageData() {
return imageData;
}

public void setImageData(byte[]  imageData) {
this.imageData = imageData;
}


public Map<String, Object> mapAttributeField() {
return ImageDataResourceMapper.map(this);
}
}

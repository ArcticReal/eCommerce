package com.skytala.eCommerce.domain.content.relations.dataResource.model.video;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.video.VideoDataResourceMapper;

public class VideoDataResource implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private byte[] videoData;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public byte[] getVideoData() {
return videoData;
}

public void setVideoData(byte[]  videoData) {
this.videoData = videoData;
}


public Map<String, Object> mapAttributeField() {
return VideoDataResourceMapper.map(this);
}
}

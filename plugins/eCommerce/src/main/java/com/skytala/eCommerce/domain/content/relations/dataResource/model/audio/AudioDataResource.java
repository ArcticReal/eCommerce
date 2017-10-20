package com.skytala.eCommerce.domain.content.relations.dataResource.model.audio;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.audio.AudioDataResourceMapper;

public class AudioDataResource implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private byte[] audioData;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public byte[] getAudioData() {
return audioData;
}

public void setAudioData(byte[]  audioData) {
this.audioData = audioData;
}


public Map<String, Object> mapAttributeField() {
return AudioDataResourceMapper.map(this);
}
}

package com.skytala.eCommerce.domain.characterSet.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.characterSet.mapper.CharacterSetMapper;

public class CharacterSet implements Serializable{

private static final long serialVersionUID = 1L;
private String characterSetId;
private String description;

public String getCharacterSetId() {
return characterSetId;
}

public void setCharacterSetId(String  characterSetId) {
this.characterSetId = characterSetId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return CharacterSetMapper.map(this);
}
}

package com.skytala.eCommerce.domain.content.relations.characterSet.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.characterSet.model.CharacterSet;

public class CharacterSetMapper  {


	public static Map<String, Object> map(CharacterSet characterset) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(characterset.getCharacterSetId() != null ){
			returnVal.put("characterSetId",characterset.getCharacterSetId());
}

		if(characterset.getDescription() != null ){
			returnVal.put("description",characterset.getDescription());
}

		return returnVal;
}


	public static CharacterSet map(Map<String, Object> fields) {

		CharacterSet returnVal = new CharacterSet();

		if(fields.get("characterSetId") != null) {
			returnVal.setCharacterSetId((String) fields.get("characterSetId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CharacterSet mapstrstr(Map<String, String> fields) throws Exception {

		CharacterSet returnVal = new CharacterSet();

		if(fields.get("characterSetId") != null) {
			returnVal.setCharacterSetId((String) fields.get("characterSetId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CharacterSet map(GenericValue val) {

CharacterSet returnVal = new CharacterSet();
		returnVal.setCharacterSetId(val.getString("characterSetId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CharacterSet map(HttpServletRequest request) throws Exception {

		CharacterSet returnVal = new CharacterSet();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("characterSetId")) {
returnVal.setCharacterSetId(request.getParameter("characterSetId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

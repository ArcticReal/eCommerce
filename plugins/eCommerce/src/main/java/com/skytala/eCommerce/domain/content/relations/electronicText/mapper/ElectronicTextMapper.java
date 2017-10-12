package com.skytala.eCommerce.domain.content.relations.electronicText.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.electronicText.model.ElectronicText;

public class ElectronicTextMapper  {


	public static Map<String, Object> map(ElectronicText electronictext) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(electronictext.getDataResourceId() != null ){
			returnVal.put("dataResourceId",electronictext.getDataResourceId());
}

		if(electronictext.getTextData() != null ){
			returnVal.put("textData",electronictext.getTextData());
}

		return returnVal;
}


	public static ElectronicText map(Map<String, Object> fields) {

		ElectronicText returnVal = new ElectronicText();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("textData") != null) {
			returnVal.setTextData((String) fields.get("textData"));
}


		return returnVal;
 } 
	public static ElectronicText mapstrstr(Map<String, String> fields) throws Exception {

		ElectronicText returnVal = new ElectronicText();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("textData") != null) {
			returnVal.setTextData((String) fields.get("textData"));
}


		return returnVal;
 } 
	public static ElectronicText map(GenericValue val) {

ElectronicText returnVal = new ElectronicText();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setTextData(val.getString("textData"));


return returnVal;

}

public static ElectronicText map(HttpServletRequest request) throws Exception {

		ElectronicText returnVal = new ElectronicText();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("textData"))  {
returnVal.setTextData(request.getParameter("textData"));
}
return returnVal;

}
}

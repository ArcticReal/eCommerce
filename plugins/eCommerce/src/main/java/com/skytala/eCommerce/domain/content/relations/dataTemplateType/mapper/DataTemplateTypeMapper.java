package com.skytala.eCommerce.domain.content.relations.dataTemplateType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.model.DataTemplateType;

public class DataTemplateTypeMapper  {


	public static Map<String, Object> map(DataTemplateType datatemplatetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(datatemplatetype.getDataTemplateTypeId() != null ){
			returnVal.put("dataTemplateTypeId",datatemplatetype.getDataTemplateTypeId());
}

		if(datatemplatetype.getDescription() != null ){
			returnVal.put("description",datatemplatetype.getDescription());
}

		if(datatemplatetype.getExtension() != null ){
			returnVal.put("extension",datatemplatetype.getExtension());
}

		return returnVal;
}


	public static DataTemplateType map(Map<String, Object> fields) {

		DataTemplateType returnVal = new DataTemplateType();

		if(fields.get("dataTemplateTypeId") != null) {
			returnVal.setDataTemplateTypeId((String) fields.get("dataTemplateTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("extension") != null) {
			returnVal.setExtension((String) fields.get("extension"));
}


		return returnVal;
 } 
	public static DataTemplateType mapstrstr(Map<String, String> fields) throws Exception {

		DataTemplateType returnVal = new DataTemplateType();

		if(fields.get("dataTemplateTypeId") != null) {
			returnVal.setDataTemplateTypeId((String) fields.get("dataTemplateTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("extension") != null) {
			returnVal.setExtension((String) fields.get("extension"));
}


		return returnVal;
 } 
	public static DataTemplateType map(GenericValue val) {

DataTemplateType returnVal = new DataTemplateType();
		returnVal.setDataTemplateTypeId(val.getString("dataTemplateTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setExtension(val.getString("extension"));


return returnVal;

}

public static DataTemplateType map(HttpServletRequest request) throws Exception {

		DataTemplateType returnVal = new DataTemplateType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataTemplateTypeId")) {
returnVal.setDataTemplateTypeId(request.getParameter("dataTemplateTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("extension"))  {
returnVal.setExtension(request.getParameter("extension"));
}
return returnVal;

}
}

package com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.model.DataResourcePurpose;

public class DataResourcePurposeMapper  {


	public static Map<String, Object> map(DataResourcePurpose dataresourcepurpose) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(dataresourcepurpose.getDataResourceId() != null ){
			returnVal.put("dataResourceId",dataresourcepurpose.getDataResourceId());
}

		if(dataresourcepurpose.getContentPurposeTypeId() != null ){
			returnVal.put("contentPurposeTypeId",dataresourcepurpose.getContentPurposeTypeId());
}

		return returnVal;
}


	public static DataResourcePurpose map(Map<String, Object> fields) {

		DataResourcePurpose returnVal = new DataResourcePurpose();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("contentPurposeTypeId") != null) {
			returnVal.setContentPurposeTypeId((String) fields.get("contentPurposeTypeId"));
}


		return returnVal;
 } 
	public static DataResourcePurpose mapstrstr(Map<String, String> fields) throws Exception {

		DataResourcePurpose returnVal = new DataResourcePurpose();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("contentPurposeTypeId") != null) {
			returnVal.setContentPurposeTypeId((String) fields.get("contentPurposeTypeId"));
}


		return returnVal;
 } 
	public static DataResourcePurpose map(GenericValue val) {

DataResourcePurpose returnVal = new DataResourcePurpose();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setContentPurposeTypeId(val.getString("contentPurposeTypeId"));


return returnVal;

}

public static DataResourcePurpose map(HttpServletRequest request) throws Exception {

		DataResourcePurpose returnVal = new DataResourcePurpose();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("contentPurposeTypeId"))  {
returnVal.setContentPurposeTypeId(request.getParameter("contentPurposeTypeId"));
}
return returnVal;

}
}

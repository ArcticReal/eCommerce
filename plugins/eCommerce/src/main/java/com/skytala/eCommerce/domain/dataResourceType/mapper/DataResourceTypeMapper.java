package com.skytala.eCommerce.domain.dataResourceType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.dataResourceType.model.DataResourceType;

public class DataResourceTypeMapper  {


	public static Map<String, Object> map(DataResourceType dataresourcetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(dataresourcetype.getDataResourceTypeId() != null ){
			returnVal.put("dataResourceTypeId",dataresourcetype.getDataResourceTypeId());
}

		if(dataresourcetype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",dataresourcetype.getParentTypeId());
}

		if(dataresourcetype.getHasTable() != null ){
			returnVal.put("hasTable",dataresourcetype.getHasTable());
}

		if(dataresourcetype.getDescription() != null ){
			returnVal.put("description",dataresourcetype.getDescription());
}

		return returnVal;
}


	public static DataResourceType map(Map<String, Object> fields) {

		DataResourceType returnVal = new DataResourceType();

		if(fields.get("dataResourceTypeId") != null) {
			returnVal.setDataResourceTypeId((String) fields.get("dataResourceTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DataResourceType mapstrstr(Map<String, String> fields) throws Exception {

		DataResourceType returnVal = new DataResourceType();

		if(fields.get("dataResourceTypeId") != null) {
			returnVal.setDataResourceTypeId((String) fields.get("dataResourceTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DataResourceType map(GenericValue val) {

DataResourceType returnVal = new DataResourceType();
		returnVal.setDataResourceTypeId(val.getString("dataResourceTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static DataResourceType map(HttpServletRequest request) throws Exception {

		DataResourceType returnVal = new DataResourceType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceTypeId")) {
returnVal.setDataResourceTypeId(request.getParameter("dataResourceTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

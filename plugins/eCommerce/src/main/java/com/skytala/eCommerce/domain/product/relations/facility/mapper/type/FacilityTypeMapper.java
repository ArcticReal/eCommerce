package com.skytala.eCommerce.domain.product.relations.facility.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.model.type.FacilityType;

public class FacilityTypeMapper  {


	public static Map<String, Object> map(FacilityType facilitytype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitytype.getFacilityTypeId() != null ){
			returnVal.put("facilityTypeId",facilitytype.getFacilityTypeId());
}

		if(facilitytype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",facilitytype.getParentTypeId());
}

		if(facilitytype.getHasTable() != null ){
			returnVal.put("hasTable",facilitytype.getHasTable());
}

		if(facilitytype.getDescription() != null ){
			returnVal.put("description",facilitytype.getDescription());
}

		return returnVal;
}


	public static FacilityType map(Map<String, Object> fields) {

		FacilityType returnVal = new FacilityType();

		if(fields.get("facilityTypeId") != null) {
			returnVal.setFacilityTypeId((String) fields.get("facilityTypeId"));
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
	public static FacilityType mapstrstr(Map<String, String> fields) throws Exception {

		FacilityType returnVal = new FacilityType();

		if(fields.get("facilityTypeId") != null) {
			returnVal.setFacilityTypeId((String) fields.get("facilityTypeId"));
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
	public static FacilityType map(GenericValue val) {

FacilityType returnVal = new FacilityType();
		returnVal.setFacilityTypeId(val.getString("facilityTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FacilityType map(HttpServletRequest request) throws Exception {

		FacilityType returnVal = new FacilityType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityTypeId")) {
returnVal.setFacilityTypeId(request.getParameter("facilityTypeId"));
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

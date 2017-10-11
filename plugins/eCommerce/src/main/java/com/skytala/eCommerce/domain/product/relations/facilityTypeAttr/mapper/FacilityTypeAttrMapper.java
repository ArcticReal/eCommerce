package com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.model.FacilityTypeAttr;

public class FacilityTypeAttrMapper  {


	public static Map<String, Object> map(FacilityTypeAttr facilitytypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitytypeattr.getFacilityTypeId() != null ){
			returnVal.put("facilityTypeId",facilitytypeattr.getFacilityTypeId());
}

		if(facilitytypeattr.getAttrName() != null ){
			returnVal.put("attrName",facilitytypeattr.getAttrName());
}

		if(facilitytypeattr.getDescription() != null ){
			returnVal.put("description",facilitytypeattr.getDescription());
}

		return returnVal;
}


	public static FacilityTypeAttr map(Map<String, Object> fields) {

		FacilityTypeAttr returnVal = new FacilityTypeAttr();

		if(fields.get("facilityTypeId") != null) {
			returnVal.setFacilityTypeId((String) fields.get("facilityTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FacilityTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		FacilityTypeAttr returnVal = new FacilityTypeAttr();

		if(fields.get("facilityTypeId") != null) {
			returnVal.setFacilityTypeId((String) fields.get("facilityTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FacilityTypeAttr map(GenericValue val) {

FacilityTypeAttr returnVal = new FacilityTypeAttr();
		returnVal.setFacilityTypeId(val.getString("facilityTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FacilityTypeAttr map(HttpServletRequest request) throws Exception {

		FacilityTypeAttr returnVal = new FacilityTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityTypeId")) {
returnVal.setFacilityTypeId(request.getParameter("facilityTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

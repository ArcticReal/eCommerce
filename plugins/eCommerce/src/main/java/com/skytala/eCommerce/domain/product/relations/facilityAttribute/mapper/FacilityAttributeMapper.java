package com.skytala.eCommerce.domain.product.relations.facilityAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.model.FacilityAttribute;

public class FacilityAttributeMapper  {


	public static Map<String, Object> map(FacilityAttribute facilityattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilityattribute.getFacilityId() != null ){
			returnVal.put("facilityId",facilityattribute.getFacilityId());
}

		if(facilityattribute.getAttrName() != null ){
			returnVal.put("attrName",facilityattribute.getAttrName());
}

		if(facilityattribute.getAttrValue() != null ){
			returnVal.put("attrValue",facilityattribute.getAttrValue());
}

		if(facilityattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",facilityattribute.getAttrDescription());
}

		return returnVal;
}


	public static FacilityAttribute map(Map<String, Object> fields) {

		FacilityAttribute returnVal = new FacilityAttribute();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static FacilityAttribute mapstrstr(Map<String, String> fields) throws Exception {

		FacilityAttribute returnVal = new FacilityAttribute();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static FacilityAttribute map(GenericValue val) {

FacilityAttribute returnVal = new FacilityAttribute();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static FacilityAttribute map(HttpServletRequest request) throws Exception {

		FacilityAttribute returnVal = new FacilityAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}

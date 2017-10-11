package com.skytala.eCommerce.domain.product.relations.facilityGroupType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroupType.model.FacilityGroupType;

public class FacilityGroupTypeMapper  {


	public static Map<String, Object> map(FacilityGroupType facilitygrouptype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitygrouptype.getFacilityGroupTypeId() != null ){
			returnVal.put("facilityGroupTypeId",facilitygrouptype.getFacilityGroupTypeId());
}

		if(facilitygrouptype.getDescription() != null ){
			returnVal.put("description",facilitygrouptype.getDescription());
}

		return returnVal;
}


	public static FacilityGroupType map(Map<String, Object> fields) {

		FacilityGroupType returnVal = new FacilityGroupType();

		if(fields.get("facilityGroupTypeId") != null) {
			returnVal.setFacilityGroupTypeId((String) fields.get("facilityGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FacilityGroupType mapstrstr(Map<String, String> fields) throws Exception {

		FacilityGroupType returnVal = new FacilityGroupType();

		if(fields.get("facilityGroupTypeId") != null) {
			returnVal.setFacilityGroupTypeId((String) fields.get("facilityGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FacilityGroupType map(GenericValue val) {

FacilityGroupType returnVal = new FacilityGroupType();
		returnVal.setFacilityGroupTypeId(val.getString("facilityGroupTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FacilityGroupType map(HttpServletRequest request) throws Exception {

		FacilityGroupType returnVal = new FacilityGroupType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityGroupTypeId")) {
returnVal.setFacilityGroupTypeId(request.getParameter("facilityGroupTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

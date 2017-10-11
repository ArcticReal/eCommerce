package com.skytala.eCommerce.domain.product.relations.facilityGroupRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.model.FacilityGroupRole;

public class FacilityGroupRoleMapper  {


	public static Map<String, Object> map(FacilityGroupRole facilitygrouprole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitygrouprole.getFacilityGroupId() != null ){
			returnVal.put("facilityGroupId",facilitygrouprole.getFacilityGroupId());
}

		if(facilitygrouprole.getPartyId() != null ){
			returnVal.put("partyId",facilitygrouprole.getPartyId());
}

		if(facilitygrouprole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",facilitygrouprole.getRoleTypeId());
}

		return returnVal;
}


	public static FacilityGroupRole map(Map<String, Object> fields) {

		FacilityGroupRole returnVal = new FacilityGroupRole();

		if(fields.get("facilityGroupId") != null) {
			returnVal.setFacilityGroupId((String) fields.get("facilityGroupId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static FacilityGroupRole mapstrstr(Map<String, String> fields) throws Exception {

		FacilityGroupRole returnVal = new FacilityGroupRole();

		if(fields.get("facilityGroupId") != null) {
			returnVal.setFacilityGroupId((String) fields.get("facilityGroupId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static FacilityGroupRole map(GenericValue val) {

FacilityGroupRole returnVal = new FacilityGroupRole();
		returnVal.setFacilityGroupId(val.getString("facilityGroupId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static FacilityGroupRole map(HttpServletRequest request) throws Exception {

		FacilityGroupRole returnVal = new FacilityGroupRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityGroupId")) {
returnVal.setFacilityGroupId(request.getParameter("facilityGroupId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}

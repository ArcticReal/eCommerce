package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.role.SegmentGroupRole;

public class SegmentGroupRoleMapper  {


	public static Map<String, Object> map(SegmentGroupRole segmentgrouprole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(segmentgrouprole.getSegmentGroupId() != null ){
			returnVal.put("segmentGroupId",segmentgrouprole.getSegmentGroupId());
}

		if(segmentgrouprole.getPartyId() != null ){
			returnVal.put("partyId",segmentgrouprole.getPartyId());
}

		if(segmentgrouprole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",segmentgrouprole.getRoleTypeId());
}

		return returnVal;
}


	public static SegmentGroupRole map(Map<String, Object> fields) {

		SegmentGroupRole returnVal = new SegmentGroupRole();

		if(fields.get("segmentGroupId") != null) {
			returnVal.setSegmentGroupId((String) fields.get("segmentGroupId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static SegmentGroupRole mapstrstr(Map<String, String> fields) throws Exception {

		SegmentGroupRole returnVal = new SegmentGroupRole();

		if(fields.get("segmentGroupId") != null) {
			returnVal.setSegmentGroupId((String) fields.get("segmentGroupId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static SegmentGroupRole map(GenericValue val) {

SegmentGroupRole returnVal = new SegmentGroupRole();
		returnVal.setSegmentGroupId(val.getString("segmentGroupId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static SegmentGroupRole map(HttpServletRequest request) throws Exception {

		SegmentGroupRole returnVal = new SegmentGroupRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("segmentGroupId")) {
returnVal.setSegmentGroupId(request.getParameter("segmentGroupId"));
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

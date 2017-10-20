package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.role.ItemIssuanceRole;

public class ItemIssuanceRoleMapper  {


	public static Map<String, Object> map(ItemIssuanceRole itemissuancerole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(itemissuancerole.getItemIssuanceId() != null ){
			returnVal.put("itemIssuanceId",itemissuancerole.getItemIssuanceId());
}

		if(itemissuancerole.getPartyId() != null ){
			returnVal.put("partyId",itemissuancerole.getPartyId());
}

		if(itemissuancerole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",itemissuancerole.getRoleTypeId());
}

		return returnVal;
}


	public static ItemIssuanceRole map(Map<String, Object> fields) {

		ItemIssuanceRole returnVal = new ItemIssuanceRole();

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static ItemIssuanceRole mapstrstr(Map<String, String> fields) throws Exception {

		ItemIssuanceRole returnVal = new ItemIssuanceRole();

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static ItemIssuanceRole map(GenericValue val) {

ItemIssuanceRole returnVal = new ItemIssuanceRole();
		returnVal.setItemIssuanceId(val.getString("itemIssuanceId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static ItemIssuanceRole map(HttpServletRequest request) throws Exception {

		ItemIssuanceRole returnVal = new ItemIssuanceRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("itemIssuanceId")) {
returnVal.setItemIssuanceId(request.getParameter("itemIssuanceId"));
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

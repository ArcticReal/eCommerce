package com.skytala.eCommerce.domain.party.relations.contactMech.mapper.validRole;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.validRole.ValidContactMechRole;

public class ValidContactMechRoleMapper  {


	public static Map<String, Object> map(ValidContactMechRole validcontactmechrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(validcontactmechrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",validcontactmechrole.getRoleTypeId());
}

		if(validcontactmechrole.getContactMechTypeId() != null ){
			returnVal.put("contactMechTypeId",validcontactmechrole.getContactMechTypeId());
}

		return returnVal;
}


	public static ValidContactMechRole map(Map<String, Object> fields) {

		ValidContactMechRole returnVal = new ValidContactMechRole();

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}


		return returnVal;
 } 
	public static ValidContactMechRole mapstrstr(Map<String, String> fields) throws Exception {

		ValidContactMechRole returnVal = new ValidContactMechRole();

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}


		return returnVal;
 } 
	public static ValidContactMechRole map(GenericValue val) {

ValidContactMechRole returnVal = new ValidContactMechRole();
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setContactMechTypeId(val.getString("contactMechTypeId"));


return returnVal;

}

public static ValidContactMechRole map(HttpServletRequest request) throws Exception {

		ValidContactMechRole returnVal = new ValidContactMechRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("roleTypeId")) {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}

		if(paramMap.containsKey("contactMechTypeId"))  {
returnVal.setContactMechTypeId(request.getParameter("contactMechTypeId"));
}
return returnVal;

}
}

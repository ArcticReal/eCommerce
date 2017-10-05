package com.skytala.eCommerce.domain.contactListType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contactListType.model.ContactListType;

public class ContactListTypeMapper  {


	public static Map<String, Object> map(ContactListType contactlisttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactlisttype.getContactListTypeId() != null ){
			returnVal.put("contactListTypeId",contactlisttype.getContactListTypeId());
}

		if(contactlisttype.getDescription() != null ){
			returnVal.put("description",contactlisttype.getDescription());
}

		return returnVal;
}


	public static ContactListType map(Map<String, Object> fields) {

		ContactListType returnVal = new ContactListType();

		if(fields.get("contactListTypeId") != null) {
			returnVal.setContactListTypeId((String) fields.get("contactListTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContactListType mapstrstr(Map<String, String> fields) throws Exception {

		ContactListType returnVal = new ContactListType();

		if(fields.get("contactListTypeId") != null) {
			returnVal.setContactListTypeId((String) fields.get("contactListTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContactListType map(GenericValue val) {

ContactListType returnVal = new ContactListType();
		returnVal.setContactListTypeId(val.getString("contactListTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContactListType map(HttpServletRequest request) throws Exception {

		ContactListType returnVal = new ContactListType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactListTypeId")) {
returnVal.setContactListTypeId(request.getParameter("contactListTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

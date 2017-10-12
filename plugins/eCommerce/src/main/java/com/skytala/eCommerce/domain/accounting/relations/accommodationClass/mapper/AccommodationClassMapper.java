package com.skytala.eCommerce.domain.accounting.relations.accommodationClass.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.model.AccommodationClass;

public class AccommodationClassMapper  {


	public static Map<String, Object> map(AccommodationClass accommodationclass) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(accommodationclass.getAccommodationClassId() != null ){
			returnVal.put("accommodationClassId",accommodationclass.getAccommodationClassId());
}

		if(accommodationclass.getParentClassId() != null ){
			returnVal.put("parentClassId",accommodationclass.getParentClassId());
}

		if(accommodationclass.getDescription() != null ){
			returnVal.put("description",accommodationclass.getDescription());
}

		return returnVal;
}


	public static AccommodationClass map(Map<String, Object> fields) {

		AccommodationClass returnVal = new AccommodationClass();

		if(fields.get("accommodationClassId") != null) {
			returnVal.setAccommodationClassId((String) fields.get("accommodationClassId"));
}

		if(fields.get("parentClassId") != null) {
			returnVal.setParentClassId((String) fields.get("parentClassId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AccommodationClass mapstrstr(Map<String, String> fields) throws Exception {

		AccommodationClass returnVal = new AccommodationClass();

		if(fields.get("accommodationClassId") != null) {
			returnVal.setAccommodationClassId((String) fields.get("accommodationClassId"));
}

		if(fields.get("parentClassId") != null) {
			returnVal.setParentClassId((String) fields.get("parentClassId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AccommodationClass map(GenericValue val) {

AccommodationClass returnVal = new AccommodationClass();
		returnVal.setAccommodationClassId(val.getString("accommodationClassId"));
		returnVal.setParentClassId(val.getString("parentClassId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AccommodationClass map(HttpServletRequest request) throws Exception {

		AccommodationClass returnVal = new AccommodationClass();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("accommodationClassId")) {
returnVal.setAccommodationClassId(request.getParameter("accommodationClassId"));
}

		if(paramMap.containsKey("parentClassId"))  {
returnVal.setParentClassId(request.getParameter("parentClassId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

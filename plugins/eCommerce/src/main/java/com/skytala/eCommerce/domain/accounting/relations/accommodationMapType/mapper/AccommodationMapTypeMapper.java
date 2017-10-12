package com.skytala.eCommerce.domain.accounting.relations.accommodationMapType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMapType.model.AccommodationMapType;

public class AccommodationMapTypeMapper  {


	public static Map<String, Object> map(AccommodationMapType accommodationmaptype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(accommodationmaptype.getAccommodationMapTypeId() != null ){
			returnVal.put("accommodationMapTypeId",accommodationmaptype.getAccommodationMapTypeId());
}

		if(accommodationmaptype.getDescription() != null ){
			returnVal.put("description",accommodationmaptype.getDescription());
}

		return returnVal;
}


	public static AccommodationMapType map(Map<String, Object> fields) {

		AccommodationMapType returnVal = new AccommodationMapType();

		if(fields.get("accommodationMapTypeId") != null) {
			returnVal.setAccommodationMapTypeId((String) fields.get("accommodationMapTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AccommodationMapType mapstrstr(Map<String, String> fields) throws Exception {

		AccommodationMapType returnVal = new AccommodationMapType();

		if(fields.get("accommodationMapTypeId") != null) {
			returnVal.setAccommodationMapTypeId((String) fields.get("accommodationMapTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AccommodationMapType map(GenericValue val) {

AccommodationMapType returnVal = new AccommodationMapType();
		returnVal.setAccommodationMapTypeId(val.getString("accommodationMapTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AccommodationMapType map(HttpServletRequest request) throws Exception {

		AccommodationMapType returnVal = new AccommodationMapType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("accommodationMapTypeId")) {
returnVal.setAccommodationMapTypeId(request.getParameter("accommodationMapTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

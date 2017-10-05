package com.skytala.eCommerce.domain.rateType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.rateType.model.RateType;

public class RateTypeMapper  {


	public static Map<String, Object> map(RateType ratetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ratetype.getRateTypeId() != null ){
			returnVal.put("rateTypeId",ratetype.getRateTypeId());
}

		if(ratetype.getDescription() != null ){
			returnVal.put("description",ratetype.getDescription());
}

		return returnVal;
}


	public static RateType map(Map<String, Object> fields) {

		RateType returnVal = new RateType();

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RateType mapstrstr(Map<String, String> fields) throws Exception {

		RateType returnVal = new RateType();

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RateType map(GenericValue val) {

RateType returnVal = new RateType();
		returnVal.setRateTypeId(val.getString("rateTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static RateType map(HttpServletRequest request) throws Exception {

		RateType returnVal = new RateType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("rateTypeId")) {
returnVal.setRateTypeId(request.getParameter("rateTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

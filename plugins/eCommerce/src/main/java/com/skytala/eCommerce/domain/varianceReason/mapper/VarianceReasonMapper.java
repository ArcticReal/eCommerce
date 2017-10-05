package com.skytala.eCommerce.domain.varianceReason.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.varianceReason.model.VarianceReason;

public class VarianceReasonMapper  {


	public static Map<String, Object> map(VarianceReason variancereason) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(variancereason.getVarianceReasonId() != null ){
			returnVal.put("varianceReasonId",variancereason.getVarianceReasonId());
}

		if(variancereason.getDescription() != null ){
			returnVal.put("description",variancereason.getDescription());
}

		return returnVal;
}


	public static VarianceReason map(Map<String, Object> fields) {

		VarianceReason returnVal = new VarianceReason();

		if(fields.get("varianceReasonId") != null) {
			returnVal.setVarianceReasonId((String) fields.get("varianceReasonId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static VarianceReason mapstrstr(Map<String, String> fields) throws Exception {

		VarianceReason returnVal = new VarianceReason();

		if(fields.get("varianceReasonId") != null) {
			returnVal.setVarianceReasonId((String) fields.get("varianceReasonId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static VarianceReason map(GenericValue val) {

VarianceReason returnVal = new VarianceReason();
		returnVal.setVarianceReasonId(val.getString("varianceReasonId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static VarianceReason map(HttpServletRequest request) throws Exception {

		VarianceReason returnVal = new VarianceReason();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("varianceReasonId")) {
returnVal.setVarianceReasonId(request.getParameter("varianceReasonId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

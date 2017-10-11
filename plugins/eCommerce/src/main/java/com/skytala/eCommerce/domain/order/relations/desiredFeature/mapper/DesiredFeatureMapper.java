package com.skytala.eCommerce.domain.order.relations.desiredFeature.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.desiredFeature.model.DesiredFeature;

public class DesiredFeatureMapper  {


	public static Map<String, Object> map(DesiredFeature desiredfeature) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(desiredfeature.getDesiredFeatureId() != null ){
			returnVal.put("desiredFeatureId",desiredfeature.getDesiredFeatureId());
}

		if(desiredfeature.getRequirementId() != null ){
			returnVal.put("requirementId",desiredfeature.getRequirementId());
}

		if(desiredfeature.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",desiredfeature.getProductFeatureId());
}

		if(desiredfeature.getOptionalInd() != null ){
			returnVal.put("optionalInd",desiredfeature.getOptionalInd());
}

		return returnVal;
}


	public static DesiredFeature map(Map<String, Object> fields) {

		DesiredFeature returnVal = new DesiredFeature();

		if(fields.get("desiredFeatureId") != null) {
			returnVal.setDesiredFeatureId((String) fields.get("desiredFeatureId"));
}

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("optionalInd") != null) {
			returnVal.setOptionalInd((boolean) fields.get("optionalInd"));
}


		return returnVal;
 } 
	public static DesiredFeature mapstrstr(Map<String, String> fields) throws Exception {

		DesiredFeature returnVal = new DesiredFeature();

		if(fields.get("desiredFeatureId") != null) {
			returnVal.setDesiredFeatureId((String) fields.get("desiredFeatureId"));
}

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("optionalInd") != null) {
String buf;
buf = fields.get("optionalInd");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setOptionalInd(ibuf);
}


		return returnVal;
 } 
	public static DesiredFeature map(GenericValue val) {

DesiredFeature returnVal = new DesiredFeature();
		returnVal.setDesiredFeatureId(val.getString("desiredFeatureId"));
		returnVal.setRequirementId(val.getString("requirementId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setOptionalInd(val.getBoolean("optionalInd"));


return returnVal;

}

public static DesiredFeature map(HttpServletRequest request) throws Exception {

		DesiredFeature returnVal = new DesiredFeature();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("desiredFeatureId")) {
returnVal.setDesiredFeatureId(request.getParameter("desiredFeatureId"));
}

		if(paramMap.containsKey("requirementId"))  {
returnVal.setRequirementId(request.getParameter("requirementId"));
}
		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
		if(paramMap.containsKey("optionalInd"))  {
String buf = request.getParameter("optionalInd");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setOptionalInd(ibuf);
}
return returnVal;

}
}

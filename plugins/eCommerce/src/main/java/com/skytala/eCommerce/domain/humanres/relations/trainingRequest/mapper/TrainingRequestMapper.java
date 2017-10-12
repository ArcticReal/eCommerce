package com.skytala.eCommerce.domain.humanres.relations.trainingRequest.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.model.TrainingRequest;

public class TrainingRequestMapper  {


	public static Map<String, Object> map(TrainingRequest trainingrequest) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(trainingrequest.getTrainingRequestId() != null ){
			returnVal.put("trainingRequestId",trainingrequest.getTrainingRequestId());
}

		return returnVal;
}


	public static TrainingRequest map(Map<String, Object> fields) {

		TrainingRequest returnVal = new TrainingRequest();

		if(fields.get("trainingRequestId") != null) {
			returnVal.setTrainingRequestId((String) fields.get("trainingRequestId"));
}


		return returnVal;
 } 
	public static TrainingRequest mapstrstr(Map<String, String> fields) throws Exception {

		TrainingRequest returnVal = new TrainingRequest();

		if(fields.get("trainingRequestId") != null) {
			returnVal.setTrainingRequestId((String) fields.get("trainingRequestId"));
}


		return returnVal;
 } 
	public static TrainingRequest map(GenericValue val) {

TrainingRequest returnVal = new TrainingRequest();
		returnVal.setTrainingRequestId(val.getString("trainingRequestId"));


return returnVal;

}

public static TrainingRequest map(HttpServletRequest request) throws Exception {

		TrainingRequest returnVal = new TrainingRequest();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("trainingRequestId")) {
returnVal.setTrainingRequestId(request.getParameter("trainingRequestId"));
}

return returnVal;

}
}

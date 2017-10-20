package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.icalData;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.icalData.WorkEffortIcalData;

public class WorkEffortIcalDataMapper  {


	public static Map<String, Object> map(WorkEffortIcalData workefforticaldata) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workefforticaldata.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workefforticaldata.getWorkEffortId());
}

		if(workefforticaldata.getIcalData() != null ){
			returnVal.put("icalData",workefforticaldata.getIcalData());
}

		return returnVal;
}


	public static WorkEffortIcalData map(Map<String, Object> fields) {

		WorkEffortIcalData returnVal = new WorkEffortIcalData();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("icalData") != null) {
			returnVal.setIcalData((String) fields.get("icalData"));
}


		return returnVal;
 } 
	public static WorkEffortIcalData mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortIcalData returnVal = new WorkEffortIcalData();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("icalData") != null) {
			returnVal.setIcalData((String) fields.get("icalData"));
}


		return returnVal;
 } 
	public static WorkEffortIcalData map(GenericValue val) {

WorkEffortIcalData returnVal = new WorkEffortIcalData();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setIcalData(val.getString("icalData"));


return returnVal;

}

public static WorkEffortIcalData map(HttpServletRequest request) throws Exception {

		WorkEffortIcalData returnVal = new WorkEffortIcalData();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("icalData"))  {
returnVal.setIcalData(request.getParameter("icalData"));
}
return returnVal;

}
}

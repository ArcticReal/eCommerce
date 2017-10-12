package com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.model.WorkEffortKeyword;

public class WorkEffortKeywordMapper  {


	public static Map<String, Object> map(WorkEffortKeyword workeffortkeyword) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortkeyword.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortkeyword.getWorkEffortId());
}

		if(workeffortkeyword.getKeyword() != null ){
			returnVal.put("keyword",workeffortkeyword.getKeyword());
}

		if(workeffortkeyword.getRelevancyWeight() != null ){
			returnVal.put("relevancyWeight",workeffortkeyword.getRelevancyWeight());
}

		return returnVal;
}


	public static WorkEffortKeyword map(Map<String, Object> fields) {

		WorkEffortKeyword returnVal = new WorkEffortKeyword();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("keyword") != null) {
			returnVal.setKeyword((String) fields.get("keyword"));
}

		if(fields.get("relevancyWeight") != null) {
			returnVal.setRelevancyWeight((long) fields.get("relevancyWeight"));
}


		return returnVal;
 } 
	public static WorkEffortKeyword mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortKeyword returnVal = new WorkEffortKeyword();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("keyword") != null) {
			returnVal.setKeyword((String) fields.get("keyword"));
}

		if(fields.get("relevancyWeight") != null) {
String buf;
buf = fields.get("relevancyWeight");
long ibuf = Long.parseLong(buf);
			returnVal.setRelevancyWeight(ibuf);
}


		return returnVal;
 } 
	public static WorkEffortKeyword map(GenericValue val) {

WorkEffortKeyword returnVal = new WorkEffortKeyword();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setKeyword(val.getString("keyword"));
		returnVal.setRelevancyWeight(val.getLong("relevancyWeight"));


return returnVal;

}

public static WorkEffortKeyword map(HttpServletRequest request) throws Exception {

		WorkEffortKeyword returnVal = new WorkEffortKeyword();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("keyword"))  {
returnVal.setKeyword(request.getParameter("keyword"));
}
		if(paramMap.containsKey("relevancyWeight"))  {
String buf = request.getParameter("relevancyWeight");
Long ibuf = Long.parseLong(buf);
returnVal.setRelevancyWeight(ibuf);
}
return returnVal;

}
}

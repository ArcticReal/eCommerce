package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.model.WorkEffortSearchResult;

public class WorkEffortSearchResultMapper  {


	public static Map<String, Object> map(WorkEffortSearchResult workeffortsearchresult) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortsearchresult.getWorkEffortSearchResultId() != null ){
			returnVal.put("workEffortSearchResultId",workeffortsearchresult.getWorkEffortSearchResultId());
}

		if(workeffortsearchresult.getVisitId() != null ){
			returnVal.put("visitId",workeffortsearchresult.getVisitId());
}

		if(workeffortsearchresult.getOrderByName() != null ){
			returnVal.put("orderByName",workeffortsearchresult.getOrderByName());
}

		if(workeffortsearchresult.getIsAscending() != null ){
			returnVal.put("isAscending",workeffortsearchresult.getIsAscending());
}

		if(workeffortsearchresult.getNumResults() != null ){
			returnVal.put("numResults",workeffortsearchresult.getNumResults());
}

		if(workeffortsearchresult.getSecondsTotal() != null ){
			returnVal.put("secondsTotal",workeffortsearchresult.getSecondsTotal());
}

		if(workeffortsearchresult.getSearchDate() != null ){
			returnVal.put("searchDate",workeffortsearchresult.getSearchDate());
}

		return returnVal;
}


	public static WorkEffortSearchResult map(Map<String, Object> fields) {

		WorkEffortSearchResult returnVal = new WorkEffortSearchResult();

		if(fields.get("workEffortSearchResultId") != null) {
			returnVal.setWorkEffortSearchResultId((String) fields.get("workEffortSearchResultId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("orderByName") != null) {
			returnVal.setOrderByName((String) fields.get("orderByName"));
}

		if(fields.get("isAscending") != null) {
			returnVal.setIsAscending((boolean) fields.get("isAscending"));
}

		if(fields.get("numResults") != null) {
			returnVal.setNumResults((long) fields.get("numResults"));
}

		if(fields.get("secondsTotal") != null) {
			returnVal.setSecondsTotal((BigDecimal) fields.get("secondsTotal"));
}

		if(fields.get("searchDate") != null) {
			returnVal.setSearchDate((Timestamp) fields.get("searchDate"));
}


		return returnVal;
 } 
	public static WorkEffortSearchResult mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortSearchResult returnVal = new WorkEffortSearchResult();

		if(fields.get("workEffortSearchResultId") != null) {
			returnVal.setWorkEffortSearchResultId((String) fields.get("workEffortSearchResultId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("orderByName") != null) {
			returnVal.setOrderByName((String) fields.get("orderByName"));
}

		if(fields.get("isAscending") != null) {
String buf;
buf = fields.get("isAscending");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsAscending(ibuf);
}

		if(fields.get("numResults") != null) {
String buf;
buf = fields.get("numResults");
long ibuf = Long.parseLong(buf);
			returnVal.setNumResults(ibuf);
}

		if(fields.get("secondsTotal") != null) {
String buf;
buf = fields.get("secondsTotal");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSecondsTotal(bd);
}

		if(fields.get("searchDate") != null) {
String buf = fields.get("searchDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSearchDate(ibuf);
}


		return returnVal;
 } 
	public static WorkEffortSearchResult map(GenericValue val) {

WorkEffortSearchResult returnVal = new WorkEffortSearchResult();
		returnVal.setWorkEffortSearchResultId(val.getString("workEffortSearchResultId"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setOrderByName(val.getString("orderByName"));
		returnVal.setIsAscending(val.getBoolean("isAscending"));
		returnVal.setNumResults(val.getLong("numResults"));
		returnVal.setSecondsTotal(val.getBigDecimal("secondsTotal"));
		returnVal.setSearchDate(val.getTimestamp("searchDate"));


return returnVal;

}

public static WorkEffortSearchResult map(HttpServletRequest request) throws Exception {

		WorkEffortSearchResult returnVal = new WorkEffortSearchResult();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortSearchResultId")) {
returnVal.setWorkEffortSearchResultId(request.getParameter("workEffortSearchResultId"));
}

		if(paramMap.containsKey("visitId"))  {
returnVal.setVisitId(request.getParameter("visitId"));
}
		if(paramMap.containsKey("orderByName"))  {
returnVal.setOrderByName(request.getParameter("orderByName"));
}
		if(paramMap.containsKey("isAscending"))  {
String buf = request.getParameter("isAscending");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsAscending(ibuf);
}
		if(paramMap.containsKey("numResults"))  {
String buf = request.getParameter("numResults");
Long ibuf = Long.parseLong(buf);
returnVal.setNumResults(ibuf);
}
		if(paramMap.containsKey("secondsTotal"))  {
String buf = request.getParameter("secondsTotal");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSecondsTotal(bd);
}
		if(paramMap.containsKey("searchDate"))  {
String buf = request.getParameter("searchDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setSearchDate(ibuf);
}
return returnVal;

}
}

package com.skytala.eCommerce.domain.content.relations.content.mapper.searchResult;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.model.searchResult.ContentSearchResult;

public class ContentSearchResultMapper  {


	public static Map<String, Object> map(ContentSearchResult contentsearchresult) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentsearchresult.getContentSearchResultId() != null ){
			returnVal.put("contentSearchResultId",contentsearchresult.getContentSearchResultId());
}

		if(contentsearchresult.getVisitId() != null ){
			returnVal.put("visitId",contentsearchresult.getVisitId());
}

		if(contentsearchresult.getOrderByName() != null ){
			returnVal.put("orderByName",contentsearchresult.getOrderByName());
}

		if(contentsearchresult.getIsAscending() != null ){
			returnVal.put("isAscending",contentsearchresult.getIsAscending());
}

		if(contentsearchresult.getNumResults() != null ){
			returnVal.put("numResults",contentsearchresult.getNumResults());
}

		if(contentsearchresult.getSecondsTotal() != null ){
			returnVal.put("secondsTotal",contentsearchresult.getSecondsTotal());
}

		if(contentsearchresult.getSearchDate() != null ){
			returnVal.put("searchDate",contentsearchresult.getSearchDate());
}

		return returnVal;
}


	public static ContentSearchResult map(Map<String, Object> fields) {

		ContentSearchResult returnVal = new ContentSearchResult();

		if(fields.get("contentSearchResultId") != null) {
			returnVal.setContentSearchResultId((String) fields.get("contentSearchResultId"));
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
	public static ContentSearchResult mapstrstr(Map<String, String> fields) throws Exception {

		ContentSearchResult returnVal = new ContentSearchResult();

		if(fields.get("contentSearchResultId") != null) {
			returnVal.setContentSearchResultId((String) fields.get("contentSearchResultId"));
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
	public static ContentSearchResult map(GenericValue val) {

ContentSearchResult returnVal = new ContentSearchResult();
		returnVal.setContentSearchResultId(val.getString("contentSearchResultId"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setOrderByName(val.getString("orderByName"));
		returnVal.setIsAscending(val.getBoolean("isAscending"));
		returnVal.setNumResults(val.getLong("numResults"));
		returnVal.setSecondsTotal(val.getBigDecimal("secondsTotal"));
		returnVal.setSearchDate(val.getTimestamp("searchDate"));


return returnVal;

}

public static ContentSearchResult map(HttpServletRequest request) throws Exception {

		ContentSearchResult returnVal = new ContentSearchResult();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentSearchResultId")) {
returnVal.setContentSearchResultId(request.getParameter("contentSearchResultId"));
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

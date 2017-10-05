package com.skytala.eCommerce.domain.productSearchResult.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productSearchResult.model.ProductSearchResult;

public class ProductSearchResultMapper  {


	public static Map<String, Object> map(ProductSearchResult productsearchresult) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productsearchresult.getProductSearchResultId() != null ){
			returnVal.put("productSearchResultId",productsearchresult.getProductSearchResultId());
}

		if(productsearchresult.getVisitId() != null ){
			returnVal.put("visitId",productsearchresult.getVisitId());
}

		if(productsearchresult.getOrderByName() != null ){
			returnVal.put("orderByName",productsearchresult.getOrderByName());
}

		if(productsearchresult.getIsAscending() != null ){
			returnVal.put("isAscending",productsearchresult.getIsAscending());
}

		if(productsearchresult.getNumResults() != null ){
			returnVal.put("numResults",productsearchresult.getNumResults());
}

		if(productsearchresult.getSecondsTotal() != null ){
			returnVal.put("secondsTotal",productsearchresult.getSecondsTotal());
}

		if(productsearchresult.getSearchDate() != null ){
			returnVal.put("searchDate",productsearchresult.getSearchDate());
}

		return returnVal;
}


	public static ProductSearchResult map(Map<String, Object> fields) {

		ProductSearchResult returnVal = new ProductSearchResult();

		if(fields.get("productSearchResultId") != null) {
			returnVal.setProductSearchResultId((String) fields.get("productSearchResultId"));
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
	public static ProductSearchResult mapstrstr(Map<String, String> fields) throws Exception {

		ProductSearchResult returnVal = new ProductSearchResult();

		if(fields.get("productSearchResultId") != null) {
			returnVal.setProductSearchResultId((String) fields.get("productSearchResultId"));
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
	public static ProductSearchResult map(GenericValue val) {

ProductSearchResult returnVal = new ProductSearchResult();
		returnVal.setProductSearchResultId(val.getString("productSearchResultId"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setOrderByName(val.getString("orderByName"));
		returnVal.setIsAscending(val.getBoolean("isAscending"));
		returnVal.setNumResults(val.getLong("numResults"));
		returnVal.setSecondsTotal(val.getBigDecimal("secondsTotal"));
		returnVal.setSearchDate(val.getTimestamp("searchDate"));


return returnVal;

}

public static ProductSearchResult map(HttpServletRequest request) throws Exception {

		ProductSearchResult returnVal = new ProductSearchResult();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productSearchResultId")) {
returnVal.setProductSearchResultId(request.getParameter("productSearchResultId"));
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

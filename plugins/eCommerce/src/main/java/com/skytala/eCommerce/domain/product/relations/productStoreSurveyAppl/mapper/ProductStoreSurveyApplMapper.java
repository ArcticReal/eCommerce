package com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.model.ProductStoreSurveyAppl;

public class ProductStoreSurveyApplMapper  {


	public static Map<String, Object> map(ProductStoreSurveyAppl productstoresurveyappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstoresurveyappl.getProductStoreSurveyId() != null ){
			returnVal.put("productStoreSurveyId",productstoresurveyappl.getProductStoreSurveyId());
}

		if(productstoresurveyappl.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstoresurveyappl.getProductStoreId());
}

		if(productstoresurveyappl.getSurveyApplTypeId() != null ){
			returnVal.put("surveyApplTypeId",productstoresurveyappl.getSurveyApplTypeId());
}

		if(productstoresurveyappl.getGroupName() != null ){
			returnVal.put("groupName",productstoresurveyappl.getGroupName());
}

		if(productstoresurveyappl.getSurveyId() != null ){
			returnVal.put("surveyId",productstoresurveyappl.getSurveyId());
}

		if(productstoresurveyappl.getProductId() != null ){
			returnVal.put("productId",productstoresurveyappl.getProductId());
}

		if(productstoresurveyappl.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productstoresurveyappl.getProductCategoryId());
}

		if(productstoresurveyappl.getFromDate() != null ){
			returnVal.put("fromDate",productstoresurveyappl.getFromDate());
}

		if(productstoresurveyappl.getThruDate() != null ){
			returnVal.put("thruDate",productstoresurveyappl.getThruDate());
}

		if(productstoresurveyappl.getSurveyTemplate() != null ){
			returnVal.put("surveyTemplate",productstoresurveyappl.getSurveyTemplate());
}

		if(productstoresurveyappl.getResultTemplate() != null ){
			returnVal.put("resultTemplate",productstoresurveyappl.getResultTemplate());
}

		if(productstoresurveyappl.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productstoresurveyappl.getSequenceNum());
}

		return returnVal;
}


	public static ProductStoreSurveyAppl map(Map<String, Object> fields) {

		ProductStoreSurveyAppl returnVal = new ProductStoreSurveyAppl();

		if(fields.get("productStoreSurveyId") != null) {
			returnVal.setProductStoreSurveyId((String) fields.get("productStoreSurveyId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("surveyApplTypeId") != null) {
			returnVal.setSurveyApplTypeId((String) fields.get("surveyApplTypeId"));
}

		if(fields.get("groupName") != null) {
			returnVal.setGroupName((String) fields.get("groupName"));
}

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("surveyTemplate") != null) {
			returnVal.setSurveyTemplate((String) fields.get("surveyTemplate"));
}

		if(fields.get("resultTemplate") != null) {
			returnVal.setResultTemplate((String) fields.get("resultTemplate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ProductStoreSurveyAppl mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreSurveyAppl returnVal = new ProductStoreSurveyAppl();

		if(fields.get("productStoreSurveyId") != null) {
			returnVal.setProductStoreSurveyId((String) fields.get("productStoreSurveyId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("surveyApplTypeId") != null) {
			returnVal.setSurveyApplTypeId((String) fields.get("surveyApplTypeId"));
}

		if(fields.get("groupName") != null) {
			returnVal.setGroupName((String) fields.get("groupName"));
}

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("surveyTemplate") != null) {
			returnVal.setSurveyTemplate((String) fields.get("surveyTemplate"));
}

		if(fields.get("resultTemplate") != null) {
			returnVal.setResultTemplate((String) fields.get("resultTemplate"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ProductStoreSurveyAppl map(GenericValue val) {

ProductStoreSurveyAppl returnVal = new ProductStoreSurveyAppl();
		returnVal.setProductStoreSurveyId(val.getString("productStoreSurveyId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setSurveyApplTypeId(val.getString("surveyApplTypeId"));
		returnVal.setGroupName(val.getString("groupName"));
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSurveyTemplate(val.getString("surveyTemplate"));
		returnVal.setResultTemplate(val.getString("resultTemplate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductStoreSurveyAppl map(HttpServletRequest request) throws Exception {

		ProductStoreSurveyAppl returnVal = new ProductStoreSurveyAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreSurveyId")) {
returnVal.setProductStoreSurveyId(request.getParameter("productStoreSurveyId"));
}

		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("surveyApplTypeId"))  {
returnVal.setSurveyApplTypeId(request.getParameter("surveyApplTypeId"));
}
		if(paramMap.containsKey("groupName"))  {
returnVal.setGroupName(request.getParameter("groupName"));
}
		if(paramMap.containsKey("surveyId"))  {
returnVal.setSurveyId(request.getParameter("surveyId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productCategoryId"))  {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("surveyTemplate"))  {
returnVal.setSurveyTemplate(request.getParameter("surveyTemplate"));
}
		if(paramMap.containsKey("resultTemplate"))  {
returnVal.setResultTemplate(request.getParameter("resultTemplate"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}

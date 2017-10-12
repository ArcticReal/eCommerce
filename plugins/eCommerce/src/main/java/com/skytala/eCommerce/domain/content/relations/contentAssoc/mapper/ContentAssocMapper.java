package com.skytala.eCommerce.domain.content.relations.contentAssoc.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentAssoc.model.ContentAssoc;

public class ContentAssocMapper  {


	public static Map<String, Object> map(ContentAssoc contentassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentassoc.getContentId() != null ){
			returnVal.put("contentId",contentassoc.getContentId());
}

		if(contentassoc.getContentIdTo() != null ){
			returnVal.put("contentIdTo",contentassoc.getContentIdTo());
}

		if(contentassoc.getContentAssocTypeId() != null ){
			returnVal.put("contentAssocTypeId",contentassoc.getContentAssocTypeId());
}

		if(contentassoc.getFromDate() != null ){
			returnVal.put("fromDate",contentassoc.getFromDate());
}

		if(contentassoc.getThruDate() != null ){
			returnVal.put("thruDate",contentassoc.getThruDate());
}

		if(contentassoc.getContentAssocPredicateId() != null ){
			returnVal.put("contentAssocPredicateId",contentassoc.getContentAssocPredicateId());
}

		if(contentassoc.getDataSourceId() != null ){
			returnVal.put("dataSourceId",contentassoc.getDataSourceId());
}

		if(contentassoc.getSequenceNum() != null ){
			returnVal.put("sequenceNum",contentassoc.getSequenceNum());
}

		if(contentassoc.getMapKey() != null ){
			returnVal.put("mapKey",contentassoc.getMapKey());
}

		if(contentassoc.getUpperCoordinate() != null ){
			returnVal.put("upperCoordinate",contentassoc.getUpperCoordinate());
}

		if(contentassoc.getLeftCoordinate() != null ){
			returnVal.put("leftCoordinate",contentassoc.getLeftCoordinate());
}

		if(contentassoc.getCreatedDate() != null ){
			returnVal.put("createdDate",contentassoc.getCreatedDate());
}

		if(contentassoc.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",contentassoc.getCreatedByUserLogin());
}

		if(contentassoc.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",contentassoc.getLastModifiedDate());
}

		if(contentassoc.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",contentassoc.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static ContentAssoc map(Map<String, Object> fields) {

		ContentAssoc returnVal = new ContentAssoc();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentIdTo") != null) {
			returnVal.setContentIdTo((String) fields.get("contentIdTo"));
}

		if(fields.get("contentAssocTypeId") != null) {
			returnVal.setContentAssocTypeId((String) fields.get("contentAssocTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("contentAssocPredicateId") != null) {
			returnVal.setContentAssocPredicateId((String) fields.get("contentAssocPredicateId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("mapKey") != null) {
			returnVal.setMapKey((String) fields.get("mapKey"));
}

		if(fields.get("upperCoordinate") != null) {
			returnVal.setUpperCoordinate((long) fields.get("upperCoordinate"));
}

		if(fields.get("leftCoordinate") != null) {
			returnVal.setLeftCoordinate((long) fields.get("leftCoordinate"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ContentAssoc mapstrstr(Map<String, String> fields) throws Exception {

		ContentAssoc returnVal = new ContentAssoc();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentIdTo") != null) {
			returnVal.setContentIdTo((String) fields.get("contentIdTo"));
}

		if(fields.get("contentAssocTypeId") != null) {
			returnVal.setContentAssocTypeId((String) fields.get("contentAssocTypeId"));
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

		if(fields.get("contentAssocPredicateId") != null) {
			returnVal.setContentAssocPredicateId((String) fields.get("contentAssocPredicateId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("mapKey") != null) {
			returnVal.setMapKey((String) fields.get("mapKey"));
}

		if(fields.get("upperCoordinate") != null) {
String buf;
buf = fields.get("upperCoordinate");
long ibuf = Long.parseLong(buf);
			returnVal.setUpperCoordinate(ibuf);
}

		if(fields.get("leftCoordinate") != null) {
String buf;
buf = fields.get("leftCoordinate");
long ibuf = Long.parseLong(buf);
			returnVal.setLeftCoordinate(ibuf);
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ContentAssoc map(GenericValue val) {

ContentAssoc returnVal = new ContentAssoc();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setContentIdTo(val.getString("contentIdTo"));
		returnVal.setContentAssocTypeId(val.getString("contentAssocTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setContentAssocPredicateId(val.getString("contentAssocPredicateId"));
		returnVal.setDataSourceId(val.getString("dataSourceId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setMapKey(val.getString("mapKey"));
		returnVal.setUpperCoordinate(val.getLong("upperCoordinate"));
		returnVal.setLeftCoordinate(val.getLong("leftCoordinate"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static ContentAssoc map(HttpServletRequest request) throws Exception {

		ContentAssoc returnVal = new ContentAssoc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("contentIdTo"))  {
returnVal.setContentIdTo(request.getParameter("contentIdTo"));
}
		if(paramMap.containsKey("contentAssocTypeId"))  {
returnVal.setContentAssocTypeId(request.getParameter("contentAssocTypeId"));
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
		if(paramMap.containsKey("contentAssocPredicateId"))  {
returnVal.setContentAssocPredicateId(request.getParameter("contentAssocPredicateId"));
}
		if(paramMap.containsKey("dataSourceId"))  {
returnVal.setDataSourceId(request.getParameter("dataSourceId"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("mapKey"))  {
returnVal.setMapKey(request.getParameter("mapKey"));
}
		if(paramMap.containsKey("upperCoordinate"))  {
String buf = request.getParameter("upperCoordinate");
Long ibuf = Long.parseLong(buf);
returnVal.setUpperCoordinate(ibuf);
}
		if(paramMap.containsKey("leftCoordinate"))  {
String buf = request.getParameter("leftCoordinate");
Long ibuf = Long.parseLong(buf);
returnVal.setLeftCoordinate(ibuf);
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}

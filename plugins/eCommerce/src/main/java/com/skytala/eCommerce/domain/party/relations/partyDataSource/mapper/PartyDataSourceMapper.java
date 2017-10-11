package com.skytala.eCommerce.domain.party.relations.partyDataSource.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyDataSource.model.PartyDataSource;

public class PartyDataSourceMapper  {


	public static Map<String, Object> map(PartyDataSource partydatasource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partydatasource.getPartyId() != null ){
			returnVal.put("partyId",partydatasource.getPartyId());
}

		if(partydatasource.getDataSourceId() != null ){
			returnVal.put("dataSourceId",partydatasource.getDataSourceId());
}

		if(partydatasource.getFromDate() != null ){
			returnVal.put("fromDate",partydatasource.getFromDate());
}

		if(partydatasource.getVisitId() != null ){
			returnVal.put("visitId",partydatasource.getVisitId());
}

		if(partydatasource.getComments() != null ){
			returnVal.put("comments",partydatasource.getComments());
}

		if(partydatasource.getIsCreate() != null ){
			returnVal.put("isCreate",partydatasource.getIsCreate());
}

		return returnVal;
}


	public static PartyDataSource map(Map<String, Object> fields) {

		PartyDataSource returnVal = new PartyDataSource();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("isCreate") != null) {
			returnVal.setIsCreate((boolean) fields.get("isCreate"));
}


		return returnVal;
 } 
	public static PartyDataSource mapstrstr(Map<String, String> fields) throws Exception {

		PartyDataSource returnVal = new PartyDataSource();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("isCreate") != null) {
String buf;
buf = fields.get("isCreate");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsCreate(ibuf);
}


		return returnVal;
 } 
	public static PartyDataSource map(GenericValue val) {

PartyDataSource returnVal = new PartyDataSource();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setDataSourceId(val.getString("dataSourceId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setIsCreate(val.getBoolean("isCreate"));


return returnVal;

}

public static PartyDataSource map(HttpServletRequest request) throws Exception {

		PartyDataSource returnVal = new PartyDataSource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("dataSourceId"))  {
returnVal.setDataSourceId(request.getParameter("dataSourceId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("visitId"))  {
returnVal.setVisitId(request.getParameter("visitId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("isCreate"))  {
String buf = request.getParameter("isCreate");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsCreate(ibuf);
}
return returnVal;

}
}

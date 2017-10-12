package com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.model.FinAccountStatus;

public class FinAccountStatusMapper  {


	public static Map<String, Object> map(FinAccountStatus finaccountstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccountstatus.getFinAccountId() != null ){
			returnVal.put("finAccountId",finaccountstatus.getFinAccountId());
}

		if(finaccountstatus.getStatusId() != null ){
			returnVal.put("statusId",finaccountstatus.getStatusId());
}

		if(finaccountstatus.getStatusDate() != null ){
			returnVal.put("statusDate",finaccountstatus.getStatusDate());
}

		if(finaccountstatus.getStatusEndDate() != null ){
			returnVal.put("statusEndDate",finaccountstatus.getStatusEndDate());
}

		if(finaccountstatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",finaccountstatus.getChangeByUserLoginId());
}

		return returnVal;
}


	public static FinAccountStatus map(Map<String, Object> fields) {

		FinAccountStatus returnVal = new FinAccountStatus();

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDate") != null) {
			returnVal.setStatusDate((Timestamp) fields.get("statusDate"));
}

		if(fields.get("statusEndDate") != null) {
			returnVal.setStatusEndDate((Timestamp) fields.get("statusEndDate"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static FinAccountStatus mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountStatus returnVal = new FinAccountStatus();

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDate") != null) {
String buf = fields.get("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDate(ibuf);
}

		if(fields.get("statusEndDate") != null) {
String buf = fields.get("statusEndDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusEndDate(ibuf);
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static FinAccountStatus map(GenericValue val) {

FinAccountStatus returnVal = new FinAccountStatus();
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setStatusDate(val.getTimestamp("statusDate"));
		returnVal.setStatusEndDate(val.getTimestamp("statusEndDate"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));


return returnVal;

}

public static FinAccountStatus map(HttpServletRequest request) throws Exception {

		FinAccountStatus returnVal = new FinAccountStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountId")) {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("statusDate"))  {
String buf = request.getParameter("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDate(ibuf);
}
		if(paramMap.containsKey("statusEndDate"))  {
String buf = request.getParameter("statusEndDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusEndDate(ibuf);
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
return returnVal;

}
}

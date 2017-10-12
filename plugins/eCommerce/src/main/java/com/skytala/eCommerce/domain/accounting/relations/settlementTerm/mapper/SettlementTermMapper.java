package com.skytala.eCommerce.domain.accounting.relations.settlementTerm.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.model.SettlementTerm;

public class SettlementTermMapper  {


	public static Map<String, Object> map(SettlementTerm settlementterm) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(settlementterm.getSettlementTermId() != null ){
			returnVal.put("settlementTermId",settlementterm.getSettlementTermId());
}

		if(settlementterm.getTermName() != null ){
			returnVal.put("termName",settlementterm.getTermName());
}

		if(settlementterm.getTermValue() != null ){
			returnVal.put("termValue",settlementterm.getTermValue());
}

		if(settlementterm.getUomId() != null ){
			returnVal.put("uomId",settlementterm.getUomId());
}

		return returnVal;
}


	public static SettlementTerm map(Map<String, Object> fields) {

		SettlementTerm returnVal = new SettlementTerm();

		if(fields.get("settlementTermId") != null) {
			returnVal.setSettlementTermId((String) fields.get("settlementTermId"));
}

		if(fields.get("termName") != null) {
			returnVal.setTermName((String) fields.get("termName"));
}

		if(fields.get("termValue") != null) {
			returnVal.setTermValue((long) fields.get("termValue"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}


		return returnVal;
 } 
	public static SettlementTerm mapstrstr(Map<String, String> fields) throws Exception {

		SettlementTerm returnVal = new SettlementTerm();

		if(fields.get("settlementTermId") != null) {
			returnVal.setSettlementTermId((String) fields.get("settlementTermId"));
}

		if(fields.get("termName") != null) {
			returnVal.setTermName((String) fields.get("termName"));
}

		if(fields.get("termValue") != null) {
String buf;
buf = fields.get("termValue");
long ibuf = Long.parseLong(buf);
			returnVal.setTermValue(ibuf);
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}


		return returnVal;
 } 
	public static SettlementTerm map(GenericValue val) {

SettlementTerm returnVal = new SettlementTerm();
		returnVal.setSettlementTermId(val.getString("settlementTermId"));
		returnVal.setTermName(val.getString("termName"));
		returnVal.setTermValue(val.getLong("termValue"));
		returnVal.setUomId(val.getString("uomId"));


return returnVal;

}

public static SettlementTerm map(HttpServletRequest request) throws Exception {

		SettlementTerm returnVal = new SettlementTerm();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("settlementTermId")) {
returnVal.setSettlementTermId(request.getParameter("settlementTermId"));
}

		if(paramMap.containsKey("termName"))  {
returnVal.setTermName(request.getParameter("termName"));
}
		if(paramMap.containsKey("termValue"))  {
String buf = request.getParameter("termValue");
Long ibuf = Long.parseLong(buf);
returnVal.setTermValue(ibuf);
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
return returnVal;

}
}

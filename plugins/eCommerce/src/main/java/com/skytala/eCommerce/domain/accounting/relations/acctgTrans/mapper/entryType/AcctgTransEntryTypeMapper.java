package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entryType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entryType.AcctgTransEntryType;

public class AcctgTransEntryTypeMapper  {


	public static Map<String, Object> map(AcctgTransEntryType acctgtransentrytype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(acctgtransentrytype.getAcctgTransEntryTypeId() != null ){
			returnVal.put("acctgTransEntryTypeId",acctgtransentrytype.getAcctgTransEntryTypeId());
}

		if(acctgtransentrytype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",acctgtransentrytype.getParentTypeId());
}

		if(acctgtransentrytype.getHasTable() != null ){
			returnVal.put("hasTable",acctgtransentrytype.getHasTable());
}

		if(acctgtransentrytype.getDescription() != null ){
			returnVal.put("description",acctgtransentrytype.getDescription());
}

		return returnVal;
}


	public static AcctgTransEntryType map(Map<String, Object> fields) {

		AcctgTransEntryType returnVal = new AcctgTransEntryType();

		if(fields.get("acctgTransEntryTypeId") != null) {
			returnVal.setAcctgTransEntryTypeId((String) fields.get("acctgTransEntryTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AcctgTransEntryType mapstrstr(Map<String, String> fields) throws Exception {

		AcctgTransEntryType returnVal = new AcctgTransEntryType();

		if(fields.get("acctgTransEntryTypeId") != null) {
			returnVal.setAcctgTransEntryTypeId((String) fields.get("acctgTransEntryTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AcctgTransEntryType map(GenericValue val) {

AcctgTransEntryType returnVal = new AcctgTransEntryType();
		returnVal.setAcctgTransEntryTypeId(val.getString("acctgTransEntryTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AcctgTransEntryType map(HttpServletRequest request) throws Exception {

		AcctgTransEntryType returnVal = new AcctgTransEntryType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("acctgTransEntryTypeId")) {
returnVal.setAcctgTransEntryTypeId(request.getParameter("acctgTransEntryTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}

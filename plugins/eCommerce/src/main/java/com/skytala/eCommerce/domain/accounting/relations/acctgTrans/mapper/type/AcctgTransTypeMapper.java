package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.type.AcctgTransType;

public class AcctgTransTypeMapper  {


	public static Map<String, Object> map(AcctgTransType acctgtranstype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(acctgtranstype.getAcctgTransTypeId() != null ){
			returnVal.put("acctgTransTypeId",acctgtranstype.getAcctgTransTypeId());
}

		if(acctgtranstype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",acctgtranstype.getParentTypeId());
}

		if(acctgtranstype.getHasTable() != null ){
			returnVal.put("hasTable",acctgtranstype.getHasTable());
}

		if(acctgtranstype.getDescription() != null ){
			returnVal.put("description",acctgtranstype.getDescription());
}

		return returnVal;
}


	public static AcctgTransType map(Map<String, Object> fields) {

		AcctgTransType returnVal = new AcctgTransType();

		if(fields.get("acctgTransTypeId") != null) {
			returnVal.setAcctgTransTypeId((String) fields.get("acctgTransTypeId"));
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
	public static AcctgTransType mapstrstr(Map<String, String> fields) throws Exception {

		AcctgTransType returnVal = new AcctgTransType();

		if(fields.get("acctgTransTypeId") != null) {
			returnVal.setAcctgTransTypeId((String) fields.get("acctgTransTypeId"));
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
	public static AcctgTransType map(GenericValue val) {

AcctgTransType returnVal = new AcctgTransType();
		returnVal.setAcctgTransTypeId(val.getString("acctgTransTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AcctgTransType map(HttpServletRequest request) throws Exception {

		AcctgTransType returnVal = new AcctgTransType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("acctgTransTypeId")) {
returnVal.setAcctgTransTypeId(request.getParameter("acctgTransTypeId"));
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

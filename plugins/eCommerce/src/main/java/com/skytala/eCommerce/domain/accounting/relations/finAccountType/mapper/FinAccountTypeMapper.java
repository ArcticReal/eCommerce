package com.skytala.eCommerce.domain.accounting.relations.finAccountType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountType.model.FinAccountType;

public class FinAccountTypeMapper  {


	public static Map<String, Object> map(FinAccountType finaccounttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccounttype.getFinAccountTypeId() != null ){
			returnVal.put("finAccountTypeId",finaccounttype.getFinAccountTypeId());
}

		if(finaccounttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",finaccounttype.getParentTypeId());
}

		if(finaccounttype.getReplenishEnumId() != null ){
			returnVal.put("replenishEnumId",finaccounttype.getReplenishEnumId());
}

		if(finaccounttype.getIsRefundable() != null ){
			returnVal.put("isRefundable",finaccounttype.getIsRefundable());
}

		if(finaccounttype.getHasTable() != null ){
			returnVal.put("hasTable",finaccounttype.getHasTable());
}

		if(finaccounttype.getDescription() != null ){
			returnVal.put("description",finaccounttype.getDescription());
}

		return returnVal;
}


	public static FinAccountType map(Map<String, Object> fields) {

		FinAccountType returnVal = new FinAccountType();

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("replenishEnumId") != null) {
			returnVal.setReplenishEnumId((String) fields.get("replenishEnumId"));
}

		if(fields.get("isRefundable") != null) {
			returnVal.setIsRefundable((boolean) fields.get("isRefundable"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FinAccountType mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountType returnVal = new FinAccountType();

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("replenishEnumId") != null) {
			returnVal.setReplenishEnumId((String) fields.get("replenishEnumId"));
}

		if(fields.get("isRefundable") != null) {
String buf;
buf = fields.get("isRefundable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsRefundable(ibuf);
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
	public static FinAccountType map(GenericValue val) {

FinAccountType returnVal = new FinAccountType();
		returnVal.setFinAccountTypeId(val.getString("finAccountTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setReplenishEnumId(val.getString("replenishEnumId"));
		returnVal.setIsRefundable(val.getBoolean("isRefundable"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FinAccountType map(HttpServletRequest request) throws Exception {

		FinAccountType returnVal = new FinAccountType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountTypeId")) {
returnVal.setFinAccountTypeId(request.getParameter("finAccountTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("replenishEnumId"))  {
returnVal.setReplenishEnumId(request.getParameter("replenishEnumId"));
}
		if(paramMap.containsKey("isRefundable"))  {
String buf = request.getParameter("isRefundable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsRefundable(ibuf);
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

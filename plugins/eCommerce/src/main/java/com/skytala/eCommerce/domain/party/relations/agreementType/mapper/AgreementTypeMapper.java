package com.skytala.eCommerce.domain.party.relations.agreementType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementType.model.AgreementType;

public class AgreementTypeMapper  {


	public static Map<String, Object> map(AgreementType agreementtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementtype.getAgreementTypeId() != null ){
			returnVal.put("agreementTypeId",agreementtype.getAgreementTypeId());
}

		if(agreementtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",agreementtype.getParentTypeId());
}

		if(agreementtype.getHasTable() != null ){
			returnVal.put("hasTable",agreementtype.getHasTable());
}

		if(agreementtype.getDescription() != null ){
			returnVal.put("description",agreementtype.getDescription());
}

		return returnVal;
}


	public static AgreementType map(Map<String, Object> fields) {

		AgreementType returnVal = new AgreementType();

		if(fields.get("agreementTypeId") != null) {
			returnVal.setAgreementTypeId((String) fields.get("agreementTypeId"));
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
	public static AgreementType mapstrstr(Map<String, String> fields) throws Exception {

		AgreementType returnVal = new AgreementType();

		if(fields.get("agreementTypeId") != null) {
			returnVal.setAgreementTypeId((String) fields.get("agreementTypeId"));
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
	public static AgreementType map(GenericValue val) {

AgreementType returnVal = new AgreementType();
		returnVal.setAgreementTypeId(val.getString("agreementTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AgreementType map(HttpServletRequest request) throws Exception {

		AgreementType returnVal = new AgreementType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementTypeId")) {
returnVal.setAgreementTypeId(request.getParameter("agreementTypeId"));
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

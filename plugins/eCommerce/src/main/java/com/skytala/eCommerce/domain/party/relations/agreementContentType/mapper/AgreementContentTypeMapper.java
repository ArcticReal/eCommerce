package com.skytala.eCommerce.domain.party.relations.agreementContentType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.model.AgreementContentType;

public class AgreementContentTypeMapper  {


	public static Map<String, Object> map(AgreementContentType agreementcontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementcontenttype.getAgreementContentTypeId() != null ){
			returnVal.put("agreementContentTypeId",agreementcontenttype.getAgreementContentTypeId());
}

		if(agreementcontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",agreementcontenttype.getParentTypeId());
}

		if(agreementcontenttype.getHasTable() != null ){
			returnVal.put("hasTable",agreementcontenttype.getHasTable());
}

		if(agreementcontenttype.getDescription() != null ){
			returnVal.put("description",agreementcontenttype.getDescription());
}

		return returnVal;
}


	public static AgreementContentType map(Map<String, Object> fields) {

		AgreementContentType returnVal = new AgreementContentType();

		if(fields.get("agreementContentTypeId") != null) {
			returnVal.setAgreementContentTypeId((String) fields.get("agreementContentTypeId"));
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
	public static AgreementContentType mapstrstr(Map<String, String> fields) throws Exception {

		AgreementContentType returnVal = new AgreementContentType();

		if(fields.get("agreementContentTypeId") != null) {
			returnVal.setAgreementContentTypeId((String) fields.get("agreementContentTypeId"));
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
	public static AgreementContentType map(GenericValue val) {

AgreementContentType returnVal = new AgreementContentType();
		returnVal.setAgreementContentTypeId(val.getString("agreementContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AgreementContentType map(HttpServletRequest request) throws Exception {

		AgreementContentType returnVal = new AgreementContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementContentTypeId")) {
returnVal.setAgreementContentTypeId(request.getParameter("agreementContentTypeId"));
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

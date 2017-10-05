package com.skytala.eCommerce.domain.agreementItemType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.agreementItemType.model.AgreementItemType;

public class AgreementItemTypeMapper  {


	public static Map<String, Object> map(AgreementItemType agreementitemtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementitemtype.getAgreementItemTypeId() != null ){
			returnVal.put("agreementItemTypeId",agreementitemtype.getAgreementItemTypeId());
}

		if(agreementitemtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",agreementitemtype.getParentTypeId());
}

		if(agreementitemtype.getHasTable() != null ){
			returnVal.put("hasTable",agreementitemtype.getHasTable());
}

		if(agreementitemtype.getDescription() != null ){
			returnVal.put("description",agreementitemtype.getDescription());
}

		return returnVal;
}


	public static AgreementItemType map(Map<String, Object> fields) {

		AgreementItemType returnVal = new AgreementItemType();

		if(fields.get("agreementItemTypeId") != null) {
			returnVal.setAgreementItemTypeId((String) fields.get("agreementItemTypeId"));
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
	public static AgreementItemType mapstrstr(Map<String, String> fields) throws Exception {

		AgreementItemType returnVal = new AgreementItemType();

		if(fields.get("agreementItemTypeId") != null) {
			returnVal.setAgreementItemTypeId((String) fields.get("agreementItemTypeId"));
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
	public static AgreementItemType map(GenericValue val) {

AgreementItemType returnVal = new AgreementItemType();
		returnVal.setAgreementItemTypeId(val.getString("agreementItemTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AgreementItemType map(HttpServletRequest request) throws Exception {

		AgreementItemType returnVal = new AgreementItemType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementItemTypeId")) {
returnVal.setAgreementItemTypeId(request.getParameter("agreementItemTypeId"));
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

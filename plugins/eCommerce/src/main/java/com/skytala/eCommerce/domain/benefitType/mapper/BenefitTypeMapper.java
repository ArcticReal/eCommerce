package com.skytala.eCommerce.domain.benefitType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.benefitType.model.BenefitType;

public class BenefitTypeMapper  {


	public static Map<String, Object> map(BenefitType benefittype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(benefittype.getBenefitTypeId() != null ){
			returnVal.put("benefitTypeId",benefittype.getBenefitTypeId());
}

		if(benefittype.getBenefitName() != null ){
			returnVal.put("benefitName",benefittype.getBenefitName());
}

		if(benefittype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",benefittype.getParentTypeId());
}

		if(benefittype.getHasTable() != null ){
			returnVal.put("hasTable",benefittype.getHasTable());
}

		if(benefittype.getDescription() != null ){
			returnVal.put("description",benefittype.getDescription());
}

		if(benefittype.getEmployerPaidPercentage() != null ){
			returnVal.put("employerPaidPercentage",benefittype.getEmployerPaidPercentage());
}

		return returnVal;
}


	public static BenefitType map(Map<String, Object> fields) {

		BenefitType returnVal = new BenefitType();

		if(fields.get("benefitTypeId") != null) {
			returnVal.setBenefitTypeId((String) fields.get("benefitTypeId"));
}

		if(fields.get("benefitName") != null) {
			returnVal.setBenefitName((String) fields.get("benefitName"));
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

		if(fields.get("employerPaidPercentage") != null) {
			returnVal.setEmployerPaidPercentage((BigDecimal) fields.get("employerPaidPercentage"));
}


		return returnVal;
 } 
	public static BenefitType mapstrstr(Map<String, String> fields) throws Exception {

		BenefitType returnVal = new BenefitType();

		if(fields.get("benefitTypeId") != null) {
			returnVal.setBenefitTypeId((String) fields.get("benefitTypeId"));
}

		if(fields.get("benefitName") != null) {
			returnVal.setBenefitName((String) fields.get("benefitName"));
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

		if(fields.get("employerPaidPercentage") != null) {
String buf;
buf = fields.get("employerPaidPercentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEmployerPaidPercentage(bd);
}


		return returnVal;
 } 
	public static BenefitType map(GenericValue val) {

BenefitType returnVal = new BenefitType();
		returnVal.setBenefitTypeId(val.getString("benefitTypeId"));
		returnVal.setBenefitName(val.getString("benefitName"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setEmployerPaidPercentage(val.getBigDecimal("employerPaidPercentage"));


return returnVal;

}

public static BenefitType map(HttpServletRequest request) throws Exception {

		BenefitType returnVal = new BenefitType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("benefitTypeId")) {
returnVal.setBenefitTypeId(request.getParameter("benefitTypeId"));
}

		if(paramMap.containsKey("benefitName"))  {
returnVal.setBenefitName(request.getParameter("benefitName"));
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
		if(paramMap.containsKey("employerPaidPercentage"))  {
String buf = request.getParameter("employerPaidPercentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEmployerPaidPercentage(bd);
}
return returnVal;

}
}

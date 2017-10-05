package com.skytala.eCommerce.domain.skillType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.skillType.model.SkillType;

public class SkillTypeMapper  {


	public static Map<String, Object> map(SkillType skilltype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(skilltype.getSkillTypeId() != null ){
			returnVal.put("skillTypeId",skilltype.getSkillTypeId());
}

		if(skilltype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",skilltype.getParentTypeId());
}

		if(skilltype.getHasTable() != null ){
			returnVal.put("hasTable",skilltype.getHasTable());
}

		if(skilltype.getDescription() != null ){
			returnVal.put("description",skilltype.getDescription());
}

		return returnVal;
}


	public static SkillType map(Map<String, Object> fields) {

		SkillType returnVal = new SkillType();

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
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
	public static SkillType mapstrstr(Map<String, String> fields) throws Exception {

		SkillType returnVal = new SkillType();

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
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
	public static SkillType map(GenericValue val) {

SkillType returnVal = new SkillType();
		returnVal.setSkillTypeId(val.getString("skillTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SkillType map(HttpServletRequest request) throws Exception {

		SkillType returnVal = new SkillType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("skillTypeId")) {
returnVal.setSkillTypeId(request.getParameter("skillTypeId"));
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

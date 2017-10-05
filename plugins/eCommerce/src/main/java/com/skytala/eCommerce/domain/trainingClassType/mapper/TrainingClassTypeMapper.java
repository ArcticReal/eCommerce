package com.skytala.eCommerce.domain.trainingClassType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.trainingClassType.model.TrainingClassType;

public class TrainingClassTypeMapper  {


	public static Map<String, Object> map(TrainingClassType trainingclasstype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(trainingclasstype.getTrainingClassTypeId() != null ){
			returnVal.put("trainingClassTypeId",trainingclasstype.getTrainingClassTypeId());
}

		if(trainingclasstype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",trainingclasstype.getParentTypeId());
}

		if(trainingclasstype.getHasTable() != null ){
			returnVal.put("hasTable",trainingclasstype.getHasTable());
}

		if(trainingclasstype.getDescription() != null ){
			returnVal.put("description",trainingclasstype.getDescription());
}

		return returnVal;
}


	public static TrainingClassType map(Map<String, Object> fields) {

		TrainingClassType returnVal = new TrainingClassType();

		if(fields.get("trainingClassTypeId") != null) {
			returnVal.setTrainingClassTypeId((String) fields.get("trainingClassTypeId"));
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
	public static TrainingClassType mapstrstr(Map<String, String> fields) throws Exception {

		TrainingClassType returnVal = new TrainingClassType();

		if(fields.get("trainingClassTypeId") != null) {
			returnVal.setTrainingClassTypeId((String) fields.get("trainingClassTypeId"));
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
	public static TrainingClassType map(GenericValue val) {

TrainingClassType returnVal = new TrainingClassType();
		returnVal.setTrainingClassTypeId(val.getString("trainingClassTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TrainingClassType map(HttpServletRequest request) throws Exception {

		TrainingClassType returnVal = new TrainingClassType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("trainingClassTypeId")) {
returnVal.setTrainingClassTypeId(request.getParameter("trainingClassTypeId"));
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

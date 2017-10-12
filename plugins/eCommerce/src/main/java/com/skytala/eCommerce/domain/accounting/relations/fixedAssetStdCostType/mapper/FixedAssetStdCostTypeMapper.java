package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.model.FixedAssetStdCostType;

public class FixedAssetStdCostTypeMapper  {


	public static Map<String, Object> map(FixedAssetStdCostType fixedassetstdcosttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetstdcosttype.getFixedAssetStdCostTypeId() != null ){
			returnVal.put("fixedAssetStdCostTypeId",fixedassetstdcosttype.getFixedAssetStdCostTypeId());
}

		if(fixedassetstdcosttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",fixedassetstdcosttype.getParentTypeId());
}

		if(fixedassetstdcosttype.getHasTable() != null ){
			returnVal.put("hasTable",fixedassetstdcosttype.getHasTable());
}

		if(fixedassetstdcosttype.getDescription() != null ){
			returnVal.put("description",fixedassetstdcosttype.getDescription());
}

		return returnVal;
}


	public static FixedAssetStdCostType map(Map<String, Object> fields) {

		FixedAssetStdCostType returnVal = new FixedAssetStdCostType();

		if(fields.get("fixedAssetStdCostTypeId") != null) {
			returnVal.setFixedAssetStdCostTypeId((String) fields.get("fixedAssetStdCostTypeId"));
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
	public static FixedAssetStdCostType mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetStdCostType returnVal = new FixedAssetStdCostType();

		if(fields.get("fixedAssetStdCostTypeId") != null) {
			returnVal.setFixedAssetStdCostTypeId((String) fields.get("fixedAssetStdCostTypeId"));
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
	public static FixedAssetStdCostType map(GenericValue val) {

FixedAssetStdCostType returnVal = new FixedAssetStdCostType();
		returnVal.setFixedAssetStdCostTypeId(val.getString("fixedAssetStdCostTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FixedAssetStdCostType map(HttpServletRequest request) throws Exception {

		FixedAssetStdCostType returnVal = new FixedAssetStdCostType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetStdCostTypeId")) {
returnVal.setFixedAssetStdCostTypeId(request.getParameter("fixedAssetStdCostTypeId"));
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

package com.skytala.eCommerce.domain.content.relations.dataResource.mapper.metaData;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.metaData.DataResourceMetaData;

public class DataResourceMetaDataMapper  {


	public static Map<String, Object> map(DataResourceMetaData dataresourcemetadata) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(dataresourcemetadata.getDataResourceId() != null ){
			returnVal.put("dataResourceId",dataresourcemetadata.getDataResourceId());
}

		if(dataresourcemetadata.getMetaDataPredicateId() != null ){
			returnVal.put("metaDataPredicateId",dataresourcemetadata.getMetaDataPredicateId());
}

		if(dataresourcemetadata.getMetaDataValue() != null ){
			returnVal.put("metaDataValue",dataresourcemetadata.getMetaDataValue());
}

		if(dataresourcemetadata.getDataSourceId() != null ){
			returnVal.put("dataSourceId",dataresourcemetadata.getDataSourceId());
}

		return returnVal;
}


	public static DataResourceMetaData map(Map<String, Object> fields) {

		DataResourceMetaData returnVal = new DataResourceMetaData();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("metaDataPredicateId") != null) {
			returnVal.setMetaDataPredicateId((String) fields.get("metaDataPredicateId"));
}

		if(fields.get("metaDataValue") != null) {
			returnVal.setMetaDataValue((long) fields.get("metaDataValue"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}


		return returnVal;
 } 
	public static DataResourceMetaData mapstrstr(Map<String, String> fields) throws Exception {

		DataResourceMetaData returnVal = new DataResourceMetaData();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("metaDataPredicateId") != null) {
			returnVal.setMetaDataPredicateId((String) fields.get("metaDataPredicateId"));
}

		if(fields.get("metaDataValue") != null) {
String buf;
buf = fields.get("metaDataValue");
long ibuf = Long.parseLong(buf);
			returnVal.setMetaDataValue(ibuf);
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}


		return returnVal;
 } 
	public static DataResourceMetaData map(GenericValue val) {

DataResourceMetaData returnVal = new DataResourceMetaData();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setMetaDataPredicateId(val.getString("metaDataPredicateId"));
		returnVal.setMetaDataValue(val.getLong("metaDataValue"));
		returnVal.setDataSourceId(val.getString("dataSourceId"));


return returnVal;

}

public static DataResourceMetaData map(HttpServletRequest request) throws Exception {

		DataResourceMetaData returnVal = new DataResourceMetaData();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("metaDataPredicateId"))  {
returnVal.setMetaDataPredicateId(request.getParameter("metaDataPredicateId"));
}
		if(paramMap.containsKey("metaDataValue"))  {
String buf = request.getParameter("metaDataValue");
Long ibuf = Long.parseLong(buf);
returnVal.setMetaDataValue(ibuf);
}
		if(paramMap.containsKey("dataSourceId"))  {
returnVal.setDataSourceId(request.getParameter("dataSourceId"));
}
return returnVal;

}
}

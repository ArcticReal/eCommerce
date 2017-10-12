package com.skytala.eCommerce.domain.content.relations.contentMetaData.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.model.ContentMetaData;

public class ContentMetaDataMapper  {


	public static Map<String, Object> map(ContentMetaData contentmetadata) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentmetadata.getContentId() != null ){
			returnVal.put("contentId",contentmetadata.getContentId());
}

		if(contentmetadata.getMetaDataPredicateId() != null ){
			returnVal.put("metaDataPredicateId",contentmetadata.getMetaDataPredicateId());
}

		if(contentmetadata.getMetaDataValue() != null ){
			returnVal.put("metaDataValue",contentmetadata.getMetaDataValue());
}

		if(contentmetadata.getDataSourceId() != null ){
			returnVal.put("dataSourceId",contentmetadata.getDataSourceId());
}

		return returnVal;
}


	public static ContentMetaData map(Map<String, Object> fields) {

		ContentMetaData returnVal = new ContentMetaData();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
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
	public static ContentMetaData mapstrstr(Map<String, String> fields) throws Exception {

		ContentMetaData returnVal = new ContentMetaData();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
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
	public static ContentMetaData map(GenericValue val) {

ContentMetaData returnVal = new ContentMetaData();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setMetaDataPredicateId(val.getString("metaDataPredicateId"));
		returnVal.setMetaDataValue(val.getLong("metaDataValue"));
		returnVal.setDataSourceId(val.getString("dataSourceId"));


return returnVal;

}

public static ContentMetaData map(HttpServletRequest request) throws Exception {

		ContentMetaData returnVal = new ContentMetaData();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
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

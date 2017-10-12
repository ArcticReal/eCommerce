package com.skytala.eCommerce.domain.content.relations.videoDataResource.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.model.VideoDataResource;

public class VideoDataResourceMapper  {


	public static Map<String, Object> map(VideoDataResource videodataresource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(videodataresource.getDataResourceId() != null ){
			returnVal.put("dataResourceId",videodataresource.getDataResourceId());
}

		if(videodataresource.getVideoData() != null ){
			returnVal.put("videoData",videodataresource.getVideoData());
}

		return returnVal;
}


	public static VideoDataResource map(Map<String, Object> fields) {

		VideoDataResource returnVal = new VideoDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("videoData") != null) {
			returnVal.setVideoData((byte[]) fields.get("videoData"));
}


		return returnVal;
 } 
	public static VideoDataResource mapstrstr(Map<String, String> fields) throws Exception {

		VideoDataResource returnVal = new VideoDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("videoData") != null) {
String buf = fields.get("videoData");
byte[] ibuf = buf.getBytes();
			returnVal.setVideoData(ibuf);
}


		return returnVal;
 } 
	public static VideoDataResource map(GenericValue val) {

VideoDataResource returnVal = new VideoDataResource();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setVideoData(val.getBytes("videoData"));


return returnVal;

}

public static VideoDataResource map(HttpServletRequest request) throws Exception {

		VideoDataResource returnVal = new VideoDataResource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("videoData"))  {
String buf = request.getParameter("videoData");
byte[] ibuf = buf.getBytes();
returnVal.setVideoData(ibuf);
}
return returnVal;

}
}

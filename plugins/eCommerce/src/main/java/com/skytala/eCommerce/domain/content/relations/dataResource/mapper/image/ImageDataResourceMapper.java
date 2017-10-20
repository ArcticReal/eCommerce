package com.skytala.eCommerce.domain.content.relations.dataResource.mapper.image;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.image.ImageDataResource;

public class ImageDataResourceMapper  {


	public static Map<String, Object> map(ImageDataResource imagedataresource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(imagedataresource.getDataResourceId() != null ){
			returnVal.put("dataResourceId",imagedataresource.getDataResourceId());
}

		if(imagedataresource.getImageData() != null ){
			returnVal.put("imageData",imagedataresource.getImageData());
}

		return returnVal;
}


	public static ImageDataResource map(Map<String, Object> fields) {

		ImageDataResource returnVal = new ImageDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("imageData") != null) {
			returnVal.setImageData((byte[]) fields.get("imageData"));
}


		return returnVal;
 } 
	public static ImageDataResource mapstrstr(Map<String, String> fields) throws Exception {

		ImageDataResource returnVal = new ImageDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("imageData") != null) {
String buf = fields.get("imageData");
byte[] ibuf = buf.getBytes();
			returnVal.setImageData(ibuf);
}


		return returnVal;
 } 
	public static ImageDataResource map(GenericValue val) {

ImageDataResource returnVal = new ImageDataResource();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setImageData(val.getBytes("imageData"));


return returnVal;

}

public static ImageDataResource map(HttpServletRequest request) throws Exception {

		ImageDataResource returnVal = new ImageDataResource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("imageData"))  {
String buf = request.getParameter("imageData");
byte[] ibuf = buf.getBytes();
returnVal.setImageData(ibuf);
}
return returnVal;

}
}

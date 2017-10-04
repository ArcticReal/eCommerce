package com.skytala.eCommerce.domain.productReview.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productReview.model.ProductReview;

public class ProductReviewMapper  {


	public static Map<String, Object> map(ProductReview productreview) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productreview.getProductReviewId() != null ){
			returnVal.put("productReviewId",productreview.getProductReviewId());
}

		if(productreview.getProductStoreId() != null ){
			returnVal.put("productStoreId",productreview.getProductStoreId());
}

		if(productreview.getProductId() != null ){
			returnVal.put("productId",productreview.getProductId());
}

		if(productreview.getUserLoginId() != null ){
			returnVal.put("userLoginId",productreview.getUserLoginId());
}

		if(productreview.getStatusId() != null ){
			returnVal.put("statusId",productreview.getStatusId());
}

		if(productreview.getPostedAnonymous() != null ){
			returnVal.put("postedAnonymous",productreview.getPostedAnonymous());
}

		if(productreview.getPostedDateTime() != null ){
			returnVal.put("postedDateTime",productreview.getPostedDateTime());
}

		if(productreview.getProductRating() != null ){
			returnVal.put("productRating",productreview.getProductRating());
}

		if(productreview.getProductReview() != null ){
			returnVal.put("productReview",productreview.getProductReview());
}

		return returnVal;
}


	public static ProductReview map(Map<String, Object> fields) {

		ProductReview returnVal = new ProductReview();

		if(fields.get("productReviewId") != null) {
			returnVal.setProductReviewId((String) fields.get("productReviewId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("postedAnonymous") != null) {
			returnVal.setPostedAnonymous((boolean) fields.get("postedAnonymous"));
}

		if(fields.get("postedDateTime") != null) {
			returnVal.setPostedDateTime((Timestamp) fields.get("postedDateTime"));
}

		if(fields.get("productRating") != null) {
			returnVal.setProductRating((BigDecimal) fields.get("productRating"));
}

		if(fields.get("productReview") != null) {
			returnVal.setProductReview((String) fields.get("productReview"));
}


		return returnVal;
 } 
	public static ProductReview mapstrstr(Map<String, String> fields) throws Exception {

		ProductReview returnVal = new ProductReview();

		if(fields.get("productReviewId") != null) {
			returnVal.setProductReviewId((String) fields.get("productReviewId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("postedAnonymous") != null) {
String buf;
buf = fields.get("postedAnonymous");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPostedAnonymous(ibuf);
}

		if(fields.get("postedDateTime") != null) {
String buf = fields.get("postedDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPostedDateTime(ibuf);
}

		if(fields.get("productRating") != null) {
String buf;
buf = fields.get("productRating");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductRating(bd);
}

		if(fields.get("productReview") != null) {
			returnVal.setProductReview((String) fields.get("productReview"));
}


		return returnVal;
 } 
	public static ProductReview map(GenericValue val) {

ProductReview returnVal = new ProductReview();
		returnVal.setProductReviewId(val.getString("productReviewId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPostedAnonymous(val.getBoolean("postedAnonymous"));
		returnVal.setPostedDateTime(val.getTimestamp("postedDateTime"));
		returnVal.setProductRating(val.getBigDecimal("productRating"));
		returnVal.setProductReview(val.getString("productReview"));


return returnVal;

}

public static ProductReview map(HttpServletRequest request) throws Exception {

		ProductReview returnVal = new ProductReview();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productReviewId")) {
returnVal.setProductReviewId(request.getParameter("productReviewId"));
}

		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("userLoginId"))  {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("postedAnonymous"))  {
String buf = request.getParameter("postedAnonymous");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setPostedAnonymous(ibuf);
}
		if(paramMap.containsKey("postedDateTime"))  {
String buf = request.getParameter("postedDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPostedDateTime(ibuf);
}
		if(paramMap.containsKey("productRating"))  {
String buf = request.getParameter("productRating");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductRating(bd);
}
		if(paramMap.containsKey("productReview"))  {
returnVal.setProductReview(request.getParameter("productReview"));
}
return returnVal;

}
}

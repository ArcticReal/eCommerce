package com.skytala.eCommerce.domain.product.relations.productCategoryLink.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productCategoryLink.model.ProductCategoryLink;

public class ProductCategoryLinkMapper  {


	public static Map<String, Object> map(ProductCategoryLink productcategorylink) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategorylink.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productcategorylink.getProductCategoryId());
}

		if(productcategorylink.getLinkSeqId() != null ){
			returnVal.put("linkSeqId",productcategorylink.getLinkSeqId());
}

		if(productcategorylink.getFromDate() != null ){
			returnVal.put("fromDate",productcategorylink.getFromDate());
}

		if(productcategorylink.getThruDate() != null ){
			returnVal.put("thruDate",productcategorylink.getThruDate());
}

		if(productcategorylink.getComments() != null ){
			returnVal.put("comments",productcategorylink.getComments());
}

		if(productcategorylink.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productcategorylink.getSequenceNum());
}

		if(productcategorylink.getTitleText() != null ){
			returnVal.put("titleText",productcategorylink.getTitleText());
}

		if(productcategorylink.getDetailText() != null ){
			returnVal.put("detailText",productcategorylink.getDetailText());
}

		if(productcategorylink.getImageUrl() != null ){
			returnVal.put("imageUrl",productcategorylink.getImageUrl());
}

		if(productcategorylink.getImageTwoUrl() != null ){
			returnVal.put("imageTwoUrl",productcategorylink.getImageTwoUrl());
}

		if(productcategorylink.getLinkTypeEnumId() != null ){
			returnVal.put("linkTypeEnumId",productcategorylink.getLinkTypeEnumId());
}

		if(productcategorylink.getLinkInfo() != null ){
			returnVal.put("linkInfo",productcategorylink.getLinkInfo());
}

		if(productcategorylink.getDetailSubScreen() != null ){
			returnVal.put("detailSubScreen",productcategorylink.getDetailSubScreen());
}

		return returnVal;
}


	public static ProductCategoryLink map(Map<String, Object> fields) {

		ProductCategoryLink returnVal = new ProductCategoryLink();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("linkSeqId") != null) {
			returnVal.setLinkSeqId((String) fields.get("linkSeqId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("titleText") != null) {
			returnVal.setTitleText((String) fields.get("titleText"));
}

		if(fields.get("detailText") != null) {
			returnVal.setDetailText((String) fields.get("detailText"));
}

		if(fields.get("imageUrl") != null) {
			returnVal.setImageUrl((String) fields.get("imageUrl"));
}

		if(fields.get("imageTwoUrl") != null) {
			returnVal.setImageTwoUrl((String) fields.get("imageTwoUrl"));
}

		if(fields.get("linkTypeEnumId") != null) {
			returnVal.setLinkTypeEnumId((String) fields.get("linkTypeEnumId"));
}

		if(fields.get("linkInfo") != null) {
			returnVal.setLinkInfo((String) fields.get("linkInfo"));
}

		if(fields.get("detailSubScreen") != null) {
			returnVal.setDetailSubScreen((String) fields.get("detailSubScreen"));
}


		return returnVal;
 } 
	public static ProductCategoryLink mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryLink returnVal = new ProductCategoryLink();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("linkSeqId") != null) {
			returnVal.setLinkSeqId((String) fields.get("linkSeqId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("titleText") != null) {
			returnVal.setTitleText((String) fields.get("titleText"));
}

		if(fields.get("detailText") != null) {
			returnVal.setDetailText((String) fields.get("detailText"));
}

		if(fields.get("imageUrl") != null) {
			returnVal.setImageUrl((String) fields.get("imageUrl"));
}

		if(fields.get("imageTwoUrl") != null) {
			returnVal.setImageTwoUrl((String) fields.get("imageTwoUrl"));
}

		if(fields.get("linkTypeEnumId") != null) {
			returnVal.setLinkTypeEnumId((String) fields.get("linkTypeEnumId"));
}

		if(fields.get("linkInfo") != null) {
			returnVal.setLinkInfo((String) fields.get("linkInfo"));
}

		if(fields.get("detailSubScreen") != null) {
			returnVal.setDetailSubScreen((String) fields.get("detailSubScreen"));
}


		return returnVal;
 } 
	public static ProductCategoryLink map(GenericValue val) {

ProductCategoryLink returnVal = new ProductCategoryLink();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setLinkSeqId(val.getString("linkSeqId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setTitleText(val.getString("titleText"));
		returnVal.setDetailText(val.getString("detailText"));
		returnVal.setImageUrl(val.getString("imageUrl"));
		returnVal.setImageTwoUrl(val.getString("imageTwoUrl"));
		returnVal.setLinkTypeEnumId(val.getString("linkTypeEnumId"));
		returnVal.setLinkInfo(val.getString("linkInfo"));
		returnVal.setDetailSubScreen(val.getString("detailSubScreen"));


return returnVal;

}

public static ProductCategoryLink map(HttpServletRequest request) throws Exception {

		ProductCategoryLink returnVal = new ProductCategoryLink();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("linkSeqId"))  {
returnVal.setLinkSeqId(request.getParameter("linkSeqId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("titleText"))  {
returnVal.setTitleText(request.getParameter("titleText"));
}
		if(paramMap.containsKey("detailText"))  {
returnVal.setDetailText(request.getParameter("detailText"));
}
		if(paramMap.containsKey("imageUrl"))  {
returnVal.setImageUrl(request.getParameter("imageUrl"));
}
		if(paramMap.containsKey("imageTwoUrl"))  {
returnVal.setImageTwoUrl(request.getParameter("imageTwoUrl"));
}
		if(paramMap.containsKey("linkTypeEnumId"))  {
returnVal.setLinkTypeEnumId(request.getParameter("linkTypeEnumId"));
}
		if(paramMap.containsKey("linkInfo"))  {
returnVal.setLinkInfo(request.getParameter("linkInfo"));
}
		if(paramMap.containsKey("detailSubScreen"))  {
returnVal.setDetailSubScreen(request.getParameter("detailSubScreen"));
}
return returnVal;

}
}

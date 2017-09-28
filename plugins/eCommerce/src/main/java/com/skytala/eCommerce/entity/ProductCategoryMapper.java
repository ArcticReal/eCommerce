package com.skytala.eCommerce.entity;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.entity.GenericValue;

public class ProductCategoryMapper {

	public static Map<String, Object> map(ProductCategory productcategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();

		if (productcategory.getProductCategoryId() != null) {
			returnVal.put("productCategoryId", productcategory.getProductCategoryId());
		}

		if (productcategory.getProductCategoryTypeId() != null) {
			returnVal.put("productCategoryTypeId", productcategory.getProductCategoryTypeId());
		}

		if (productcategory.getPrimaryParentCategoryId() != null) {
			returnVal.put("primaryParentCategoryId", productcategory.getPrimaryParentCategoryId());
		}

		if (productcategory.getCategoryName() != null) {
			returnVal.put("categoryName", productcategory.getCategoryName());
		}

		if (productcategory.getDescription() != null) {
			returnVal.put("description", productcategory.getDescription());
		}

		if (productcategory.getLongDescription() != null) {
			returnVal.put("longDescription", productcategory.getLongDescription());
		}

		if (productcategory.getCategoryImageUrl() != null) {
			returnVal.put("categoryImageUrl", productcategory.getCategoryImageUrl());
		}

		if (productcategory.getLinkOneImageUrl() != null) {
			returnVal.put("linkOneImageUrl", productcategory.getLinkOneImageUrl());
		}

		if (productcategory.getLinkTwoImageUrl() != null) {
			returnVal.put("linkTwoImageUrl", productcategory.getLinkTwoImageUrl());
		}

		if (productcategory.getDetailScreen() != null) {
			returnVal.put("detailScreen", productcategory.getDetailScreen());
		}

		if (productcategory.getShowInSelect() != null) {
			returnVal.put("showInSelect", productcategory.getShowInSelect());
		}

		return returnVal;
	}

	public static ProductCategory map(Map<String, Object> fields) {

		ProductCategory returnVal = new ProductCategory();

		if (fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
		}

		if (fields.get("productCategoryTypeId") != null) {
			returnVal.setProductCategoryTypeId((String) fields.get("productCategoryTypeId"));
		}

		if (fields.get("primaryParentCategoryId") != null) {
			returnVal.setPrimaryParentCategoryId((String) fields.get("primaryParentCategoryId"));
		}

		if (fields.get("categoryName") != null) {
			returnVal.setCategoryName((String) fields.get("categoryName"));
		}

		if (fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
		}

		if (fields.get("longDescription") != null) {
			returnVal.setLongDescription((String) fields.get("longDescription"));
		}

		if (fields.get("categoryImageUrl") != null) {
			returnVal.setCategoryImageUrl((String) fields.get("categoryImageUrl"));
		}

		if (fields.get("linkOneImageUrl") != null) {
			returnVal.setLinkOneImageUrl((String) fields.get("linkOneImageUrl"));
		}

		if (fields.get("linkTwoImageUrl") != null) {
			returnVal.setLinkTwoImageUrl((String) fields.get("linkTwoImageUrl"));
		}

		if (fields.get("detailScreen") != null) {
			returnVal.setDetailScreen((String) fields.get("detailScreen"));
		}

		if (fields.get("showInSelect") != null) {
			returnVal.setShowInSelect((boolean) fields.get("showInSelect"));
		}

		return returnVal;
	}

	public static ProductCategory mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategory returnVal = new ProductCategory();

		if (fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
		}

		if (fields.get("productCategoryTypeId") != null) {
			returnVal.setProductCategoryTypeId((String) fields.get("productCategoryTypeId"));
		}

		if (fields.get("primaryParentCategoryId") != null) {
			returnVal.setPrimaryParentCategoryId((String) fields.get("primaryParentCategoryId"));
		}

		if (fields.get("categoryName") != null) {
			returnVal.setCategoryName((String) fields.get("categoryName"));
		}

		if (fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
		}

		if (fields.get("longDescription") != null) {
			returnVal.setLongDescription((String) fields.get("longDescription"));
		}

		if (fields.get("categoryImageUrl") != null) {
			returnVal.setCategoryImageUrl((String) fields.get("categoryImageUrl"));
		}

		if (fields.get("linkOneImageUrl") != null) {
			returnVal.setLinkOneImageUrl((String) fields.get("linkOneImageUrl"));
		}

		if (fields.get("linkTwoImageUrl") != null) {
			returnVal.setLinkTwoImageUrl((String) fields.get("linkTwoImageUrl"));
		}

		if (fields.get("detailScreen") != null) {
			returnVal.setDetailScreen((String) fields.get("detailScreen"));
		}

		if (fields.get("showInSelect") != null) {
			String buf;
			buf = fields.get("showInSelect");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setShowInSelect(ibuf);
		}

		return returnVal;
	}

	public static ProductCategory map(GenericValue val) {

		ProductCategory returnVal = new ProductCategory();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setProductCategoryTypeId(val.getString("productCategoryTypeId"));
		returnVal.setPrimaryParentCategoryId(val.getString("primaryParentCategoryId"));
		returnVal.setCategoryName(val.getString("categoryName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setLongDescription(val.getString("longDescription"));
		returnVal.setCategoryImageUrl(val.getString("categoryImageUrl"));
		returnVal.setLinkOneImageUrl(val.getString("linkOneImageUrl"));
		returnVal.setLinkTwoImageUrl(val.getString("linkTwoImageUrl"));
		returnVal.setDetailScreen(val.getString("detailScreen"));
		returnVal.setShowInSelect(val.getBoolean("showInSelect"));

		return returnVal;

	}

	public static ProductCategory map(HttpServletRequest request) throws Exception {

		ProductCategory returnVal = new ProductCategory();

		Map<String, String[]> paramMap = request.getParameterMap();

		if (paramMap.containsKey("productCategoryId")) {
			returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
		}

		if (paramMap.containsKey("productCategoryTypeId")) {
			returnVal.setProductCategoryTypeId(request.getParameter("productCategoryTypeId"));
		}
		if (paramMap.containsKey("primaryParentCategoryId")) {
			returnVal.setPrimaryParentCategoryId(request.getParameter("primaryParentCategoryId"));
		}
		if (paramMap.containsKey("categoryName")) {
			returnVal.setCategoryName(request.getParameter("categoryName"));
		}
		if (paramMap.containsKey("description")) {
			returnVal.setDescription(request.getParameter("description"));
		}
		if (paramMap.containsKey("longDescription")) {
			returnVal.setLongDescription(request.getParameter("longDescription"));
		}
		if (paramMap.containsKey("categoryImageUrl")) {
			returnVal.setCategoryImageUrl(request.getParameter("categoryImageUrl"));
		}
		if (paramMap.containsKey("linkOneImageUrl")) {
			returnVal.setLinkOneImageUrl(request.getParameter("linkOneImageUrl"));
		}
		if (paramMap.containsKey("linkTwoImageUrl")) {
			returnVal.setLinkTwoImageUrl(request.getParameter("linkTwoImageUrl"));
		}
		if (paramMap.containsKey("detailScreen")) {
			returnVal.setDetailScreen(request.getParameter("detailScreen"));
		}
		if (paramMap.containsKey("showInSelect")) {
			String buf = request.getParameter("showInSelect");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setShowInSelect(ibuf);
		}
		return returnVal;

	}
}

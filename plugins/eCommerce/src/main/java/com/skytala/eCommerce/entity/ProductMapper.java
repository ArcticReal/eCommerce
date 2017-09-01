package com.skytala.eCommerce.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.entity.GenericValue;

public class ProductMapper {

	public static Map<String, Object> map(Product product) {

		Map<String, Object> returnVal = new HashMap<String, Object>();

		if (product.getProductId() != null) {
			returnVal.put("productId", product.getProductId());
		}

		if (product.getProductTypeId() != null) {
			returnVal.put("productTypeId", product.getProductTypeId());
		}

		if (product.getPrimaryProductCategoryId() != null) {
			returnVal.put("primaryProductCategoryId", product.getPrimaryProductCategoryId());
		}

		if (product.getManufacturerPartyId() != null) {
			returnVal.put("manufacturerPartyId", product.getManufacturerPartyId());
		}

		if (product.getFacilityId() != null) {
			returnVal.put("facilityId", product.getFacilityId());
		}

		if (product.getIntroductionDate() != null) {
			returnVal.put("introductionDate", product.getIntroductionDate());
		}

		if (product.getReleaseDate() != null) {
			returnVal.put("releaseDate", product.getReleaseDate());
		}

		if (product.getSupportDiscontinuationDate() != null) {
			returnVal.put("supportDiscontinuationDate", product.getSupportDiscontinuationDate());
		}

		if (product.getSalesDiscontinuationDate() != null) {
			returnVal.put("salesDiscontinuationDate", product.getSalesDiscontinuationDate());
		}

		if (product.getSalesDiscWhenNotAvail() != null) {
			returnVal.put("salesDiscWhenNotAvail", product.getSalesDiscWhenNotAvail());
		}

		if (product.getInternalName() != null) {
			returnVal.put("internalName", product.getInternalName());
		}

		if (product.getBrandName() != null) {
			returnVal.put("brandName", product.getBrandName());
		}

		if (product.getComments() != null) {
			returnVal.put("comments", product.getComments());
		}

		if (product.getProductName() != null) {
			returnVal.put("productName", product.getProductName());
		}

		if (product.getDescription() != null) {
			returnVal.put("description", product.getDescription());
		}

		if (product.getLongDescription() != null) {
			returnVal.put("longDescription", product.getLongDescription());
		}

		if (product.getPriceDetailText() != null) {
			returnVal.put("priceDetailText", product.getPriceDetailText());
		}

		if (product.getSmallImageUrl() != null) {
			returnVal.put("smallImageUrl", product.getSmallImageUrl());
		}

		if (product.getMediumImageUrl() != null) {
			returnVal.put("mediumImageUrl", product.getMediumImageUrl());
		}

		if (product.getLargeImageUrl() != null) {
			returnVal.put("largeImageUrl", product.getLargeImageUrl());
		}

		if (product.getDetailImageUrl() != null) {
			returnVal.put("detailImageUrl", product.getDetailImageUrl());
		}

		if (product.getOriginalImageUrl() != null) {
			returnVal.put("originalImageUrl", product.getOriginalImageUrl());
		}

		if (product.getDetailScreen() != null) {
			returnVal.put("detailScreen", product.getDetailScreen());
		}

		if (product.getInventoryMessage() != null) {
			returnVal.put("inventoryMessage", product.getInventoryMessage());
		}

		if (product.getInventoryItemTypeId() != null) {
			returnVal.put("inventoryItemTypeId", product.getInventoryItemTypeId());
		}

		if (product.getRequireInventory() != null) {
			returnVal.put("requireInventory", product.getRequireInventory());
		}

		if (product.getQuantityUomId() != null) {
			returnVal.put("quantityUomId", product.getQuantityUomId());
		}

		if (product.getQuantityIncluded() != null) {
			returnVal.put("quantityIncluded", product.getQuantityIncluded());
		}

		if (product.getPiecesIncluded() != null) {
			returnVal.put("piecesIncluded", product.getPiecesIncluded());
		}

		if (product.getRequireAmount() != null) {
			returnVal.put("requireAmount", product.getRequireAmount());
		}

		if (product.getFixedAmount() != null) {
			returnVal.put("fixedAmount", product.getFixedAmount());
		}

		if (product.getAmountUomTypeId() != null) {
			returnVal.put("amountUomTypeId", product.getAmountUomTypeId());
		}

		if (product.getWeightUomId() != null) {
			returnVal.put("weightUomId", product.getWeightUomId());
		}

		if (product.getShippingWeight() != null) {
			returnVal.put("shippingWeight", product.getShippingWeight());
		}

		if (product.getProductWeight() != null) {
			returnVal.put("productWeight", product.getProductWeight());
		}

		if (product.getHeightUomId() != null) {
			returnVal.put("heightUomId", product.getHeightUomId());
		}

		if (product.getProductHeight() != null) {
			returnVal.put("productHeight", product.getProductHeight());
		}

		if (product.getShippingHeight() != null) {
			returnVal.put("shippingHeight", product.getShippingHeight());
		}

		if (product.getWidthUomId() != null) {
			returnVal.put("widthUomId", product.getWidthUomId());
		}

		if (product.getProductWidth() != null) {
			returnVal.put("productWidth", product.getProductWidth());
		}

		if (product.getShippingWidth() != null) {
			returnVal.put("shippingWidth", product.getShippingWidth());
		}

		if (product.getDepthUomId() != null) {
			returnVal.put("depthUomId", product.getDepthUomId());
		}

		if (product.getProductDepth() != null) {
			returnVal.put("productDepth", product.getProductDepth());
		}

		if (product.getShippingDepth() != null) {
			returnVal.put("shippingDepth", product.getShippingDepth());
		}

		if (product.getDiameterUomId() != null) {
			returnVal.put("diameterUomId", product.getDiameterUomId());
		}

		if (product.getProductDiameter() != null) {
			returnVal.put("productDiameter", product.getProductDiameter());
		}

		if (product.getProductRating() != null) {
			returnVal.put("productRating", product.getProductRating());
		}

		if (product.getRatingTypeEnum() != null) {
			returnVal.put("ratingTypeEnum", product.getRatingTypeEnum());
		}

		if (product.getReturnable() != null) {
			returnVal.put("returnable", product.getReturnable());
		}

		if (product.getTaxable() != null) {
			returnVal.put("taxable", product.getTaxable());
		}

		if (product.getChargeShipping() != null) {
			returnVal.put("chargeShipping", product.getChargeShipping());
		}

		if (product.getAutoCreateKeywords() != null) {
			returnVal.put("autoCreateKeywords", product.getAutoCreateKeywords());
		}

		if (product.getIncludeInPromotions() != null) {
			returnVal.put("includeInPromotions", product.getIncludeInPromotions());
		}

		if (product.getIsVirtual() != null) {
			returnVal.put("isVirtual", product.getIsVirtual());
		}

		if (product.getIsVariant() != null) {
			returnVal.put("isVariant", product.getIsVariant());
		}

		if (product.getVirtualVariantMethodEnum() != null) {
			returnVal.put("virtualVariantMethodEnum", product.getVirtualVariantMethodEnum());
		}

		if (product.getOriginGeoId() != null) {
			returnVal.put("originGeoId", product.getOriginGeoId());
		}

		if (product.getRequirementMethodEnumId() != null) {
			returnVal.put("requirementMethodEnumId", product.getRequirementMethodEnumId());
		}

		if (product.getBillOfMaterialLevel() != null) {
			returnVal.put("billOfMaterialLevel", product.getBillOfMaterialLevel());
		}

		if (product.getReservMaxPersons() != null) {
			returnVal.put("reservMaxPersons", product.getReservMaxPersons());
		}

		if (product.getReserv2ndPPPerc() != null) {
			returnVal.put("reserv2ndPPPerc", product.getReserv2ndPPPerc());
		}

		if (product.getReservNthPPPerc() != null) {
			returnVal.put("reservNthPPPerc", product.getReservNthPPPerc());
		}

		if (product.getConfigId() != null) {
			returnVal.put("configId", product.getConfigId());
		}

		if (product.getCreatedDate() != null) {
			returnVal.put("createdDate", product.getCreatedDate());
		}

		if (product.getCreatedByUserLogin() != null) {
			returnVal.put("createdByUserLogin", product.getCreatedByUserLogin());
		}

		if (product.getLastModifiedDate() != null) {
			returnVal.put("lastModifiedDate", product.getLastModifiedDate());
		}

		if (product.getLastModifiedByUserLogin() != null) {
			returnVal.put("lastModifiedByUserLogin", product.getLastModifiedByUserLogin());
		}

		if (product.getInShippingBox() != null) {
			returnVal.put("inShippingBox", product.getInShippingBox());
		}

		if (product.getDefaultShipmentBoxTypeId() != null) {
			returnVal.put("defaultShipmentBoxTypeId", product.getDefaultShipmentBoxTypeId());
		}

		if (product.getLotIdFilledIn() != null) {
			returnVal.put("lotIdFilledIn", product.getLotIdFilledIn());
		}

		if (product.getOrderDecimalQuantity() != null) {
			returnVal.put("orderDecimalQuantity", product.getOrderDecimalQuantity());
		}

		return returnVal;
	}

	public static Product map(Map<String, Object> fields) {

		Product returnVal = new Product();

		if (fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
		}

		if (fields.get("productTypeId") != null) {
			returnVal.setProductTypeId((String) fields.get("productTypeId"));
		}

		if (fields.get("primaryProductCategoryId") != null) {
			returnVal.setPrimaryProductCategoryId((String) fields.get("primaryProductCategoryId"));
		}

		if (fields.get("manufacturerPartyId") != null) {
			returnVal.setManufacturerPartyId((String) fields.get("manufacturerPartyId"));
		}

		if (fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
		}

		if (fields.get("introductionDate") != null) {
			returnVal.setIntroductionDate((Timestamp) fields.get("introductionDate"));
		}

		if (fields.get("releaseDate") != null) {
			returnVal.setReleaseDate((Timestamp) fields.get("releaseDate"));
		}

		if (fields.get("supportDiscontinuationDate") != null) {
			returnVal.setSupportDiscontinuationDate((Timestamp) fields.get("supportDiscontinuationDate"));
		}

		if (fields.get("salesDiscontinuationDate") != null) {
			returnVal.setSalesDiscontinuationDate((Timestamp) fields.get("salesDiscontinuationDate"));
		}

		if (fields.get("salesDiscWhenNotAvail") != null) {
			returnVal.setSalesDiscWhenNotAvail((boolean) fields.get("salesDiscWhenNotAvail"));
		}

		if (fields.get("internalName") != null) {
			returnVal.setInternalName((String) fields.get("internalName"));
		}

		if (fields.get("brandName") != null) {
			returnVal.setBrandName((String) fields.get("brandName"));
		}

		if (fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
		}

		if (fields.get("productName") != null) {
			returnVal.setProductName((String) fields.get("productName"));
		}

		if (fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
		}

		if (fields.get("longDescription") != null) {
			returnVal.setLongDescription((String) fields.get("longDescription"));
		}

		if (fields.get("priceDetailText") != null) {
			returnVal.setPriceDetailText((String) fields.get("priceDetailText"));
		}

		if (fields.get("smallImageUrl") != null) {
			returnVal.setSmallImageUrl((String) fields.get("smallImageUrl"));
		}

		if (fields.get("mediumImageUrl") != null) {
			returnVal.setMediumImageUrl((String) fields.get("mediumImageUrl"));
		}

		if (fields.get("largeImageUrl") != null) {
			returnVal.setLargeImageUrl((String) fields.get("largeImageUrl"));
		}

		if (fields.get("detailImageUrl") != null) {
			returnVal.setDetailImageUrl((String) fields.get("detailImageUrl"));
		}

		if (fields.get("originalImageUrl") != null) {
			returnVal.setOriginalImageUrl((String) fields.get("originalImageUrl"));
		}

		if (fields.get("detailScreen") != null) {
			returnVal.setDetailScreen((String) fields.get("detailScreen"));
		}

		if (fields.get("inventoryMessage") != null) {
			returnVal.setInventoryMessage((String) fields.get("inventoryMessage"));
		}

		if (fields.get("inventoryItemTypeId") != null) {
			returnVal.setInventoryItemTypeId((String) fields.get("inventoryItemTypeId"));
		}

		if (fields.get("requireInventory") != null) {
			returnVal.setRequireInventory((boolean) fields.get("requireInventory"));
		}

		if (fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
		}

		if (fields.get("quantityIncluded") != null) {
			returnVal.setQuantityIncluded((BigDecimal) fields.get("quantityIncluded"));
		}

		if (fields.get("piecesIncluded") != null) {
			returnVal.setPiecesIncluded((long) fields.get("piecesIncluded"));
		}

		if (fields.get("requireAmount") != null) {
			returnVal.setRequireAmount((boolean) fields.get("requireAmount"));
		}

		if (fields.get("fixedAmount") != null) {
			returnVal.setFixedAmount((BigDecimal) fields.get("fixedAmount"));
		}

		if (fields.get("amountUomTypeId") != null) {
			returnVal.setAmountUomTypeId((String) fields.get("amountUomTypeId"));
		}

		if (fields.get("weightUomId") != null) {
			returnVal.setWeightUomId((String) fields.get("weightUomId"));
		}

		if (fields.get("shippingWeight") != null) {
			returnVal.setShippingWeight((BigDecimal) fields.get("shippingWeight"));
		}

		if (fields.get("productWeight") != null) {
			returnVal.setProductWeight((BigDecimal) fields.get("productWeight"));
		}

		if (fields.get("heightUomId") != null) {
			returnVal.setHeightUomId((String) fields.get("heightUomId"));
		}

		if (fields.get("productHeight") != null) {
			returnVal.setProductHeight((BigDecimal) fields.get("productHeight"));
		}

		if (fields.get("shippingHeight") != null) {
			returnVal.setShippingHeight((BigDecimal) fields.get("shippingHeight"));
		}

		if (fields.get("widthUomId") != null) {
			returnVal.setWidthUomId((String) fields.get("widthUomId"));
		}

		if (fields.get("productWidth") != null) {
			returnVal.setProductWidth((BigDecimal) fields.get("productWidth"));
		}

		if (fields.get("shippingWidth") != null) {
			returnVal.setShippingWidth((BigDecimal) fields.get("shippingWidth"));
		}

		if (fields.get("depthUomId") != null) {
			returnVal.setDepthUomId((String) fields.get("depthUomId"));
		}

		if (fields.get("productDepth") != null) {
			returnVal.setProductDepth((BigDecimal) fields.get("productDepth"));
		}

		if (fields.get("shippingDepth") != null) {
			returnVal.setShippingDepth((BigDecimal) fields.get("shippingDepth"));
		}

		if (fields.get("diameterUomId") != null) {
			returnVal.setDiameterUomId((String) fields.get("diameterUomId"));
		}

		if (fields.get("productDiameter") != null) {
			returnVal.setProductDiameter((BigDecimal) fields.get("productDiameter"));
		}

		if (fields.get("productRating") != null) {
			returnVal.setProductRating((BigDecimal) fields.get("productRating"));
		}

		if (fields.get("ratingTypeEnum") != null) {
			returnVal.setRatingTypeEnum((String) fields.get("ratingTypeEnum"));
		}

		if (fields.get("returnable") != null) {
			returnVal.setReturnable((boolean) fields.get("returnable"));
		}

		if (fields.get("taxable") != null) {
			returnVal.setTaxable((boolean) fields.get("taxable"));
		}

		if (fields.get("chargeShipping") != null) {
			returnVal.setChargeShipping((boolean) fields.get("chargeShipping"));
		}

		if (fields.get("autoCreateKeywords") != null) {
			returnVal.setAutoCreateKeywords((boolean) fields.get("autoCreateKeywords"));
		}

		if (fields.get("includeInPromotions") != null) {
			returnVal.setIncludeInPromotions((boolean) fields.get("includeInPromotions"));
		}

		if (fields.get("isVirtual") != null) {
			returnVal.setIsVirtual((boolean) fields.get("isVirtual"));
		}

		if (fields.get("isVariant") != null) {
			returnVal.setIsVariant((boolean) fields.get("isVariant"));
		}

		if (fields.get("virtualVariantMethodEnum") != null) {
			returnVal.setVirtualVariantMethodEnum((String) fields.get("virtualVariantMethodEnum"));
		}

		if (fields.get("originGeoId") != null) {
			returnVal.setOriginGeoId((String) fields.get("originGeoId"));
		}

		if (fields.get("requirementMethodEnumId") != null) {
			returnVal.setRequirementMethodEnumId((String) fields.get("requirementMethodEnumId"));
		}

		if (fields.get("billOfMaterialLevel") != null) {
			returnVal.setBillOfMaterialLevel((long) fields.get("billOfMaterialLevel"));
		}

		if (fields.get("reservMaxPersons") != null) {
			returnVal.setReservMaxPersons((BigDecimal) fields.get("reservMaxPersons"));
		}

		if (fields.get("reserv2ndPPPerc") != null) {
			returnVal.setReserv2ndPPPerc((BigDecimal) fields.get("reserv2ndPPPerc"));
		}

		if (fields.get("reservNthPPPerc") != null) {
			returnVal.setReservNthPPPerc((BigDecimal) fields.get("reservNthPPPerc"));
		}

		if (fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
		}

		if (fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
		}

		if (fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
		}

		if (fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
		}

		if (fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
		}

		if (fields.get("inShippingBox") != null) {
			returnVal.setInShippingBox((boolean) fields.get("inShippingBox"));
		}

		if (fields.get("defaultShipmentBoxTypeId") != null) {
			returnVal.setDefaultShipmentBoxTypeId((String) fields.get("defaultShipmentBoxTypeId"));
		}

		if (fields.get("lotIdFilledIn") != null) {
			returnVal.setLotIdFilledIn((String) fields.get("lotIdFilledIn"));
		}

		if (fields.get("orderDecimalQuantity") != null) {
			returnVal.setOrderDecimalQuantity((boolean) fields.get("orderDecimalQuantity"));
		}

		return returnVal;
	}

	public static Product mapstrstr(Map<String, String> fields) throws Exception {

		Product returnVal = new Product();

		if (fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
		}

		if (fields.get("productTypeId") != null) {
			returnVal.setProductTypeId((String) fields.get("productTypeId"));
		}

		if (fields.get("primaryProductCategoryId") != null) {
			returnVal.setPrimaryProductCategoryId((String) fields.get("primaryProductCategoryId"));
		}

		if (fields.get("manufacturerPartyId") != null) {
			returnVal.setManufacturerPartyId((String) fields.get("manufacturerPartyId"));
		}

		if (fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
		}

		if (fields.get("introductionDate") != null) {
			String buf = fields.get("introductionDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setIntroductionDate(ibuf);
		}

		if (fields.get("releaseDate") != null) {
			String buf = fields.get("releaseDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReleaseDate(ibuf);
		}

		if (fields.get("supportDiscontinuationDate") != null) {
			String buf = fields.get("supportDiscontinuationDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSupportDiscontinuationDate(ibuf);
		}

		if (fields.get("salesDiscontinuationDate") != null) {
			String buf = fields.get("salesDiscontinuationDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSalesDiscontinuationDate(ibuf);
		}

		if (fields.get("salesDiscWhenNotAvail") != null) {
			String buf;
			buf = fields.get("salesDiscWhenNotAvail");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSalesDiscWhenNotAvail(ibuf);
		}

		if (fields.get("internalName") != null) {
			returnVal.setInternalName((String) fields.get("internalName"));
		}

		if (fields.get("brandName") != null) {
			returnVal.setBrandName((String) fields.get("brandName"));
		}

		if (fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
		}

		if (fields.get("productName") != null) {
			returnVal.setProductName((String) fields.get("productName"));
		}

		if (fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
		}

		if (fields.get("longDescription") != null) {
			returnVal.setLongDescription((String) fields.get("longDescription"));
		}

		if (fields.get("priceDetailText") != null) {
			returnVal.setPriceDetailText((String) fields.get("priceDetailText"));
		}

		if (fields.get("smallImageUrl") != null) {
			returnVal.setSmallImageUrl((String) fields.get("smallImageUrl"));
		}

		if (fields.get("mediumImageUrl") != null) {
			returnVal.setMediumImageUrl((String) fields.get("mediumImageUrl"));
		}

		if (fields.get("largeImageUrl") != null) {
			returnVal.setLargeImageUrl((String) fields.get("largeImageUrl"));
		}

		if (fields.get("detailImageUrl") != null) {
			returnVal.setDetailImageUrl((String) fields.get("detailImageUrl"));
		}

		if (fields.get("originalImageUrl") != null) {
			returnVal.setOriginalImageUrl((String) fields.get("originalImageUrl"));
		}

		if (fields.get("detailScreen") != null) {
			returnVal.setDetailScreen((String) fields.get("detailScreen"));
		}

		if (fields.get("inventoryMessage") != null) {
			returnVal.setInventoryMessage((String) fields.get("inventoryMessage"));
		}

		if (fields.get("inventoryItemTypeId") != null) {
			returnVal.setInventoryItemTypeId((String) fields.get("inventoryItemTypeId"));
		}

		if (fields.get("requireInventory") != null) {
			String buf;
			buf = fields.get("requireInventory");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireInventory(ibuf);
		}

		if (fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
		}

		if (fields.get("quantityIncluded") != null) {
			String buf;
			buf = fields.get("quantityIncluded");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityIncluded(bd);
		}

		if (fields.get("piecesIncluded") != null) {
			String buf;
			buf = fields.get("piecesIncluded");
			long ibuf = Long.parseLong(buf);
			returnVal.setPiecesIncluded(ibuf);
		}

		if (fields.get("requireAmount") != null) {
			String buf;
			buf = fields.get("requireAmount");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireAmount(ibuf);
		}

		if (fields.get("fixedAmount") != null) {
			String buf;
			buf = fields.get("fixedAmount");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFixedAmount(bd);
		}

		if (fields.get("amountUomTypeId") != null) {
			returnVal.setAmountUomTypeId((String) fields.get("amountUomTypeId"));
		}

		if (fields.get("weightUomId") != null) {
			returnVal.setWeightUomId((String) fields.get("weightUomId"));
		}

		if (fields.get("shippingWeight") != null) {
			String buf;
			buf = fields.get("shippingWeight");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingWeight(bd);
		}

		if (fields.get("productWeight") != null) {
			String buf;
			buf = fields.get("productWeight");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductWeight(bd);
		}

		if (fields.get("heightUomId") != null) {
			returnVal.setHeightUomId((String) fields.get("heightUomId"));
		}

		if (fields.get("productHeight") != null) {
			String buf;
			buf = fields.get("productHeight");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductHeight(bd);
		}

		if (fields.get("shippingHeight") != null) {
			String buf;
			buf = fields.get("shippingHeight");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingHeight(bd);
		}

		if (fields.get("widthUomId") != null) {
			returnVal.setWidthUomId((String) fields.get("widthUomId"));
		}

		if (fields.get("productWidth") != null) {
			String buf;
			buf = fields.get("productWidth");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductWidth(bd);
		}

		if (fields.get("shippingWidth") != null) {
			String buf;
			buf = fields.get("shippingWidth");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingWidth(bd);
		}

		if (fields.get("depthUomId") != null) {
			returnVal.setDepthUomId((String) fields.get("depthUomId"));
		}

		if (fields.get("productDepth") != null) {
			String buf;
			buf = fields.get("productDepth");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductDepth(bd);
		}

		if (fields.get("shippingDepth") != null) {
			String buf;
			buf = fields.get("shippingDepth");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingDepth(bd);
		}

		if (fields.get("diameterUomId") != null) {
			returnVal.setDiameterUomId((String) fields.get("diameterUomId"));
		}

		if (fields.get("productDiameter") != null) {
			String buf;
			buf = fields.get("productDiameter");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductDiameter(bd);
		}

		if (fields.get("productRating") != null) {
			String buf;
			buf = fields.get("productRating");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductRating(bd);
		}

		if (fields.get("ratingTypeEnum") != null) {
			returnVal.setRatingTypeEnum((String) fields.get("ratingTypeEnum"));
		}

		if (fields.get("returnable") != null) {
			String buf;
			buf = fields.get("returnable");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setReturnable(ibuf);
		}

		if (fields.get("taxable") != null) {
			String buf;
			buf = fields.get("taxable");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setTaxable(ibuf);
		}

		if (fields.get("chargeShipping") != null) {
			String buf;
			buf = fields.get("chargeShipping");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setChargeShipping(ibuf);
		}

		if (fields.get("autoCreateKeywords") != null) {
			String buf;
			buf = fields.get("autoCreateKeywords");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoCreateKeywords(ibuf);
		}

		if (fields.get("includeInPromotions") != null) {
			String buf;
			buf = fields.get("includeInPromotions");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeInPromotions(ibuf);
		}

		if (fields.get("isVirtual") != null) {
			String buf;
			buf = fields.get("isVirtual");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsVirtual(ibuf);
		}

		if (fields.get("isVariant") != null) {
			String buf;
			buf = fields.get("isVariant");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsVariant(ibuf);
		}

		if (fields.get("virtualVariantMethodEnum") != null) {
			returnVal.setVirtualVariantMethodEnum((String) fields.get("virtualVariantMethodEnum"));
		}

		if (fields.get("originGeoId") != null) {
			returnVal.setOriginGeoId((String) fields.get("originGeoId"));
		}

		if (fields.get("requirementMethodEnumId") != null) {
			returnVal.setRequirementMethodEnumId((String) fields.get("requirementMethodEnumId"));
		}

		if (fields.get("billOfMaterialLevel") != null) {
			String buf;
			buf = fields.get("billOfMaterialLevel");
			long ibuf = Long.parseLong(buf);
			returnVal.setBillOfMaterialLevel(ibuf);
		}

		if (fields.get("reservMaxPersons") != null) {
			String buf;
			buf = fields.get("reservMaxPersons");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservMaxPersons(bd);
		}

		if (fields.get("reserv2ndPPPerc") != null) {
			String buf;
			buf = fields.get("reserv2ndPPPerc");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReserv2ndPPPerc(bd);
		}

		if (fields.get("reservNthPPPerc") != null) {
			String buf;
			buf = fields.get("reservNthPPPerc");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservNthPPPerc(bd);
		}

		if (fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
		}

		if (fields.get("createdDate") != null) {
			String buf = fields.get("createdDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
		}

		if (fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
		}

		if (fields.get("lastModifiedDate") != null) {
			String buf = fields.get("lastModifiedDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
		}

		if (fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
		}

		if (fields.get("inShippingBox") != null) {
			String buf;
			buf = fields.get("inShippingBox");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setInShippingBox(ibuf);
		}

		if (fields.get("defaultShipmentBoxTypeId") != null) {
			returnVal.setDefaultShipmentBoxTypeId((String) fields.get("defaultShipmentBoxTypeId"));
		}

		if (fields.get("lotIdFilledIn") != null) {
			returnVal.setLotIdFilledIn((String) fields.get("lotIdFilledIn"));
		}

		if (fields.get("orderDecimalQuantity") != null) {
			String buf;
			buf = fields.get("orderDecimalQuantity");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setOrderDecimalQuantity(ibuf);
		}

		return returnVal;
	}

	public static Product map(GenericValue val) {

		Product returnVal = new Product();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductTypeId(val.getString("productTypeId"));
		returnVal.setPrimaryProductCategoryId(val.getString("primaryProductCategoryId"));
		returnVal.setManufacturerPartyId(val.getString("manufacturerPartyId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setIntroductionDate(val.getTimestamp("introductionDate"));
		returnVal.setReleaseDate(val.getTimestamp("releaseDate"));
		returnVal.setSupportDiscontinuationDate(val.getTimestamp("supportDiscontinuationDate"));
		returnVal.setSalesDiscontinuationDate(val.getTimestamp("salesDiscontinuationDate"));
		returnVal.setSalesDiscWhenNotAvail(val.getBoolean("salesDiscWhenNotAvail"));
		returnVal.setInternalName(val.getString("internalName"));
		returnVal.setBrandName(val.getString("brandName"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setProductName(val.getString("productName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setLongDescription(val.getString("longDescription"));
		returnVal.setPriceDetailText(val.getString("priceDetailText"));
		returnVal.setSmallImageUrl(val.getString("smallImageUrl"));
		returnVal.setMediumImageUrl(val.getString("mediumImageUrl"));
		returnVal.setLargeImageUrl(val.getString("largeImageUrl"));
		returnVal.setDetailImageUrl(val.getString("detailImageUrl"));
		returnVal.setOriginalImageUrl(val.getString("originalImageUrl"));
		returnVal.setDetailScreen(val.getString("detailScreen"));
		returnVal.setInventoryMessage(val.getString("inventoryMessage"));
		returnVal.setInventoryItemTypeId(val.getString("inventoryItemTypeId"));
		returnVal.setRequireInventory(val.getBoolean("requireInventory"));
		returnVal.setQuantityUomId(val.getString("quantityUomId"));
		returnVal.setQuantityIncluded(val.getBigDecimal("quantityIncluded"));
		returnVal.setPiecesIncluded(val.getLong("piecesIncluded"));
		returnVal.setRequireAmount(val.getBoolean("requireAmount"));
		returnVal.setFixedAmount(val.getBigDecimal("fixedAmount"));
		returnVal.setAmountUomTypeId(val.getString("amountUomTypeId"));
		returnVal.setWeightUomId(val.getString("weightUomId"));
		returnVal.setShippingWeight(val.getBigDecimal("shippingWeight"));
		returnVal.setProductWeight(val.getBigDecimal("productWeight"));
		returnVal.setHeightUomId(val.getString("heightUomId"));
		returnVal.setProductHeight(val.getBigDecimal("productHeight"));
		returnVal.setShippingHeight(val.getBigDecimal("shippingHeight"));
		returnVal.setWidthUomId(val.getString("widthUomId"));
		returnVal.setProductWidth(val.getBigDecimal("productWidth"));
		returnVal.setShippingWidth(val.getBigDecimal("shippingWidth"));
		returnVal.setDepthUomId(val.getString("depthUomId"));
		returnVal.setProductDepth(val.getBigDecimal("productDepth"));
		returnVal.setShippingDepth(val.getBigDecimal("shippingDepth"));
		returnVal.setDiameterUomId(val.getString("diameterUomId"));
		returnVal.setProductDiameter(val.getBigDecimal("productDiameter"));
		returnVal.setProductRating(val.getBigDecimal("productRating"));
		returnVal.setRatingTypeEnum(val.getString("ratingTypeEnum"));
		returnVal.setReturnable(val.getBoolean("returnable"));
		returnVal.setTaxable(val.getBoolean("taxable"));
		returnVal.setChargeShipping(val.getBoolean("chargeShipping"));
		returnVal.setAutoCreateKeywords(val.getBoolean("autoCreateKeywords"));
		returnVal.setIncludeInPromotions(val.getBoolean("includeInPromotions"));
		returnVal.setIsVirtual(val.getBoolean("isVirtual"));
		returnVal.setIsVariant(val.getBoolean("isVariant"));
		returnVal.setVirtualVariantMethodEnum(val.getString("virtualVariantMethodEnum"));
		returnVal.setOriginGeoId(val.getString("originGeoId"));
		returnVal.setRequirementMethodEnumId(val.getString("requirementMethodEnumId"));
		returnVal.setBillOfMaterialLevel(val.getLong("billOfMaterialLevel"));
		returnVal.setReservMaxPersons(val.getBigDecimal("reservMaxPersons"));
		returnVal.setReserv2ndPPPerc(val.getBigDecimal("reserv2ndPPPerc"));
		returnVal.setReservNthPPPerc(val.getBigDecimal("reservNthPPPerc"));
		returnVal.setConfigId(val.getString("configId"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));
		returnVal.setInShippingBox(val.getBoolean("inShippingBox"));
		returnVal.setDefaultShipmentBoxTypeId(val.getString("defaultShipmentBoxTypeId"));
		returnVal.setLotIdFilledIn(val.getString("lotIdFilledIn"));
		returnVal.setOrderDecimalQuantity(val.getBoolean("orderDecimalQuantity"));

		return returnVal;

	}

	public static Product map(HttpServletRequest request) throws Exception {

		Product returnVal = new Product();

		Map<String, String[]> paramMap = request.getParameterMap();

		if (!paramMap.containsKey("productId")) {
			throw new Exception("Error! Id required");
		} else {
			returnVal.setProductId(request.getParameter("productId"));
		}

		if (paramMap.containsKey("productTypeId")) {
			returnVal.setProductTypeId(request.getParameter("productTypeId"));
		}
		if (paramMap.containsKey("primaryProductCategoryId")) {
			returnVal.setPrimaryProductCategoryId(request.getParameter("primaryProductCategoryId"));
		}
		if (paramMap.containsKey("manufacturerPartyId")) {
			returnVal.setManufacturerPartyId(request.getParameter("manufacturerPartyId"));
		}
		if (paramMap.containsKey("facilityId")) {
			returnVal.setFacilityId(request.getParameter("facilityId"));
		}
		if (paramMap.containsKey("introductionDate")) {
			String buf = request.getParameter("introductionDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setIntroductionDate(ibuf);
		}
		if (paramMap.containsKey("releaseDate")) {
			String buf = request.getParameter("releaseDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReleaseDate(ibuf);
		}
		if (paramMap.containsKey("supportDiscontinuationDate")) {
			String buf = request.getParameter("supportDiscontinuationDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSupportDiscontinuationDate(ibuf);
		}
		if (paramMap.containsKey("salesDiscontinuationDate")) {
			String buf = request.getParameter("salesDiscontinuationDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSalesDiscontinuationDate(ibuf);
		}
		if (paramMap.containsKey("salesDiscWhenNotAvail")) {
			String buf = request.getParameter("salesDiscWhenNotAvail");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSalesDiscWhenNotAvail(ibuf);
		}
		if (paramMap.containsKey("internalName")) {
			returnVal.setInternalName(request.getParameter("internalName"));
		}
		if (paramMap.containsKey("brandName")) {
			returnVal.setBrandName(request.getParameter("brandName"));
		}
		if (paramMap.containsKey("comments")) {
			returnVal.setComments(request.getParameter("comments"));
		}
		if (paramMap.containsKey("productName")) {
			returnVal.setProductName(request.getParameter("productName"));
		}
		if (paramMap.containsKey("description")) {
			returnVal.setDescription(request.getParameter("description"));
		}
		if (paramMap.containsKey("longDescription")) {
			returnVal.setLongDescription(request.getParameter("longDescription"));
		}
		if (paramMap.containsKey("priceDetailText")) {
			returnVal.setPriceDetailText(request.getParameter("priceDetailText"));
		}
		if (paramMap.containsKey("smallImageUrl")) {
			returnVal.setSmallImageUrl(request.getParameter("smallImageUrl"));
		}
		if (paramMap.containsKey("mediumImageUrl")) {
			returnVal.setMediumImageUrl(request.getParameter("mediumImageUrl"));
		}
		if (paramMap.containsKey("largeImageUrl")) {
			returnVal.setLargeImageUrl(request.getParameter("largeImageUrl"));
		}
		if (paramMap.containsKey("detailImageUrl")) {
			returnVal.setDetailImageUrl(request.getParameter("detailImageUrl"));
		}
		if (paramMap.containsKey("originalImageUrl")) {
			returnVal.setOriginalImageUrl(request.getParameter("originalImageUrl"));
		}
		if (paramMap.containsKey("detailScreen")) {
			returnVal.setDetailScreen(request.getParameter("detailScreen"));
		}
		if (paramMap.containsKey("inventoryMessage")) {
			returnVal.setInventoryMessage(request.getParameter("inventoryMessage"));
		}
		if (paramMap.containsKey("inventoryItemTypeId")) {
			returnVal.setInventoryItemTypeId(request.getParameter("inventoryItemTypeId"));
		}
		if (paramMap.containsKey("requireInventory")) {
			String buf = request.getParameter("requireInventory");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireInventory(ibuf);
		}
		if (paramMap.containsKey("quantityUomId")) {
			returnVal.setQuantityUomId(request.getParameter("quantityUomId"));
		}
		if (paramMap.containsKey("quantityIncluded")) {
			String buf = request.getParameter("quantityIncluded");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityIncluded(bd);
		}
		if (paramMap.containsKey("piecesIncluded")) {
			String buf = request.getParameter("piecesIncluded");
			Long ibuf = Long.parseLong(buf);
			returnVal.setPiecesIncluded(ibuf);
		}
		if (paramMap.containsKey("requireAmount")) {
			String buf = request.getParameter("requireAmount");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireAmount(ibuf);
		}
		if (paramMap.containsKey("fixedAmount")) {
			String buf = request.getParameter("fixedAmount");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFixedAmount(bd);
		}
		if (paramMap.containsKey("amountUomTypeId")) {
			returnVal.setAmountUomTypeId(request.getParameter("amountUomTypeId"));
		}
		if (paramMap.containsKey("weightUomId")) {
			returnVal.setWeightUomId(request.getParameter("weightUomId"));
		}
		if (paramMap.containsKey("shippingWeight")) {
			String buf = request.getParameter("shippingWeight");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingWeight(bd);
		}
		if (paramMap.containsKey("productWeight")) {
			String buf = request.getParameter("productWeight");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductWeight(bd);
		}
		if (paramMap.containsKey("heightUomId")) {
			returnVal.setHeightUomId(request.getParameter("heightUomId"));
		}
		if (paramMap.containsKey("productHeight")) {
			String buf = request.getParameter("productHeight");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductHeight(bd);
		}
		if (paramMap.containsKey("shippingHeight")) {
			String buf = request.getParameter("shippingHeight");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingHeight(bd);
		}
		if (paramMap.containsKey("widthUomId")) {
			returnVal.setWidthUomId(request.getParameter("widthUomId"));
		}
		if (paramMap.containsKey("productWidth")) {
			String buf = request.getParameter("productWidth");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductWidth(bd);
		}
		if (paramMap.containsKey("shippingWidth")) {
			String buf = request.getParameter("shippingWidth");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingWidth(bd);
		}
		if (paramMap.containsKey("depthUomId")) {
			returnVal.setDepthUomId(request.getParameter("depthUomId"));
		}
		if (paramMap.containsKey("productDepth")) {
			String buf = request.getParameter("productDepth");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductDepth(bd);
		}
		if (paramMap.containsKey("shippingDepth")) {
			String buf = request.getParameter("shippingDepth");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingDepth(bd);
		}
		if (paramMap.containsKey("diameterUomId")) {
			returnVal.setDiameterUomId(request.getParameter("diameterUomId"));
		}
		if (paramMap.containsKey("productDiameter")) {
			String buf = request.getParameter("productDiameter");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductDiameter(bd);
		}
		if (paramMap.containsKey("productRating")) {
			String buf = request.getParameter("productRating");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductRating(bd);
		}
		if (paramMap.containsKey("ratingTypeEnum")) {
			returnVal.setRatingTypeEnum(request.getParameter("ratingTypeEnum"));
		}
		if (paramMap.containsKey("returnable")) {
			String buf = request.getParameter("returnable");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setReturnable(ibuf);
		}
		if (paramMap.containsKey("taxable")) {
			String buf = request.getParameter("taxable");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setTaxable(ibuf);
		}
		if (paramMap.containsKey("chargeShipping")) {
			String buf = request.getParameter("chargeShipping");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setChargeShipping(ibuf);
		}
		if (paramMap.containsKey("autoCreateKeywords")) {
			String buf = request.getParameter("autoCreateKeywords");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoCreateKeywords(ibuf);
		}
		if (paramMap.containsKey("includeInPromotions")) {
			String buf = request.getParameter("includeInPromotions");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeInPromotions(ibuf);
		}
		if (paramMap.containsKey("isVirtual")) {
			String buf = request.getParameter("isVirtual");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsVirtual(ibuf);
		}
		if (paramMap.containsKey("isVariant")) {
			String buf = request.getParameter("isVariant");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsVariant(ibuf);
		}
		if (paramMap.containsKey("virtualVariantMethodEnum")) {
			returnVal.setVirtualVariantMethodEnum(request.getParameter("virtualVariantMethodEnum"));
		}
		if (paramMap.containsKey("originGeoId")) {
			returnVal.setOriginGeoId(request.getParameter("originGeoId"));
		}
		if (paramMap.containsKey("requirementMethodEnumId")) {
			returnVal.setRequirementMethodEnumId(request.getParameter("requirementMethodEnumId"));
		}
		if (paramMap.containsKey("billOfMaterialLevel")) {
			String buf = request.getParameter("billOfMaterialLevel");
			Long ibuf = Long.parseLong(buf);
			returnVal.setBillOfMaterialLevel(ibuf);
		}
		if (paramMap.containsKey("reservMaxPersons")) {
			String buf = request.getParameter("reservMaxPersons");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservMaxPersons(bd);
		}
		if (paramMap.containsKey("reserv2ndPPPerc")) {
			String buf = request.getParameter("reserv2ndPPPerc");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReserv2ndPPPerc(bd);
		}
		if (paramMap.containsKey("reservNthPPPerc")) {
			String buf = request.getParameter("reservNthPPPerc");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservNthPPPerc(bd);
		}
		if (paramMap.containsKey("configId")) {
			returnVal.setConfigId(request.getParameter("configId"));
		}
		if (paramMap.containsKey("createdDate")) {
			String buf = request.getParameter("createdDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
		}
		if (paramMap.containsKey("createdByUserLogin")) {
			returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
		}
		if (paramMap.containsKey("lastModifiedDate")) {
			String buf = request.getParameter("lastModifiedDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
		}
		if (paramMap.containsKey("lastModifiedByUserLogin")) {
			returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
		}
		if (paramMap.containsKey("inShippingBox")) {
			String buf = request.getParameter("inShippingBox");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setInShippingBox(ibuf);
		}
		if (paramMap.containsKey("defaultShipmentBoxTypeId")) {
			returnVal.setDefaultShipmentBoxTypeId(request.getParameter("defaultShipmentBoxTypeId"));
		}
		if (paramMap.containsKey("lotIdFilledIn")) {
			returnVal.setLotIdFilledIn(request.getParameter("lotIdFilledIn"));
		}
		if (paramMap.containsKey("orderDecimalQuantity")) {
			String buf = request.getParameter("orderDecimalQuantity");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setOrderDecimalQuantity(ibuf);
		}
		return returnVal;

	}
}

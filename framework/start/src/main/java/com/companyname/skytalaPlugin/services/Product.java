package com.companyname.skytalaPlugin.services;

import java.util.Date;
import java.util.Map;

public class Product {

private String productId;
private String productTypeId;
private String primaryProductCategoryId;
private String manufacturerPartyId;
private String facilityId;
private Date introductionDate;
private Date releaseDate;
private Date supportDiscontinuationDate;
private Date salesDiscontinuationDate;
private boolean salesDiscWhenNotAvail;
private String internalName;
private String brandName;
private String comments;
private String productName;
private String description;
private String longDescription;
private String priceDetailText;
private String smallImageUrl;
private String mediumImageUrl;
private String largeImageUrl;
private String detailImageUrl;
private String originalImageUrl;
private String detailScreen;
private String inventoryMessage;
private String inventoryItemTypeId;
private boolean requireInventory;
private String quantityUomId;
private float quantityIncluded;
private int piecesIncluded;
private boolean requireAmount;
private float fixedAmount;
private String amountUomTypeId;
private String weightUomId;
private float shippingWeight;
private float productWeight;
private String heightUomId;
private float productHeight;
private float shippingHeight;
private String widthUomId;
private float productWidth;
private float shippingWidth;
private String depthUomId;
private float productDepth;
private float shippingDepth;
private String diameterUomId;
private float productDiameter;
private float productRating;
private String ratingTypeEnum;
private boolean returnable;
private boolean taxable;
private boolean chargeShipping;
private boolean autoCreateKeywords;
private boolean includeInPromotions;
private boolean isVirtual;
private boolean isVariant;
private String virtualVariantMethodEnum;
private String originGeoId;
private String requirementMethodEnumId;
private int billOfMaterialLevel;
private float reservMaxPersons;
private float reserv2ndPPPerc;
private float reservNthPPPerc;
private String configId;
private Date createdDate;
private String createdByUserLogin;
private Date lastModifiedDate;
private String lastModifiedByUserLogin;
private boolean inShippingBox;
private String defaultShipmentBoxTypeId;
private String lotIdFilledIn;
private boolean orderDecimalQuantity;

public String getProductId() {
	return productId;
}


public void setProductId(String productId) {
	this.productId = productId;
}


public String getProductTypeId() {
	return productTypeId;
}


public void setProductTypeId(String productTypeId) {
	this.productTypeId = productTypeId;
}


public String getPrimaryProductCategoryId() {
	return primaryProductCategoryId;
}


public void setPrimaryProductCategoryId(String primaryProductCategoryId) {
	this.primaryProductCategoryId = primaryProductCategoryId;
}


public String getManufacturerPartyId() {
	return manufacturerPartyId;
}


public void setManufacturerPartyId(String manufacturerPartyId) {
	this.manufacturerPartyId = manufacturerPartyId;
}


public String getFacilityId() {
	return facilityId;
}


public void setFacilityId(String facilityId) {
	this.facilityId = facilityId;
}


public Date getIntroductionDate() {
	return introductionDate;
}


public void setIntroductionDate(Date introductionDate) {
	this.introductionDate = introductionDate;
}


public Date getReleaseDate() {
	return releaseDate;
}


public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
}


public Date getSupportDiscontinuationDate() {
	return supportDiscontinuationDate;
}


public void setSupportDiscontinuationDate(Date supportDiscontinuationDate) {
	this.supportDiscontinuationDate = supportDiscontinuationDate;
}


public Date getSalesDiscontinuationDate() {
	return salesDiscontinuationDate;
}


public void setSalesDiscontinuationDate(Date salesDiscontinuationDate) {
	this.salesDiscontinuationDate = salesDiscontinuationDate;
}


public boolean isSalesDiscWhenNotAvail() {
	return salesDiscWhenNotAvail;
}


public void setSalesDiscWhenNotAvail(boolean salesDiscWhenNotAvail) {
	this.salesDiscWhenNotAvail = salesDiscWhenNotAvail;
}


public String getInternalName() {
	return internalName;
}


public void setInternalName(String internalName) {
	this.internalName = internalName;
}


public String getBrandName() {
	return brandName;
}


public void setBrandName(String brandName) {
	this.brandName = brandName;
}


public String getComments() {
	return comments;
}


public void setComments(String comments) {
	this.comments = comments;
}


public String getProductName() {
	return productName;
}


public void setProductName(String productName) {
	this.productName = productName;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public String getLongDescription() {
	return longDescription;
}


public void setLongDescription(String longDescription) {
	this.longDescription = longDescription;
}


public String getPriceDetailText() {
	return priceDetailText;
}


public void setPriceDetailText(String priceDetailText) {
	this.priceDetailText = priceDetailText;
}


public String getSmallImageUrl() {
	return smallImageUrl;
}


public void setSmallImageUrl(String smallImageUrl) {
	this.smallImageUrl = smallImageUrl;
}


public String getMediumImageUrl() {
	return mediumImageUrl;
}


public void setMediumImageUrl(String mediumImageUrl) {
	this.mediumImageUrl = mediumImageUrl;
}


public String getLargeImageUrl() {
	return largeImageUrl;
}


public void setLargeImageUrl(String largeImageUrl) {
	this.largeImageUrl = largeImageUrl;
}


public String getDetailImageUrl() {
	return detailImageUrl;
}


public void setDetailImageUrl(String detailImageUrl) {
	this.detailImageUrl = detailImageUrl;
}


public String getOriginalImageUrl() {
	return originalImageUrl;
}


public void setOriginalImageUrl(String originalImageUrl) {
	this.originalImageUrl = originalImageUrl;
}


public String getDetailScreen() {
	return detailScreen;
}


public void setDetailScreen(String detailScreen) {
	this.detailScreen = detailScreen;
}


public String getInventoryMessage() {
	return inventoryMessage;
}


public void setInventoryMessage(String inventoryMessage) {
	this.inventoryMessage = inventoryMessage;
}


public String getInventoryItemTypeId() {
	return inventoryItemTypeId;
}


public void setInventoryItemTypeId(String inventoryItemTypeId) {
	this.inventoryItemTypeId = inventoryItemTypeId;
}


public boolean isRequireInventory() {
	return requireInventory;
}


public void setRequireInventory(boolean requireInventory) {
	this.requireInventory = requireInventory;
}


public String getQuantityUomId() {
	return quantityUomId;
}


public void setQuantityUomId(String quantityUomId) {
	this.quantityUomId = quantityUomId;
}


public float getQuantityIncluded() {
	return quantityIncluded;
}


public void setQuantityIncluded(float quantityIncluded) {
	this.quantityIncluded = quantityIncluded;
}


public int getPiecesIncluded() {
	return piecesIncluded;
}


public void setPiecesIncluded(int piecesIncluded) {
	this.piecesIncluded = piecesIncluded;
}


public boolean isRequireAmount() {
	return requireAmount;
}


public void setRequireAmount(boolean requireAmount) {
	this.requireAmount = requireAmount;
}


public float getFixedAmount() {
	return fixedAmount;
}


public void setFixedAmount(float fixedAmount) {
	this.fixedAmount = fixedAmount;
}


public String getAmountUomTypeId() {
	return amountUomTypeId;
}


public void setAmountUomTypeId(String amountUomTypeId) {
	this.amountUomTypeId = amountUomTypeId;
}


public String getWeightUomId() {
	return weightUomId;
}


public void setWeightUomId(String weightUomId) {
	this.weightUomId = weightUomId;
}


public float getShippingWeight() {
	return shippingWeight;
}


public void setShippingWeight(float shippingWeight) {
	this.shippingWeight = shippingWeight;
}


public float getProductWeight() {
	return productWeight;
}


public void setProductWeight(float productWeight) {
	this.productWeight = productWeight;
}


public String getHeightUomId() {
	return heightUomId;
}


public void setHeightUomId(String heightUomId) {
	this.heightUomId = heightUomId;
}


public float getProductHeight() {
	return productHeight;
}


public void setProductHeight(float productHeight) {
	this.productHeight = productHeight;
}


public float getShippingHeight() {
	return shippingHeight;
}


public void setShippingHeight(float shippingHeight) {
	this.shippingHeight = shippingHeight;
}


public String getWidthUomId() {
	return widthUomId;
}


public void setWidthUomId(String widthUomId) {
	this.widthUomId = widthUomId;
}


public float getProductWidth() {
	return productWidth;
}


public void setProductWidth(float productWidth) {
	this.productWidth = productWidth;
}


public float getShippingWidth() {
	return shippingWidth;
}


public void setShippingWidth(float shippingWidth) {
	this.shippingWidth = shippingWidth;
}


public String getDepthUomId() {
	return depthUomId;
}


public void setDepthUomId(String depthUomId) {
	this.depthUomId = depthUomId;
}


public float getProductDepth() {
	return productDepth;
}


public void setProductDepth(float productDepth) {
	this.productDepth = productDepth;
}


public float getShippingDepth() {
	return shippingDepth;
}


public void setShippingDepth(float shippingDepth) {
	this.shippingDepth = shippingDepth;
}


public String getDiameterUomId() {
	return diameterUomId;
}


public void setDiameterUomId(String diameterUomId) {
	this.diameterUomId = diameterUomId;
}


public float getProductDiameter() {
	return productDiameter;
}


public void setProductDiameter(float productDiameter) {
	this.productDiameter = productDiameter;
}


public float getProductRating() {
	return productRating;
}


public void setProductRating(float productRating) {
	this.productRating = productRating;
}


public String getRatingTypeEnum() {
	return ratingTypeEnum;
}


public void setRatingTypeEnum(String ratingTypeEnum) {
	this.ratingTypeEnum = ratingTypeEnum;
}


public boolean isReturnable() {
	return returnable;
}


public void setReturnable(boolean returnable) {
	this.returnable = returnable;
}


public boolean isTaxable() {
	return taxable;
}


public void setTaxable(boolean taxable) {
	this.taxable = taxable;
}


public boolean isChargeShipping() {
	return chargeShipping;
}


public void setChargeShipping(boolean chargeShipping) {
	this.chargeShipping = chargeShipping;
}


public boolean isAutoCreateKeywords() {
	return autoCreateKeywords;
}


public void setAutoCreateKeywords(boolean autoCreateKeywords) {
	this.autoCreateKeywords = autoCreateKeywords;
}


public boolean isIncludeInPromotions() {
	return includeInPromotions;
}


public void setIncludeInPromotions(boolean includeInPromotions) {
	this.includeInPromotions = includeInPromotions;
}


public boolean isVirtual() {
	return isVirtual;
}


public void setVirtual(boolean isVirtual) {
	this.isVirtual = isVirtual;
}


public boolean isVariant() {
	return isVariant;
}


public void setVariant(boolean isVariant) {
	this.isVariant = isVariant;
}


public String getVirtualVariantMethodEnum() {
	return virtualVariantMethodEnum;
}


public void setVirtualVariantMethodEnum(String virtualVariantMethodEnum) {
	this.virtualVariantMethodEnum = virtualVariantMethodEnum;
}


public String getOriginGeoId() {
	return originGeoId;
}


public void setOriginGeoId(String originGeoId) {
	this.originGeoId = originGeoId;
}


public String getRequirementMethodEnumId() {
	return requirementMethodEnumId;
}


public void setRequirementMethodEnumId(String requirementMethodEnumId) {
	this.requirementMethodEnumId = requirementMethodEnumId;
}


public int getBillOfMaterialLevel() {
	return billOfMaterialLevel;
}


public void setBillOfMaterialLevel(int billOfMaterialLevel) {
	this.billOfMaterialLevel = billOfMaterialLevel;
}


public float getReservMaxPersons() {
	return reservMaxPersons;
}


public void setReservMaxPersons(float reservMaxPersons) {
	this.reservMaxPersons = reservMaxPersons;
}


public float getReserv2ndPPPerc() {
	return reserv2ndPPPerc;
}


public void setReserv2ndPPPerc(float reserv2ndPPPerc) {
	this.reserv2ndPPPerc = reserv2ndPPPerc;
}


public float getReservNthPPPerc() {
	return reservNthPPPerc;
}


public void setReservNthPPPerc(float reservNthPPPerc) {
	this.reservNthPPPerc = reservNthPPPerc;
}


public String getConfigId() {
	return configId;
}


public void setConfigId(String configId) {
	this.configId = configId;
}


public Date getCreatedDate() {
	return createdDate;
}


public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}


public String getCreatedByUserLogin() {
	return createdByUserLogin;
}


public void setCreatedByUserLogin(String createdByUserLogin) {
	this.createdByUserLogin = createdByUserLogin;
}


public Date getLastModifiedDate() {
	return lastModifiedDate;
}


public void setLastModifiedDate(Date lastModifiedDate) {
	this.lastModifiedDate = lastModifiedDate;
}


public String getLastModifiedByUserLogin() {
	return lastModifiedByUserLogin;
}


public void setLastModifiedByUserLogin(String lastModifiedByUserLogin) {
	this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public boolean isInShippingBox() {
	return inShippingBox;
}


public void setInShippingBox(boolean inShippingBox) {
	this.inShippingBox = inShippingBox;
}


public String getDefaultShipmentBoxTypeId() {
	return defaultShipmentBoxTypeId;
}


public void setDefaultShipmentBoxTypeId(String defaultShipmentBoxTypeId) {
	this.defaultShipmentBoxTypeId = defaultShipmentBoxTypeId;
}


public String getLotIdFilledIn() {
	return lotIdFilledIn;
}


public void setLotIdFilledIn(String lotIdFilledIn) {
	this.lotIdFilledIn = lotIdFilledIn;
}


public boolean isOrderDecimalQuantity() {
	return orderDecimalQuantity;
}


public void setOrderDecimalQuantity(boolean orderDecimalQuantity) {
	this.orderDecimalQuantity = orderDecimalQuantity;
}


	public Map<String, Object> mapAttributeField(){

		return ProductMapper.map(this);
	}
}

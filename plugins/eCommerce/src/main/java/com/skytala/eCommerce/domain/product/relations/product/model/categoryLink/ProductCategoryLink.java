package com.skytala.eCommerce.domain.product.relations.product.model.categoryLink;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryLink.ProductCategoryLinkMapper;

public class ProductCategoryLink implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryId;
private String linkSeqId;
private Timestamp fromDate;
private Timestamp thruDate;
private String comments;
private Long sequenceNum;
private String titleText;
private String detailText;
private String imageUrl;
private String imageTwoUrl;
private String linkTypeEnumId;
private String linkInfo;
private String detailSubScreen;

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getLinkSeqId() {
return linkSeqId;
}

public void setLinkSeqId(String  linkSeqId) {
this.linkSeqId = linkSeqId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public String getTitleText() {
return titleText;
}

public void setTitleText(String  titleText) {
this.titleText = titleText;
}

public String getDetailText() {
return detailText;
}

public void setDetailText(String  detailText) {
this.detailText = detailText;
}

public String getImageUrl() {
return imageUrl;
}

public void setImageUrl(String  imageUrl) {
this.imageUrl = imageUrl;
}

public String getImageTwoUrl() {
return imageTwoUrl;
}

public void setImageTwoUrl(String  imageTwoUrl) {
this.imageTwoUrl = imageTwoUrl;
}

public String getLinkTypeEnumId() {
return linkTypeEnumId;
}

public void setLinkTypeEnumId(String  linkTypeEnumId) {
this.linkTypeEnumId = linkTypeEnumId;
}

public String getLinkInfo() {
return linkInfo;
}

public void setLinkInfo(String  linkInfo) {
this.linkInfo = linkInfo;
}

public String getDetailSubScreen() {
return detailSubScreen;
}

public void setDetailSubScreen(String  detailSubScreen) {
this.detailSubScreen = detailSubScreen;
}


public Map<String, Object> mapAttributeField() {
return ProductCategoryLinkMapper.map(this);
}
}

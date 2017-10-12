package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.mapper.WebSitePublishPointMapper;

public class WebSitePublishPoint implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String templateTitle;
private String styleSheetFile;
private String logo;
private String medallionLogo;
private String lineLogo;
private String leftBarId;
private String rightBarId;
private String contentDept;
private String aboutContentId;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getTemplateTitle() {
return templateTitle;
}

public void setTemplateTitle(String  templateTitle) {
this.templateTitle = templateTitle;
}

public String getStyleSheetFile() {
return styleSheetFile;
}

public void setStyleSheetFile(String  styleSheetFile) {
this.styleSheetFile = styleSheetFile;
}

public String getLogo() {
return logo;
}

public void setLogo(String  logo) {
this.logo = logo;
}

public String getMedallionLogo() {
return medallionLogo;
}

public void setMedallionLogo(String  medallionLogo) {
this.medallionLogo = medallionLogo;
}

public String getLineLogo() {
return lineLogo;
}

public void setLineLogo(String  lineLogo) {
this.lineLogo = lineLogo;
}

public String getLeftBarId() {
return leftBarId;
}

public void setLeftBarId(String  leftBarId) {
this.leftBarId = leftBarId;
}

public String getRightBarId() {
return rightBarId;
}

public void setRightBarId(String  rightBarId) {
this.rightBarId = rightBarId;
}

public String getContentDept() {
return contentDept;
}

public void setContentDept(String  contentDept) {
this.contentDept = contentDept;
}

public String getAboutContentId() {
return aboutContentId;
}

public void setAboutContentId(String  aboutContentId) {
this.aboutContentId = aboutContentId;
}


public Map<String, Object> mapAttributeField() {
return WebSitePublishPointMapper.map(this);
}
}

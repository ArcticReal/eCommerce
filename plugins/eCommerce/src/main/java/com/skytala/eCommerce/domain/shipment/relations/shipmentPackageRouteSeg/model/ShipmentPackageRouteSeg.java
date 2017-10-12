package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.mapper.ShipmentPackageRouteSegMapper;

public class ShipmentPackageRouteSeg implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentPackageSeqId;
private String shipmentRouteSegmentId;
private String trackingCode;
private String boxNumber;
private byte[] labelImage;
private byte[] labelIntlSignImage;
private String labelHtml;
private Boolean labelPrinted;
private byte[] internationalInvoice;
private BigDecimal packageTransportCost;
private BigDecimal packageServiceCost;
private BigDecimal packageOtherCost;
private BigDecimal codAmount;
private BigDecimal insuredAmount;
private String currencyUomId;

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getShipmentPackageSeqId() {
return shipmentPackageSeqId;
}

public void setShipmentPackageSeqId(String  shipmentPackageSeqId) {
this.shipmentPackageSeqId = shipmentPackageSeqId;
}

public String getShipmentRouteSegmentId() {
return shipmentRouteSegmentId;
}

public void setShipmentRouteSegmentId(String  shipmentRouteSegmentId) {
this.shipmentRouteSegmentId = shipmentRouteSegmentId;
}

public String getTrackingCode() {
return trackingCode;
}

public void setTrackingCode(String  trackingCode) {
this.trackingCode = trackingCode;
}

public String getBoxNumber() {
return boxNumber;
}

public void setBoxNumber(String  boxNumber) {
this.boxNumber = boxNumber;
}

public byte[] getLabelImage() {
return labelImage;
}

public void setLabelImage(byte[]  labelImage) {
this.labelImage = labelImage;
}

public byte[] getLabelIntlSignImage() {
return labelIntlSignImage;
}

public void setLabelIntlSignImage(byte[]  labelIntlSignImage) {
this.labelIntlSignImage = labelIntlSignImage;
}

public String getLabelHtml() {
return labelHtml;
}

public void setLabelHtml(String  labelHtml) {
this.labelHtml = labelHtml;
}

public Boolean getLabelPrinted() {
return labelPrinted;
}

public void setLabelPrinted(Boolean  labelPrinted) {
this.labelPrinted = labelPrinted;
}

public byte[] getInternationalInvoice() {
return internationalInvoice;
}

public void setInternationalInvoice(byte[]  internationalInvoice) {
this.internationalInvoice = internationalInvoice;
}

public BigDecimal getPackageTransportCost() {
return packageTransportCost;
}

public void setPackageTransportCost(BigDecimal  packageTransportCost) {
this.packageTransportCost = packageTransportCost;
}

public BigDecimal getPackageServiceCost() {
return packageServiceCost;
}

public void setPackageServiceCost(BigDecimal  packageServiceCost) {
this.packageServiceCost = packageServiceCost;
}

public BigDecimal getPackageOtherCost() {
return packageOtherCost;
}

public void setPackageOtherCost(BigDecimal  packageOtherCost) {
this.packageOtherCost = packageOtherCost;
}

public BigDecimal getCodAmount() {
return codAmount;
}

public void setCodAmount(BigDecimal  codAmount) {
this.codAmount = codAmount;
}

public BigDecimal getInsuredAmount() {
return insuredAmount;
}

public void setInsuredAmount(BigDecimal  insuredAmount) {
this.insuredAmount = insuredAmount;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}


public Map<String, Object> mapAttributeField() {
return ShipmentPackageRouteSegMapper.map(this);
}
}

package com.skytala.eCommerce.domain.order.relations.shoppingList.model.itemSurvey;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.itemSurvey.ShoppingListItemSurveyMapper;

public class ShoppingListItemSurvey implements Serializable{

private static final long serialVersionUID = 1L;
private String shoppingListId;
private String shoppingListItemSeqId;
private String surveyResponseId;

public String getShoppingListId() {
return shoppingListId;
}

public void setShoppingListId(String  shoppingListId) {
this.shoppingListId = shoppingListId;
}

public String getShoppingListItemSeqId() {
return shoppingListItemSeqId;
}

public void setShoppingListItemSeqId(String  shoppingListItemSeqId) {
this.shoppingListItemSeqId = shoppingListItemSeqId;
}

public String getSurveyResponseId() {
return surveyResponseId;
}

public void setSurveyResponseId(String  surveyResponseId) {
this.surveyResponseId = surveyResponseId;
}


public Map<String, Object> mapAttributeField() {
return ShoppingListItemSurveyMapper.map(this);
}
}

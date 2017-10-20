package com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.workEffort.ShoppingListWorkEffortMapper;

public class ShoppingListWorkEffort implements Serializable{

private static final long serialVersionUID = 1L;
private String shoppingListId;
private String workEffortId;

public String getShoppingListId() {
return shoppingListId;
}

public void setShoppingListId(String  shoppingListId) {
this.shoppingListId = shoppingListId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}


public Map<String, Object> mapAttributeField() {
return ShoppingListWorkEffortMapper.map(this);
}
}

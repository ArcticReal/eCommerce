package com.skytala.eCommerce.domain.shoppingListType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shoppingListType.mapper.ShoppingListTypeMapper;

public class ShoppingListType implements Serializable{

private static final long serialVersionUID = 1L;
private String shoppingListTypeId;
private String description;

public String getShoppingListTypeId() {
return shoppingListTypeId;
}

public void setShoppingListTypeId(String  shoppingListTypeId) {
this.shoppingListTypeId = shoppingListTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ShoppingListTypeMapper.map(this);
}
}

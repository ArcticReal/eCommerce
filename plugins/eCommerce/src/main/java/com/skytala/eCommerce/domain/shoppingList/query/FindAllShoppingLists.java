
package com.skytala.eCommerce.domain.shoppingList.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shoppingList.event.ShoppingListFound;
import com.skytala.eCommerce.domain.shoppingList.mapper.ShoppingListMapper;
import com.skytala.eCommerce.domain.shoppingList.model.ShoppingList;


public class FindAllShoppingLists extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShoppingList> returnVal = new ArrayList<ShoppingList>();
try{
List<GenericValue> results = delegator.findAll("ShoppingList", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShoppingListMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShoppingListFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

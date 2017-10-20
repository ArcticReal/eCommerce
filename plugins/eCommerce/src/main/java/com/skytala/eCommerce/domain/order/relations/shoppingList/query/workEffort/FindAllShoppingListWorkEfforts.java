
package com.skytala.eCommerce.domain.order.relations.shoppingList.query.workEffort;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort.ShoppingListWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.workEffort.ShoppingListWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort.ShoppingListWorkEffort;


public class FindAllShoppingListWorkEfforts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShoppingListWorkEffort> returnVal = new ArrayList<ShoppingListWorkEffort>();
try{
List<GenericValue> results = delegator.findAll("ShoppingListWorkEffort", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShoppingListWorkEffortMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShoppingListWorkEffortFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

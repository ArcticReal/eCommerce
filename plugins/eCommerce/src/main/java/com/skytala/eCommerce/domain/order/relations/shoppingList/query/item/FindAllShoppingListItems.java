
package com.skytala.eCommerce.domain.order.relations.shoppingList.query.item;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.item.ShoppingListItemFound;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.item.ShoppingListItemMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.item.ShoppingListItem;


public class FindAllShoppingListItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShoppingListItem> returnVal = new ArrayList<ShoppingListItem>();
try{
List<GenericValue> results = delegator.findAll("ShoppingListItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShoppingListItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShoppingListItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

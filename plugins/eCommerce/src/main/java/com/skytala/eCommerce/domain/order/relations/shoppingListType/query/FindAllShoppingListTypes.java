
package com.skytala.eCommerce.domain.order.relations.shoppingListType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.shoppingListType.event.ShoppingListTypeFound;
import com.skytala.eCommerce.domain.order.relations.shoppingListType.mapper.ShoppingListTypeMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingListType.model.ShoppingListType;


public class FindAllShoppingListTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShoppingListType> returnVal = new ArrayList<ShoppingListType>();
try{
List<GenericValue> results = delegator.findAll("ShoppingListType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShoppingListTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShoppingListTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.order.relations.shoppingList.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.ShoppingListAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.ShoppingListMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.ShoppingList;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShoppingList extends Command {

private ShoppingList elementToBeAdded;
public AddShoppingList(ShoppingList elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShoppingList addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShoppingListId(delegator.getNextSeqId("ShoppingList"));
GenericValue newValue = delegator.makeValue("ShoppingList", elementToBeAdded.mapAttributeField());
addedElement = ShoppingListMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShoppingListAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

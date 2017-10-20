package com.skytala.eCommerce.domain.order.relations.shoppingList.command.workEffort;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort.ShoppingListWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.workEffort.ShoppingListWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort.ShoppingListWorkEffort;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShoppingListWorkEffort extends Command {

private ShoppingListWorkEffort elementToBeAdded;
public AddShoppingListWorkEffort(ShoppingListWorkEffort elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShoppingListWorkEffort addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShoppingListWorkEffort", elementToBeAdded.mapAttributeField());
addedElement = ShoppingListWorkEffortMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShoppingListWorkEffortAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

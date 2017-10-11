package com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.event.ShoppingListWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.model.ShoppingListWorkEffort;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShoppingListWorkEffort extends Command {

private ShoppingListWorkEffort elementToBeUpdated;

public UpdateShoppingListWorkEffort(ShoppingListWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShoppingListWorkEffort getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShoppingListWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShoppingListWorkEffort", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShoppingListWorkEffort.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShoppingListWorkEffort.class);
}
success = false;
}
Event resultingEvent = new ShoppingListWorkEffortUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

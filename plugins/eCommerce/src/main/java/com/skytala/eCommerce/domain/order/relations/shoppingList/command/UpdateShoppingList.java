package com.skytala.eCommerce.domain.order.relations.shoppingList.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.ShoppingListUpdated;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.ShoppingList;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShoppingList extends Command {

private ShoppingList elementToBeUpdated;

public UpdateShoppingList(ShoppingList elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShoppingList getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShoppingList elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShoppingList", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShoppingList.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShoppingList.class);
}
success = false;
}
Event resultingEvent = new ShoppingListUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

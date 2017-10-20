package com.skytala.eCommerce.domain.order.relations.shoppingList.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.type.ShoppingListTypeAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.type.ShoppingListTypeMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.type.ShoppingListType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShoppingListType extends Command {

private ShoppingListType elementToBeAdded;
public AddShoppingListType(ShoppingListType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShoppingListType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShoppingListTypeId(delegator.getNextSeqId("ShoppingListType"));
GenericValue newValue = delegator.makeValue("ShoppingListType", elementToBeAdded.mapAttributeField());
addedElement = ShoppingListTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShoppingListTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

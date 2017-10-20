package com.skytala.eCommerce.domain.order.relations.shoppingList.command.item;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.item.ShoppingListItemAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.item.ShoppingListItemMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.item.ShoppingListItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShoppingListItem extends Command {

private ShoppingListItem elementToBeAdded;
public AddShoppingListItem(ShoppingListItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShoppingListItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShoppingListItemSeqId(delegator.getNextSeqId("ShoppingListItem"));
GenericValue newValue = delegator.makeValue("ShoppingListItem", elementToBeAdded.mapAttributeField());
addedElement = ShoppingListItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShoppingListItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

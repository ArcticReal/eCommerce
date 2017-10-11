package com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.event.ShoppingListItemSurveyAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.mapper.ShoppingListItemSurveyMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.model.ShoppingListItemSurvey;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShoppingListItemSurvey extends Command {

private ShoppingListItemSurvey elementToBeAdded;
public AddShoppingListItemSurvey(ShoppingListItemSurvey elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShoppingListItemSurvey addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShoppingListItemSeqId(delegator.getNextSeqId("ShoppingListItemSurvey"));
GenericValue newValue = delegator.makeValue("ShoppingListItemSurvey", elementToBeAdded.mapAttributeField());
addedElement = ShoppingListItemSurveyMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShoppingListItemSurveyAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

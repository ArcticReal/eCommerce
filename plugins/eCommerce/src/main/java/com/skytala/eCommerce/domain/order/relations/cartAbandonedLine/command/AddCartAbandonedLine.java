package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event.CartAbandonedLineAdded;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.mapper.CartAbandonedLineMapper;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model.CartAbandonedLine;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCartAbandonedLine extends Command {

private CartAbandonedLine elementToBeAdded;
public AddCartAbandonedLine(CartAbandonedLine elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CartAbandonedLine addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CartAbandonedLine", elementToBeAdded.mapAttributeField());
addedElement = CartAbandonedLineMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CartAbandonedLineAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

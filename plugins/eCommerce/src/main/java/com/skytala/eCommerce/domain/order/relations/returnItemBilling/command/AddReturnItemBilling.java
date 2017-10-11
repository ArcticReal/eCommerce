package com.skytala.eCommerce.domain.order.relations.returnItemBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItemBilling.event.ReturnItemBillingAdded;
import com.skytala.eCommerce.domain.order.relations.returnItemBilling.mapper.ReturnItemBillingMapper;
import com.skytala.eCommerce.domain.order.relations.returnItemBilling.model.ReturnItemBilling;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnItemBilling extends Command {

private ReturnItemBilling elementToBeAdded;
public AddReturnItemBilling(ReturnItemBilling elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnItemBilling addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ReturnItemBilling", elementToBeAdded.mapAttributeField());
addedElement = ReturnItemBillingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnItemBillingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

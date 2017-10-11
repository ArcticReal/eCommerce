package com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.event.OrderRequirementCommitmentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.model.OrderRequirementCommitment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderRequirementCommitment extends Command {

private OrderRequirementCommitment elementToBeUpdated;

public UpdateOrderRequirementCommitment(OrderRequirementCommitment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderRequirementCommitment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderRequirementCommitment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderRequirementCommitment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderRequirementCommitment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderRequirementCommitment.class);
}
success = false;
}
Event resultingEvent = new OrderRequirementCommitmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

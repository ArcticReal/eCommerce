package com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.event.OrderRequirementCommitmentAdded;
import com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.mapper.OrderRequirementCommitmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.model.OrderRequirementCommitment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderRequirementCommitment extends Command {

private OrderRequirementCommitment elementToBeAdded;
public AddOrderRequirementCommitment(OrderRequirementCommitment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderRequirementCommitment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemSeqId(delegator.getNextSeqId("OrderRequirementCommitment"));
GenericValue newValue = delegator.makeValue("OrderRequirementCommitment", elementToBeAdded.mapAttributeField());
addedElement = OrderRequirementCommitmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderRequirementCommitmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

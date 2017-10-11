package com.skytala.eCommerce.domain.order.relations.returnReason.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonAdded;
import com.skytala.eCommerce.domain.order.relations.returnReason.mapper.ReturnReasonMapper;
import com.skytala.eCommerce.domain.order.relations.returnReason.model.ReturnReason;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnReason extends Command {

private ReturnReason elementToBeAdded;
public AddReturnReason(ReturnReason elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnReason addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnReasonId(delegator.getNextSeqId("ReturnReason"));
GenericValue newValue = delegator.makeValue("ReturnReason", elementToBeAdded.mapAttributeField());
addedElement = ReturnReasonMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnReasonAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

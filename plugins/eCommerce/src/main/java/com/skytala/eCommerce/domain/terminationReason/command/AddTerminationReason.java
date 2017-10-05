package com.skytala.eCommerce.domain.terminationReason.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.terminationReason.event.TerminationReasonAdded;
import com.skytala.eCommerce.domain.terminationReason.mapper.TerminationReasonMapper;
import com.skytala.eCommerce.domain.terminationReason.model.TerminationReason;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTerminationReason extends Command {

private TerminationReason elementToBeAdded;
public AddTerminationReason(TerminationReason elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TerminationReason addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTerminationReasonId(delegator.getNextSeqId("TerminationReason"));
GenericValue newValue = delegator.makeValue("TerminationReason", elementToBeAdded.mapAttributeField());
addedElement = TerminationReasonMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TerminationReasonAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

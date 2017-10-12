package com.skytala.eCommerce.domain.shipment.relations.rejectionReason.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonAdded;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.mapper.RejectionReasonMapper;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRejectionReason extends Command {

private RejectionReason elementToBeAdded;
public AddRejectionReason(RejectionReason elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RejectionReason addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRejectionId(delegator.getNextSeqId("RejectionReason"));
GenericValue newValue = delegator.makeValue("RejectionReason", elementToBeAdded.mapAttributeField());
addedElement = RejectionReasonMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RejectionReasonAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

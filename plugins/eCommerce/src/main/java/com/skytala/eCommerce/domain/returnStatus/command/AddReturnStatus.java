package com.skytala.eCommerce.domain.returnStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnStatus.event.ReturnStatusAdded;
import com.skytala.eCommerce.domain.returnStatus.mapper.ReturnStatusMapper;
import com.skytala.eCommerce.domain.returnStatus.model.ReturnStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnStatus extends Command {

private ReturnStatus elementToBeAdded;
public AddReturnStatus(ReturnStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnStatus addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnStatusId(delegator.getNextSeqId("ReturnStatus"));
GenericValue newValue = delegator.makeValue("ReturnStatus", elementToBeAdded.mapAttributeField());
addedElement = ReturnStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

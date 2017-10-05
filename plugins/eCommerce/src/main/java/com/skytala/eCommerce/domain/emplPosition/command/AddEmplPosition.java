package com.skytala.eCommerce.domain.emplPosition.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.emplPosition.event.EmplPositionAdded;
import com.skytala.eCommerce.domain.emplPosition.mapper.EmplPositionMapper;
import com.skytala.eCommerce.domain.emplPosition.model.EmplPosition;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplPosition extends Command {

private EmplPosition elementToBeAdded;
public AddEmplPosition(EmplPosition elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplPosition addedElement = null;
boolean success = false;
try {
elementToBeAdded.setEmplPositionId(delegator.getNextSeqId("EmplPosition"));
GenericValue newValue = delegator.makeValue("EmplPosition", elementToBeAdded.mapAttributeField());
addedElement = EmplPositionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplPositionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

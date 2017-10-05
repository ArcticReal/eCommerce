package com.skytala.eCommerce.domain.container.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.container.event.ContainerAdded;
import com.skytala.eCommerce.domain.container.mapper.ContainerMapper;
import com.skytala.eCommerce.domain.container.model.Container;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContainer extends Command {

private Container elementToBeAdded;
public AddContainer(Container elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Container addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContainerId(delegator.getNextSeqId("Container"));
GenericValue newValue = delegator.makeValue("Container", elementToBeAdded.mapAttributeField());
addedElement = ContainerMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContainerAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

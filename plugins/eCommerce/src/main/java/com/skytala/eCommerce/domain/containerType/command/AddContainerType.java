package com.skytala.eCommerce.domain.containerType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.containerType.event.ContainerTypeAdded;
import com.skytala.eCommerce.domain.containerType.mapper.ContainerTypeMapper;
import com.skytala.eCommerce.domain.containerType.model.ContainerType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContainerType extends Command {

private ContainerType elementToBeAdded;
public AddContainerType(ContainerType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContainerType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContainerTypeId(delegator.getNextSeqId("ContainerType"));
GenericValue newValue = delegator.makeValue("ContainerType", elementToBeAdded.mapAttributeField());
addedElement = ContainerTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContainerTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

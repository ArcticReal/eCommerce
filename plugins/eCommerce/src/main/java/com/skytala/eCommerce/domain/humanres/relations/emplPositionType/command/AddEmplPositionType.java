package com.skytala.eCommerce.domain.humanres.relations.emplPositionType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.event.EmplPositionTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.mapper.EmplPositionTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.model.EmplPositionType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplPositionType extends Command {

private EmplPositionType elementToBeAdded;
public AddEmplPositionType(EmplPositionType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplPositionType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setEmplPositionTypeId(delegator.getNextSeqId("EmplPositionType"));
GenericValue newValue = delegator.makeValue("EmplPositionType", elementToBeAdded.mapAttributeField());
addedElement = EmplPositionTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplPositionTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

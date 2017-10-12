package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.event.EmplPositionTypeClassAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.mapper.EmplPositionTypeClassMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.model.EmplPositionTypeClass;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplPositionTypeClass extends Command {

private EmplPositionTypeClass elementToBeAdded;
public AddEmplPositionTypeClass(EmplPositionTypeClass elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplPositionTypeClass addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("EmplPositionTypeClass", elementToBeAdded.mapAttributeField());
addedElement = EmplPositionTypeClassMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplPositionTypeClassAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

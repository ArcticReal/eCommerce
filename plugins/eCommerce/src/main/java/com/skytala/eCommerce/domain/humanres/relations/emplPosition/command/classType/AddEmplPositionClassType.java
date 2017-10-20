package com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.classType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.classType.EmplPositionClassTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.classType.EmplPositionClassType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplPositionClassType extends Command {

private EmplPositionClassType elementToBeAdded;
public AddEmplPositionClassType(EmplPositionClassType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplPositionClassType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setEmplPositionClassTypeId(delegator.getNextSeqId("EmplPositionClassType"));
GenericValue newValue = delegator.makeValue("EmplPositionClassType", elementToBeAdded.mapAttributeField());
addedElement = EmplPositionClassTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplPositionClassTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

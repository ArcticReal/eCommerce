package com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeRate;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeRate.EmplPositionTypeRateAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.typeRate.EmplPositionTypeRateMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeRate.EmplPositionTypeRate;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplPositionTypeRate extends Command {

private EmplPositionTypeRate elementToBeAdded;
public AddEmplPositionTypeRate(EmplPositionTypeRate elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplPositionTypeRate addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("EmplPositionTypeRate", elementToBeAdded.mapAttributeField());
addedElement = EmplPositionTypeRateMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplPositionTypeRateAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

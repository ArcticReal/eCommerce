package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event.ValidResponsibilityAdded;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.mapper.ValidResponsibilityMapper;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddValidResponsibility extends Command {

private ValidResponsibility elementToBeAdded;
public AddValidResponsibility(ValidResponsibility elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ValidResponsibility addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ValidResponsibility", elementToBeAdded.mapAttributeField());
addedElement = ValidResponsibilityMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ValidResponsibilityAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

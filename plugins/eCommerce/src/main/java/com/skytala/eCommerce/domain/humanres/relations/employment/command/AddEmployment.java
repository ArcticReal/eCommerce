package com.skytala.eCommerce.domain.humanres.relations.employment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.EmploymentAdded;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.EmploymentMapper;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmployment extends Command {

private Employment elementToBeAdded;
public AddEmployment(Employment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Employment addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("Employment", elementToBeAdded.mapAttributeField());
addedElement = EmploymentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmploymentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

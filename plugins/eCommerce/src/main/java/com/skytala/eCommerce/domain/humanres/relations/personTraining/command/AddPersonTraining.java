package com.skytala.eCommerce.domain.humanres.relations.personTraining.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.event.PersonTrainingAdded;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.mapper.PersonTrainingMapper;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPersonTraining extends Command {

private PersonTraining elementToBeAdded;
public AddPersonTraining(PersonTraining elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PersonTraining addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PersonTraining", elementToBeAdded.mapAttributeField());
addedElement = PersonTrainingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PersonTrainingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

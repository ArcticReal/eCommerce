package com.skytala.eCommerce.domain.humanres.relations.personTraining.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.event.PersonTrainingUpdated;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePersonTraining extends Command {

private PersonTraining elementToBeUpdated;

public UpdatePersonTraining(PersonTraining elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PersonTraining getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PersonTraining elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PersonTraining", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PersonTraining.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PersonTraining.class);
}
success = false;
}
Event resultingEvent = new PersonTrainingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

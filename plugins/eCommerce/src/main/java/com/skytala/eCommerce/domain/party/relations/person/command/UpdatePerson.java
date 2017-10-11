package com.skytala.eCommerce.domain.party.relations.person.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.person.event.PersonUpdated;
import com.skytala.eCommerce.domain.party.relations.person.model.Person;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePerson extends Command {

private Person elementToBeUpdated;

public UpdatePerson(Person elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Person getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Person elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Person", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Person.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Person.class);
}
success = false;
}
Event resultingEvent = new PersonUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

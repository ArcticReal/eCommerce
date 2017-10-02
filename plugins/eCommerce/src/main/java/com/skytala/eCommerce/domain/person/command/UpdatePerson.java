package com.skytala.eCommerce.domain.person.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.person.event.PersonUpdated;
import com.skytala.eCommerce.domain.person.model.Person;
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
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Person", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
success = false;
}
Broker.instance().publish(new PersonUpdated(success));
return null;
}
}

package com.skytala.eCommerce.domain.party.relations.person.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.person.event.PersonAdded;
import com.skytala.eCommerce.domain.party.relations.person.mapper.PersonMapper;
import com.skytala.eCommerce.domain.party.relations.person.model.Person;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPerson extends Command {

private Person elementToBeAdded;
public AddPerson(Person elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Person addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("Person", elementToBeAdded.mapAttributeField());
addedElement = PersonMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PersonAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

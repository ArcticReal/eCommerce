package com.skytala.eCommerce.domain.party.relations.contactMech.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.ContactMechAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.ContactMechMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactMech extends Command {

private ContactMech elementToBeAdded;
public AddContactMech(ContactMech elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactMech addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContactMechId(delegator.getNextSeqId("ContactMech"));
GenericValue newValue = delegator.makeValue("ContactMech", elementToBeAdded.mapAttributeField());
addedElement = ContactMechMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactMechAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

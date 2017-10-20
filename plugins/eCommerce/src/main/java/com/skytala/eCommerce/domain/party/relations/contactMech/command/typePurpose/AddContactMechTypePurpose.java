package com.skytala.eCommerce.domain.party.relations.contactMech.command.typePurpose;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose.ContactMechTypePurposeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.typePurpose.ContactMechTypePurposeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.typePurpose.ContactMechTypePurpose;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactMechTypePurpose extends Command {

private ContactMechTypePurpose elementToBeAdded;
public AddContactMechTypePurpose(ContactMechTypePurpose elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactMechTypePurpose addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContactMechTypePurpose", elementToBeAdded.mapAttributeField());
addedElement = ContactMechTypePurposeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactMechTypePurposeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

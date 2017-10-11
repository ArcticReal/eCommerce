package com.skytala.eCommerce.domain.party.relations.postalAddress.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.PostalAddressUpdated;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePostalAddress extends Command {

private PostalAddress elementToBeUpdated;

public UpdatePostalAddress(PostalAddress elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PostalAddress getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PostalAddress elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PostalAddress", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PostalAddress.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PostalAddress.class);
}
success = false;
}
Event resultingEvent = new PostalAddressUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

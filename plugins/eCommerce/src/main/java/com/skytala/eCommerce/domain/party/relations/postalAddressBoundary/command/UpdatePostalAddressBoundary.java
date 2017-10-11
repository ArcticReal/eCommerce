package com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.event.PostalAddressBoundaryUpdated;
import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.model.PostalAddressBoundary;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePostalAddressBoundary extends Command {

private PostalAddressBoundary elementToBeUpdated;

public UpdatePostalAddressBoundary(PostalAddressBoundary elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PostalAddressBoundary getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PostalAddressBoundary elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PostalAddressBoundary", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PostalAddressBoundary.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PostalAddressBoundary.class);
}
success = false;
}
Event resultingEvent = new PostalAddressBoundaryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

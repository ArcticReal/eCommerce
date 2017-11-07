package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event.X509IssuerProvisionUpdated;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model.X509IssuerProvision;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateX509IssuerProvision extends Command {

private X509IssuerProvision elementToBeUpdated;

public UpdateX509IssuerProvision(X509IssuerProvision elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public X509IssuerProvision getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(X509IssuerProvision elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("X509IssuerProvision", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(X509IssuerProvision.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(X509IssuerProvision.class);
}
success = false;
}
Event resultingEvent = new X509IssuerProvisionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

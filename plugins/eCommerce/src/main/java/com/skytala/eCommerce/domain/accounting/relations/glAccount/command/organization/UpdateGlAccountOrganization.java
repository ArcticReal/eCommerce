package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.organization;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization.GlAccountOrganizationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.organization.GlAccountOrganization;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlAccountOrganization extends Command {

private GlAccountOrganization elementToBeUpdated;

public UpdateGlAccountOrganization(GlAccountOrganization elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlAccountOrganization getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlAccountOrganization elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlAccountOrganization", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlAccountOrganization.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlAccountOrganization.class);
}
success = false;
}
Event resultingEvent = new GlAccountOrganizationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

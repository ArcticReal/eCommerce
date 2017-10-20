package com.skytala.eCommerce.domain.party.relations.agreement.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.role.AgreementRoleUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.role.AgreementRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementRole extends Command {

private AgreementRole elementToBeUpdated;

public UpdateAgreementRole(AgreementRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementRole.class);
}
success = false;
}
Event resultingEvent = new AgreementRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

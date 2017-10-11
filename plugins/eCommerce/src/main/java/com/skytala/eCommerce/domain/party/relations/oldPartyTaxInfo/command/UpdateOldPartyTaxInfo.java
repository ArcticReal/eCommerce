package com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.event.OldPartyTaxInfoUpdated;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.model.OldPartyTaxInfo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOldPartyTaxInfo extends Command {

private OldPartyTaxInfo elementToBeUpdated;

public UpdateOldPartyTaxInfo(OldPartyTaxInfo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OldPartyTaxInfo getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OldPartyTaxInfo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OldPartyTaxInfo", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OldPartyTaxInfo.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OldPartyTaxInfo.class);
}
success = false;
}
Event resultingEvent = new OldPartyTaxInfoUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

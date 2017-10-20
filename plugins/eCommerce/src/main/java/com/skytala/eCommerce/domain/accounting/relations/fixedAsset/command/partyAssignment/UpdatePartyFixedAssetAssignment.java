package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.partyAssignment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyFixedAssetAssignment extends Command {

private PartyFixedAssetAssignment elementToBeUpdated;

public UpdatePartyFixedAssetAssignment(PartyFixedAssetAssignment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyFixedAssetAssignment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyFixedAssetAssignment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyFixedAssetAssignment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyFixedAssetAssignment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyFixedAssetAssignment.class);
}
success = false;
}
Event resultingEvent = new PartyFixedAssetAssignmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

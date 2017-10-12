package com.skytala.eCommerce.domain.humanres.relations.partySkill.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.event.PartySkillUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.model.PartySkill;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartySkill extends Command {

private PartySkill elementToBeUpdated;

public UpdatePartySkill(PartySkill elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartySkill getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartySkill elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartySkill", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartySkill.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartySkill.class);
}
success = false;
}
Event resultingEvent = new PartySkillUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.party.relations.agreement.command.itemTypeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr.AgreementItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.itemTypeAttr.AgreementItemTypeAttr;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementItemTypeAttr extends Command {

private AgreementItemTypeAttr elementToBeUpdated;

public UpdateAgreementItemTypeAttr(AgreementItemTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementItemTypeAttr getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementItemTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementItemTypeAttr", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementItemTypeAttr.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementItemTypeAttr.class);
}
success = false;
}
Event resultingEvent = new AgreementItemTypeAttrUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

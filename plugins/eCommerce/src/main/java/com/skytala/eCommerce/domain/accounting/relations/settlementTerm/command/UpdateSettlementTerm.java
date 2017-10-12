package com.skytala.eCommerce.domain.accounting.relations.settlementTerm.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.event.SettlementTermUpdated;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.model.SettlementTerm;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSettlementTerm extends Command {

private SettlementTerm elementToBeUpdated;

public UpdateSettlementTerm(SettlementTerm elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SettlementTerm getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SettlementTerm elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SettlementTerm", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SettlementTerm.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SettlementTerm.class);
}
success = false;
}
Event resultingEvent = new SettlementTermUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

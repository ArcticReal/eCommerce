package com.skytala.eCommerce.domain.product.relations.lot.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.lot.event.LotUpdated;
import com.skytala.eCommerce.domain.product.relations.lot.model.Lot;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateLot extends Command {

private Lot elementToBeUpdated;

public UpdateLot(Lot elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Lot getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Lot elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Lot", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Lot.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Lot.class);
}
success = false;
}
Event resultingEvent = new LotUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

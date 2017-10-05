package com.skytala.eCommerce.domain.itemIssuance.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.itemIssuance.event.ItemIssuanceUpdated;
import com.skytala.eCommerce.domain.itemIssuance.model.ItemIssuance;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateItemIssuance extends Command {

private ItemIssuance elementToBeUpdated;

public UpdateItemIssuance(ItemIssuance elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ItemIssuance getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ItemIssuance elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ItemIssuance", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ItemIssuance.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ItemIssuance.class);
}
success = false;
}
Event resultingEvent = new ItemIssuanceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

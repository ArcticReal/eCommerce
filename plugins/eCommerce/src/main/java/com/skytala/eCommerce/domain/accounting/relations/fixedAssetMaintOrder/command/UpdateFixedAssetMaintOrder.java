package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.event.FixedAssetMaintOrderUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.model.FixedAssetMaintOrder;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFixedAssetMaintOrder extends Command {

private FixedAssetMaintOrder elementToBeUpdated;

public UpdateFixedAssetMaintOrder(FixedAssetMaintOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FixedAssetMaintOrder getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FixedAssetMaintOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FixedAssetMaintOrder", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FixedAssetMaintOrder.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FixedAssetMaintOrder.class);
}
success = false;
}
Event resultingEvent = new FixedAssetMaintOrderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

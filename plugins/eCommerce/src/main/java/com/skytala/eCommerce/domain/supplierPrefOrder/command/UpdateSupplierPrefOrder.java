package com.skytala.eCommerce.domain.supplierPrefOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.supplierPrefOrder.event.SupplierPrefOrderUpdated;
import com.skytala.eCommerce.domain.supplierPrefOrder.model.SupplierPrefOrder;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSupplierPrefOrder extends Command {

private SupplierPrefOrder elementToBeUpdated;

public UpdateSupplierPrefOrder(SupplierPrefOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SupplierPrefOrder getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SupplierPrefOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SupplierPrefOrder", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SupplierPrefOrder.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SupplierPrefOrder.class);
}
success = false;
}
Event resultingEvent = new SupplierPrefOrderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

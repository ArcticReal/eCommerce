package com.skytala.eCommerce.domain.supplierProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.supplierProduct.event.SupplierProductUpdated;
import com.skytala.eCommerce.domain.supplierProduct.model.SupplierProduct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSupplierProduct extends Command {

private SupplierProduct elementToBeUpdated;

public UpdateSupplierProduct(SupplierProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SupplierProduct getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SupplierProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SupplierProduct", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SupplierProduct.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SupplierProduct.class);
}
success = false;
}
Event resultingEvent = new SupplierProductUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

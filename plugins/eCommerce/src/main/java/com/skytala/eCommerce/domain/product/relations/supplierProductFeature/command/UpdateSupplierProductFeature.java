package com.skytala.eCommerce.domain.product.relations.supplierProductFeature.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.event.SupplierProductFeatureUpdated;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.model.SupplierProductFeature;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSupplierProductFeature extends Command {

private SupplierProductFeature elementToBeUpdated;

public UpdateSupplierProductFeature(SupplierProductFeature elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SupplierProductFeature getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SupplierProductFeature elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SupplierProductFeature", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SupplierProductFeature.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SupplierProductFeature.class);
}
success = false;
}
Event resultingEvent = new SupplierProductFeatureUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

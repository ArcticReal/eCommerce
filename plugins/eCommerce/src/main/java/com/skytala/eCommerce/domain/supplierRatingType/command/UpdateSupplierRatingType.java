package com.skytala.eCommerce.domain.supplierRatingType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.supplierRatingType.event.SupplierRatingTypeUpdated;
import com.skytala.eCommerce.domain.supplierRatingType.model.SupplierRatingType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSupplierRatingType extends Command {

private SupplierRatingType elementToBeUpdated;

public UpdateSupplierRatingType(SupplierRatingType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SupplierRatingType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SupplierRatingType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SupplierRatingType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SupplierRatingType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SupplierRatingType.class);
}
success = false;
}
Event resultingEvent = new SupplierRatingTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.saleType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.saleType.event.SaleTypeUpdated;
import com.skytala.eCommerce.domain.saleType.model.SaleType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSaleType extends Command {

private SaleType elementToBeUpdated;

public UpdateSaleType(SaleType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SaleType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SaleType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SaleType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SaleType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SaleType.class);
}
success = false;
}
Event resultingEvent = new SaleTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

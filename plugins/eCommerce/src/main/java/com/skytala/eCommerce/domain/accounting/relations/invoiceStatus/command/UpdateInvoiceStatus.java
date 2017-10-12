package com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.event.InvoiceStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.model.InvoiceStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceStatus extends Command {

private InvoiceStatus elementToBeUpdated;

public UpdateInvoiceStatus(InvoiceStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceStatus.class);
}
success = false;
}
Event resultingEvent = new InvoiceStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

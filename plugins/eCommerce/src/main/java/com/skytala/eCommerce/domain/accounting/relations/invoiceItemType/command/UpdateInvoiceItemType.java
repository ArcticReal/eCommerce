package com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.event.InvoiceItemTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.model.InvoiceItemType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceItemType extends Command {

private InvoiceItemType elementToBeUpdated;

public UpdateInvoiceItemType(InvoiceItemType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceItemType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceItemType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceItemType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceItemType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceItemType.class);
}
success = false;
}
Event resultingEvent = new InvoiceItemTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.accounting.relations.invoiceRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.event.InvoiceRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.model.InvoiceRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceRole extends Command {

private InvoiceRole elementToBeUpdated;

public UpdateInvoiceRole(InvoiceRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceRole.class);
}
success = false;
}
Event resultingEvent = new InvoiceRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

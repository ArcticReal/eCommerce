package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.event.InvoiceItemTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.model.InvoiceItemTypeGlAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceItemTypeGlAccount extends Command {

private InvoiceItemTypeGlAccount elementToBeUpdated;

public UpdateInvoiceItemTypeGlAccount(InvoiceItemTypeGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceItemTypeGlAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceItemTypeGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceItemTypeGlAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceItemTypeGlAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceItemTypeGlAccount.class);
}
success = false;
}
Event resultingEvent = new InvoiceItemTypeGlAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.event.InvoiceItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.model.InvoiceItemTypeAttr;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceItemTypeAttr extends Command {

private InvoiceItemTypeAttr elementToBeUpdated;

public UpdateInvoiceItemTypeAttr(InvoiceItemTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceItemTypeAttr getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceItemTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceItemTypeAttr", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceItemTypeAttr.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceItemTypeAttr.class);
}
success = false;
}
Event resultingEvent = new InvoiceItemTypeAttrUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.event.InvoiceContactMechAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.mapper.InvoiceContactMechMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.model.InvoiceContactMech;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceContactMech extends Command {

private InvoiceContactMech elementToBeAdded;
public AddInvoiceContactMech(InvoiceContactMech elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceContactMech addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceContactMech", elementToBeAdded.mapAttributeField());
addedElement = InvoiceContactMechMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceContactMechAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

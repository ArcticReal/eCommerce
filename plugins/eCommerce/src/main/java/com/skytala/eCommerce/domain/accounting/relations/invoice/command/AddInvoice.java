package com.skytala.eCommerce.domain.accounting.relations.invoice.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.InvoiceAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.InvoiceMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.Invoice;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoice extends Command {

private Invoice elementToBeAdded;
public AddInvoice(Invoice elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Invoice addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceId(delegator.getNextSeqId("Invoice"));
GenericValue newValue = delegator.makeValue("Invoice", elementToBeAdded.mapAttributeField());
addedElement = InvoiceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

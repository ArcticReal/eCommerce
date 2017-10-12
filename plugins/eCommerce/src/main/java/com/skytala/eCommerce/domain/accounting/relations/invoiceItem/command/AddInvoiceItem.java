package com.skytala.eCommerce.domain.accounting.relations.invoiceItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItem.event.InvoiceItemAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItem.mapper.InvoiceItemMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItem.model.InvoiceItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceItem extends Command {

private InvoiceItem elementToBeAdded;
public AddInvoiceItem(InvoiceItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceItemSeqId(delegator.getNextSeqId("InvoiceItem"));
GenericValue newValue = delegator.makeValue("InvoiceItem", elementToBeAdded.mapAttributeField());
addedElement = InvoiceItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

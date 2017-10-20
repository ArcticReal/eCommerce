package com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssoc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc.InvoiceItemAssocAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAssoc.InvoiceItemAssocMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssoc.InvoiceItemAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceItemAssoc extends Command {

private InvoiceItemAssoc elementToBeAdded;
public AddInvoiceItemAssoc(InvoiceItemAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceItemAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceItemAssoc", elementToBeAdded.mapAttributeField());
addedElement = InvoiceItemAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceItemAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

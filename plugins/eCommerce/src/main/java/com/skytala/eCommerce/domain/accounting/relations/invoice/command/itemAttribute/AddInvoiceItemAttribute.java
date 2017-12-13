package com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAttribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAttribute.InvoiceItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceItemAttribute extends Command {

private InvoiceItemAttribute elementToBeAdded;
public AddInvoiceItemAttribute(InvoiceItemAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceItemAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceItemAttribute", elementToBeAdded.mapAttributeField());
addedElement = InvoiceItemAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceItemAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

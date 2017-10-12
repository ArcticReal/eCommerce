package com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event.InvoiceTermAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.mapper.InvoiceTermAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.model.InvoiceTermAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceTermAttribute extends Command {

private InvoiceTermAttribute elementToBeAdded;
public AddInvoiceTermAttribute(InvoiceTermAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceTermAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceTermAttribute", elementToBeAdded.mapAttributeField());
addedElement = InvoiceTermAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceTermAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

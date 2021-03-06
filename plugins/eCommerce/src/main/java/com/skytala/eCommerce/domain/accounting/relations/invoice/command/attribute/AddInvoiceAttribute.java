package com.skytala.eCommerce.domain.accounting.relations.invoice.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.attribute.InvoiceAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.attribute.InvoiceAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceAttribute extends Command {

private InvoiceAttribute elementToBeAdded;
public AddInvoiceAttribute(InvoiceAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceAttribute addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("InvoiceAttribute"));
GenericValue newValue = delegator.makeValue("InvoiceAttribute", elementToBeAdded.mapAttributeField());
addedElement = InvoiceAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

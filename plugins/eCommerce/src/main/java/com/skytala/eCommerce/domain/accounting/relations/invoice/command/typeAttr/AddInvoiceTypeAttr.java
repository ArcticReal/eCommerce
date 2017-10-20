package com.skytala.eCommerce.domain.accounting.relations.invoice.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.typeAttr.InvoiceTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.typeAttr.InvoiceTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceTypeAttr extends Command {

private InvoiceTypeAttr elementToBeAdded;
public AddInvoiceTypeAttr(InvoiceTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = InvoiceTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

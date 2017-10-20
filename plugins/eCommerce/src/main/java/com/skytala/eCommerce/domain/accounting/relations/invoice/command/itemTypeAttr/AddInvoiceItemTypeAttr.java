package com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeAttr.InvoiceItemTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeAttr.InvoiceItemTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeAttr.InvoiceItemTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceItemTypeAttr extends Command {

private InvoiceItemTypeAttr elementToBeAdded;
public AddInvoiceItemTypeAttr(InvoiceItemTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceItemTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceItemTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = InvoiceItemTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceItemTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

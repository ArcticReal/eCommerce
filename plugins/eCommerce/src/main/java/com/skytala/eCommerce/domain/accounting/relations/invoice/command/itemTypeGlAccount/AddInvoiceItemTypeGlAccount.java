package com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeGlAccount;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount.InvoiceItemTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeGlAccount.InvoiceItemTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeGlAccount.InvoiceItemTypeGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceItemTypeGlAccount extends Command {

private InvoiceItemTypeGlAccount elementToBeAdded;
public AddInvoiceItemTypeGlAccount(InvoiceItemTypeGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceItemTypeGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceItemTypeGlAccount", elementToBeAdded.mapAttributeField());
addedElement = InvoiceItemTypeGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceItemTypeGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

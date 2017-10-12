package com.skytala.eCommerce.domain.accounting.relations.invoiceRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.event.InvoiceRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.mapper.InvoiceRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.model.InvoiceRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceRole extends Command {

private InvoiceRole elementToBeAdded;
public AddInvoiceRole(InvoiceRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceRole", elementToBeAdded.mapAttributeField());
addedElement = InvoiceRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.event.TaxAuthorityAssocAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.mapper.TaxAuthorityAssocMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.model.TaxAuthorityAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTaxAuthorityAssoc extends Command {

private TaxAuthorityAssoc elementToBeAdded;
public AddTaxAuthorityAssoc(TaxAuthorityAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TaxAuthorityAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("TaxAuthorityAssoc", elementToBeAdded.mapAttributeField());
addedElement = TaxAuthorityAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TaxAuthorityAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

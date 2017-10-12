package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.event.TaxAuthorityGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.mapper.TaxAuthorityGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.model.TaxAuthorityGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTaxAuthorityGlAccount extends Command {

private TaxAuthorityGlAccount elementToBeAdded;
public AddTaxAuthorityGlAccount(TaxAuthorityGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TaxAuthorityGlAccount addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTaxAuthPartyId(delegator.getNextSeqId("TaxAuthorityGlAccount"));
GenericValue newValue = delegator.makeValue("TaxAuthorityGlAccount", elementToBeAdded.mapAttributeField());
addedElement = TaxAuthorityGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TaxAuthorityGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

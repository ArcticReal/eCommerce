package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.category;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.category.TaxAuthorityCategoryAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.category.TaxAuthorityCategoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.category.TaxAuthorityCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTaxAuthorityCategory extends Command {

private TaxAuthorityCategory elementToBeAdded;
public AddTaxAuthorityCategory(TaxAuthorityCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TaxAuthorityCategory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTaxAuthPartyId(delegator.getNextSeqId("TaxAuthorityCategory"));
GenericValue newValue = delegator.makeValue("TaxAuthorityCategory", elementToBeAdded.mapAttributeField());
addedElement = TaxAuthorityCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TaxAuthorityCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

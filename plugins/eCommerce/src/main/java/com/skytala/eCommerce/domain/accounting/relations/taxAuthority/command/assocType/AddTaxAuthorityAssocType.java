package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.assocType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assocType.TaxAuthorityAssocTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.assocType.TaxAuthorityAssocTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assocType.TaxAuthorityAssocType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTaxAuthorityAssocType extends Command {

private TaxAuthorityAssocType elementToBeAdded;
public AddTaxAuthorityAssocType(TaxAuthorityAssocType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TaxAuthorityAssocType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTaxAuthorityAssocTypeId(delegator.getNextSeqId("TaxAuthorityAssocType"));
GenericValue newValue = delegator.makeValue("TaxAuthorityAssocType", elementToBeAdded.mapAttributeField());
addedElement = TaxAuthorityAssocTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TaxAuthorityAssocTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

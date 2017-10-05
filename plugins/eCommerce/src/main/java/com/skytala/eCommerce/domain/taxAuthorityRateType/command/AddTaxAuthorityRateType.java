package com.skytala.eCommerce.domain.taxAuthorityRateType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.taxAuthorityRateType.event.TaxAuthorityRateTypeAdded;
import com.skytala.eCommerce.domain.taxAuthorityRateType.mapper.TaxAuthorityRateTypeMapper;
import com.skytala.eCommerce.domain.taxAuthorityRateType.model.TaxAuthorityRateType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTaxAuthorityRateType extends Command {

private TaxAuthorityRateType elementToBeAdded;
public AddTaxAuthorityRateType(TaxAuthorityRateType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TaxAuthorityRateType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTaxAuthorityRateTypeId(delegator.getNextSeqId("TaxAuthorityRateType"));
GenericValue newValue = delegator.makeValue("TaxAuthorityRateType", elementToBeAdded.mapAttributeField());
addedElement = TaxAuthorityRateTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TaxAuthorityRateTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

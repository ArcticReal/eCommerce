package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.rateProduct;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct.TaxAuthorityRateProductAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.rateProduct.TaxAuthorityRateProductMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateProduct.TaxAuthorityRateProduct;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTaxAuthorityRateProduct extends Command {

private TaxAuthorityRateProduct elementToBeAdded;
public AddTaxAuthorityRateProduct(TaxAuthorityRateProduct elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TaxAuthorityRateProduct addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTaxAuthorityRateSeqId(delegator.getNextSeqId("TaxAuthorityRateProduct"));
GenericValue newValue = delegator.makeValue("TaxAuthorityRateProduct", elementToBeAdded.mapAttributeField());
addedElement = TaxAuthorityRateProductMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TaxAuthorityRateProductAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

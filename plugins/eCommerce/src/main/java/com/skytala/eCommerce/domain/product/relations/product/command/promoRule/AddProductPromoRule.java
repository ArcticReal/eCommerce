package com.skytala.eCommerce.domain.product.relations.product.command.promoRule;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.promoRule.ProductPromoRuleAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoRule.ProductPromoRuleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoRule.ProductPromoRule;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoRule extends Command {

private ProductPromoRule elementToBeAdded;
public AddProductPromoRule(ProductPromoRule elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoRule addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPromoRuleId(delegator.getNextSeqId("ProductPromoRule"));
GenericValue newValue = delegator.makeValue("ProductPromoRule", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoRuleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoRuleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

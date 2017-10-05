package com.skytala.eCommerce.domain.productPriceRule.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productPriceRule.event.ProductPriceRuleAdded;
import com.skytala.eCommerce.domain.productPriceRule.mapper.ProductPriceRuleMapper;
import com.skytala.eCommerce.domain.productPriceRule.model.ProductPriceRule;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPriceRule extends Command {

private ProductPriceRule elementToBeAdded;
public AddProductPriceRule(ProductPriceRule elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPriceRule addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPriceRuleId(delegator.getNextSeqId("ProductPriceRule"));
GenericValue newValue = delegator.makeValue("ProductPriceRule", elementToBeAdded.mapAttributeField());
addedElement = ProductPriceRuleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPriceRuleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

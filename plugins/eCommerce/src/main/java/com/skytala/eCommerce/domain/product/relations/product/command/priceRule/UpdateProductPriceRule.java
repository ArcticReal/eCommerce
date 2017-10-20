package com.skytala.eCommerce.domain.product.relations.product.command.priceRule;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.priceRule.ProductPriceRuleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.priceRule.ProductPriceRule;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPriceRule extends Command {

private ProductPriceRule elementToBeUpdated;

public UpdateProductPriceRule(ProductPriceRule elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPriceRule getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPriceRule elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPriceRule", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPriceRule.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPriceRule.class);
}
success = false;
}
Event resultingEvent = new ProductPriceRuleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

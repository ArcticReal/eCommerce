package com.skytala.eCommerce.domain.product.relations.productPriceCond.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.event.ProductPriceCondAdded;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.mapper.ProductPriceCondMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.model.ProductPriceCond;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPriceCond extends Command {

private ProductPriceCond elementToBeAdded;
public AddProductPriceCond(ProductPriceCond elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPriceCond addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPriceCondSeqId(delegator.getNextSeqId("ProductPriceCond"));
GenericValue newValue = delegator.makeValue("ProductPriceCond", elementToBeAdded.mapAttributeField());
addedElement = ProductPriceCondMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPriceCondAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

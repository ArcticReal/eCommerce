package com.skytala.eCommerce.domain.product.relations.product.command.priceAction;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAction.ProductPriceActionAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceAction.ProductPriceActionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceAction.ProductPriceAction;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPriceAction extends Command {

private ProductPriceAction elementToBeAdded;
public AddProductPriceAction(ProductPriceAction elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPriceAction addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPriceActionSeqId(delegator.getNextSeqId("ProductPriceAction"));
GenericValue newValue = delegator.makeValue("ProductPriceAction", elementToBeAdded.mapAttributeField());
addedElement = ProductPriceActionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPriceActionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

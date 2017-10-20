package com.skytala.eCommerce.domain.product.relations.product.command.priceChange;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.priceChange.ProductPriceChangeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceChange.ProductPriceChangeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceChange.ProductPriceChange;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPriceChange extends Command {

private ProductPriceChange elementToBeAdded;
public AddProductPriceChange(ProductPriceChange elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPriceChange addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPriceChangeId(delegator.getNextSeqId("ProductPriceChange"));
GenericValue newValue = delegator.makeValue("ProductPriceChange", elementToBeAdded.mapAttributeField());
addedElement = ProductPriceChangeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPriceChangeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

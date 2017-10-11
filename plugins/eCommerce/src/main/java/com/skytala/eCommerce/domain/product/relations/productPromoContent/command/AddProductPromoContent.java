package com.skytala.eCommerce.domain.product.relations.productPromoContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPromoContent.event.ProductPromoContentAdded;
import com.skytala.eCommerce.domain.product.relations.productPromoContent.mapper.ProductPromoContentMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoContent.model.ProductPromoContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoContent extends Command {

private ProductPromoContent elementToBeAdded;
public AddProductPromoContent(ProductPromoContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductPromoContent", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

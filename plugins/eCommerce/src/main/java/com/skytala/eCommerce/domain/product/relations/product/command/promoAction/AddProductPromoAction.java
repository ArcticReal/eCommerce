package com.skytala.eCommerce.domain.product.relations.product.command.promoAction;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.promoAction.ProductPromoActionAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoAction.ProductPromoActionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoAction.ProductPromoAction;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoAction extends Command {

private ProductPromoAction elementToBeAdded;
public AddProductPromoAction(ProductPromoAction elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoAction addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductPromoAction", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoActionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoActionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

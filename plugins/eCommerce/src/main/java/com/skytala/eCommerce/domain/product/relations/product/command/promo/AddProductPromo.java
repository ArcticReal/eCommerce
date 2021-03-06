package com.skytala.eCommerce.domain.product.relations.product.command.promo;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promo.ProductPromoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromo extends Command {

private ProductPromo elementToBeAdded;
public AddProductPromo(ProductPromo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromo addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPromoId(delegator.getNextSeqId("ProductPromo"));
GenericValue newValue = delegator.makeValue("ProductPromo", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

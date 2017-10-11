package com.skytala.eCommerce.domain.product.relations.productPromoCode.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPromoCode.event.ProductPromoCodeAdded;
import com.skytala.eCommerce.domain.product.relations.productPromoCode.mapper.ProductPromoCodeMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoCode.model.ProductPromoCode;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoCode extends Command {

private ProductPromoCode elementToBeAdded;
public AddProductPromoCode(ProductPromoCode elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoCode addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPromoCodeId(delegator.getNextSeqId("ProductPromoCode"));
GenericValue newValue = delegator.makeValue("ProductPromoCode", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoCodeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoCodeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

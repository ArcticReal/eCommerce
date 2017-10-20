package com.skytala.eCommerce.domain.product.relations.product.command.storePromoAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storePromoAppl.ProductStorePromoApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStorePromoAppl extends Command {

private ProductStorePromoAppl elementToBeAdded;
public AddProductStorePromoAppl(ProductStorePromoAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStorePromoAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStorePromoAppl", elementToBeAdded.mapAttributeField());
addedElement = ProductStorePromoApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStorePromoApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

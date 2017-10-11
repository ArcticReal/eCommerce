package com.skytala.eCommerce.domain.product.relations.productPromoProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.event.ProductPromoProductAdded;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.mapper.ProductPromoProductMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.model.ProductPromoProduct;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoProduct extends Command {

private ProductPromoProduct elementToBeAdded;
public AddProductPromoProduct(ProductPromoProduct elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoProduct addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductPromoProduct", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoProductMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoProductAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

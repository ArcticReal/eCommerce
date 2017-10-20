package com.skytala.eCommerce.domain.product.relations.product.command.promoCategory;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCategory.ProductPromoCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCategory.ProductPromoCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCategory.ProductPromoCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoCategory extends Command {

private ProductPromoCategory elementToBeAdded;
public AddProductPromoCategory(ProductPromoCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoCategory addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductPromoCategory", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

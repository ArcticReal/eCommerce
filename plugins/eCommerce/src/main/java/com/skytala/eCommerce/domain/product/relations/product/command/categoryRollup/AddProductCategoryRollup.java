package com.skytala.eCommerce.domain.product.relations.product.command.categoryRollup;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup.ProductCategoryRollupAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryRollup.ProductCategoryRollupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryRollup.ProductCategoryRollup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryRollup extends Command {

private ProductCategoryRollup elementToBeAdded;
public AddProductCategoryRollup(ProductCategoryRollup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryRollup addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCategoryRollup", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryRollupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryRollupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

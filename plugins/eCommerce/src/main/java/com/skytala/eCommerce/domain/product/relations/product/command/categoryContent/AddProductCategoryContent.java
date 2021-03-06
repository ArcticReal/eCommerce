package com.skytala.eCommerce.domain.product.relations.product.command.categoryContent;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContent.ProductCategoryContentAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryContent.ProductCategoryContentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryContent.ProductCategoryContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryContent extends Command {

private ProductCategoryContent elementToBeAdded;
public AddProductCategoryContent(ProductCategoryContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCategoryContent", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.product.relations.product.command.categoryLink;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryLink.ProductCategoryLinkAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryLink.ProductCategoryLinkMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryLink.ProductCategoryLink;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryLink extends Command {

private ProductCategoryLink elementToBeAdded;
public AddProductCategoryLink(ProductCategoryLink elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryLink addedElement = null;
boolean success = false;
try {
elementToBeAdded.setLinkSeqId(delegator.getNextSeqId("ProductCategoryLink"));
GenericValue newValue = delegator.makeValue("ProductCategoryLink", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryLinkMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryLinkAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

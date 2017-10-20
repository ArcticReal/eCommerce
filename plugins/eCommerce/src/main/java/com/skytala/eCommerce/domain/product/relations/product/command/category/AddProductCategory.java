package com.skytala.eCommerce.domain.product.relations.product.command.category;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.category.ProductCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategory extends Command {

private ProductCategory elementToBeAdded;
public AddProductCategory(ProductCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductCategoryId(delegator.getNextSeqId("ProductCategory"));
GenericValue newValue = delegator.makeValue("ProductCategory", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.product.relations.productCategoryRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productCategoryRole.event.ProductCategoryRoleAdded;
import com.skytala.eCommerce.domain.product.relations.productCategoryRole.mapper.ProductCategoryRoleMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryRole.model.ProductCategoryRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryRole extends Command {

private ProductCategoryRole elementToBeAdded;
public AddProductCategoryRole(ProductCategoryRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCategoryRole", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

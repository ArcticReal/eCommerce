package com.skytala.eCommerce.domain.product.relations.product.command.categoryGlAccount;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryGlAccount.ProductCategoryGlAccountAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryGlAccount.ProductCategoryGlAccountMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryGlAccount.ProductCategoryGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryGlAccount extends Command {

private ProductCategoryGlAccount elementToBeAdded;
public AddProductCategoryGlAccount(ProductCategoryGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCategoryGlAccount", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

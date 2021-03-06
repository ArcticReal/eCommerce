package com.skytala.eCommerce.domain.product.relations.product.command.categoryRole;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRole.ProductCategoryRoleAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryRole.ProductCategoryRoleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryRole.ProductCategoryRole;
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
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("ProductCategoryRole"));
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

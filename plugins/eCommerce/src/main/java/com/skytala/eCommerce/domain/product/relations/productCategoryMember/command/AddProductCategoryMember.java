package com.skytala.eCommerce.domain.product.relations.productCategoryMember.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.event.ProductCategoryMemberAdded;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.mapper.ProductCategoryMemberMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.model.ProductCategoryMember;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryMember extends Command {

private ProductCategoryMember elementToBeAdded;
public AddProductCategoryMember(ProductCategoryMember elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryMember addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCategoryMember", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryMemberMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryMemberAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

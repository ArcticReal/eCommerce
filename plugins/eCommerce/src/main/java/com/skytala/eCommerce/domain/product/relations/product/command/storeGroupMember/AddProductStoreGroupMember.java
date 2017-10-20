package com.skytala.eCommerce.domain.product.relations.product.command.storeGroupMember;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember.ProductStoreGroupMemberAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupMember.ProductStoreGroupMemberMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupMember.ProductStoreGroupMember;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreGroupMember extends Command {

private ProductStoreGroupMember elementToBeAdded;
public AddProductStoreGroupMember(ProductStoreGroupMember elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreGroupMember addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStoreGroupMember", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreGroupMemberMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreGroupMemberAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

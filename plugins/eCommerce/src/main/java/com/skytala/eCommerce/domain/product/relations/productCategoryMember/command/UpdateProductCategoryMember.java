package com.skytala.eCommerce.domain.product.relations.productCategoryMember.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.event.ProductCategoryMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryMember.model.ProductCategoryMember;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCategoryMember extends Command {

private ProductCategoryMember elementToBeUpdated;

public UpdateProductCategoryMember(ProductCategoryMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategoryMember getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategoryMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategoryMember", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategoryMember.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCategoryMember.class);
}
success = false;
}
Event resultingEvent = new ProductCategoryMemberUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.product.relations.product.command.keyword;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.keyword.ProductKeywordUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.keyword.ProductKeyword;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductKeyword extends Command {

private ProductKeyword elementToBeUpdated;

public UpdateProductKeyword(ProductKeyword elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductKeyword getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductKeyword elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductKeyword", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductKeyword.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductKeyword.class);
}
success = false;
}
Event resultingEvent = new ProductKeywordUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

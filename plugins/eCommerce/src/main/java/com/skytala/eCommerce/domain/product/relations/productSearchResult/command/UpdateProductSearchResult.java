package com.skytala.eCommerce.domain.product.relations.productSearchResult.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productSearchResult.event.ProductSearchResultUpdated;
import com.skytala.eCommerce.domain.product.relations.productSearchResult.model.ProductSearchResult;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductSearchResult extends Command {

private ProductSearchResult elementToBeUpdated;

public UpdateProductSearchResult(ProductSearchResult elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductSearchResult getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductSearchResult elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductSearchResult", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductSearchResult.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductSearchResult.class);
}
success = false;
}
Event resultingEvent = new ProductSearchResultUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.product.relations.product.command.review;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.review.ProductReviewUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.review.ProductReview;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductReview extends Command {

private ProductReview elementToBeUpdated;

public UpdateProductReview(ProductReview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductReview getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductReview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductReview", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductReview.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductReview.class);
}
success = false;
}
Event resultingEvent = new ProductReviewUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

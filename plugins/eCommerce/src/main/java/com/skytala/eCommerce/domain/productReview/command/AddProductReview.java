package com.skytala.eCommerce.domain.productReview.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productReview.event.ProductReviewAdded;
import com.skytala.eCommerce.domain.productReview.mapper.ProductReviewMapper;
import com.skytala.eCommerce.domain.productReview.model.ProductReview;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductReview extends Command {

private ProductReview elementToBeAdded;
public AddProductReview(ProductReview elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductReview addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductReviewId(delegator.getNextSeqId("ProductReview"));
GenericValue newValue = delegator.makeValue("ProductReview", elementToBeAdded.mapAttributeField());
addedElement = ProductReviewMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductReviewAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

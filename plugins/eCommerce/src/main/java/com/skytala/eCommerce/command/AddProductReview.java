package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductReview;
import com.skytala.eCommerce.event.ProductReviewAdded;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class AddProductReview implements Command {

private ProductReview elementToBeAdded;
public AddProductReview(ProductReview elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public void execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try {
elementToBeAdded.setProductReviewId(delegator.getNextSeqId("ProductReview"));
GenericValue newValue = delegator.makeValue("ProductReview", elementToBeAdded.mapAttributeField());
delegator.create(newValue);
success = true;
} catch(GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}

Broker.instance().publish(new ProductReviewAdded(success));
}
}

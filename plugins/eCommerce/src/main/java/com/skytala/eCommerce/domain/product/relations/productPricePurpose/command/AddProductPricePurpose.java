package com.skytala.eCommerce.domain.product.relations.productPricePurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPricePurpose.event.ProductPricePurposeAdded;
import com.skytala.eCommerce.domain.product.relations.productPricePurpose.mapper.ProductPricePurposeMapper;
import com.skytala.eCommerce.domain.product.relations.productPricePurpose.model.ProductPricePurpose;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPricePurpose extends Command {

private ProductPricePurpose elementToBeAdded;
public AddProductPricePurpose(ProductPricePurpose elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPricePurpose addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPricePurposeId(delegator.getNextSeqId("ProductPricePurpose"));
GenericValue newValue = delegator.makeValue("ProductPricePurpose", elementToBeAdded.mapAttributeField());
addedElement = ProductPricePurposeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPricePurposeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

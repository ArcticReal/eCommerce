package com.skytala.eCommerce.domain.product.relations.productSearchResult.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productSearchResult.event.ProductSearchResultAdded;
import com.skytala.eCommerce.domain.product.relations.productSearchResult.mapper.ProductSearchResultMapper;
import com.skytala.eCommerce.domain.product.relations.productSearchResult.model.ProductSearchResult;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductSearchResult extends Command {

private ProductSearchResult elementToBeAdded;
public AddProductSearchResult(ProductSearchResult elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductSearchResult addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductSearchResultId(delegator.getNextSeqId("ProductSearchResult"));
GenericValue newValue = delegator.makeValue("ProductSearchResult", elementToBeAdded.mapAttributeField());
addedElement = ProductSearchResultMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductSearchResultAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.product.relations.product.command.storeSurveyAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeSurveyAppl.ProductStoreSurveyApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeSurveyAppl.ProductStoreSurveyAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreSurveyAppl extends Command {

private ProductStoreSurveyAppl elementToBeAdded;
public AddProductStoreSurveyAppl(ProductStoreSurveyAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreSurveyAppl addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductStoreSurveyId(delegator.getNextSeqId("ProductStoreSurveyAppl"));
GenericValue newValue = delegator.makeValue("ProductStoreSurveyAppl", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreSurveyApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreSurveyApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

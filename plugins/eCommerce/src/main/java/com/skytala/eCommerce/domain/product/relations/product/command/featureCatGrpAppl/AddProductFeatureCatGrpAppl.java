package com.skytala.eCommerce.domain.product.relations.product.command.featureCatGrpAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCatGrpAppl.ProductFeatureCatGrpApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureCatGrpAppl.ProductFeatureCatGrpApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureCatGrpAppl.ProductFeatureCatGrpAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureCatGrpAppl extends Command {

private ProductFeatureCatGrpAppl elementToBeAdded;
public AddProductFeatureCatGrpAppl(ProductFeatureCatGrpAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureCatGrpAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductFeatureCatGrpAppl", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureCatGrpApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureCatGrpApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

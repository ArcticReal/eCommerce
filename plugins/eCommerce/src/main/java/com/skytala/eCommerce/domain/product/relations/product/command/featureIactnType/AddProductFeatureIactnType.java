package com.skytala.eCommerce.domain.product.relations.product.command.featureIactnType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactnType.ProductFeatureIactnTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureIactnType.ProductFeatureIactnTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureIactnType.ProductFeatureIactnType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureIactnType extends Command {

private ProductFeatureIactnType elementToBeAdded;
public AddProductFeatureIactnType(ProductFeatureIactnType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureIactnType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductFeatureIactnTypeId(delegator.getNextSeqId("ProductFeatureIactnType"));
GenericValue newValue = delegator.makeValue("ProductFeatureIactnType", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureIactnTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureIactnTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

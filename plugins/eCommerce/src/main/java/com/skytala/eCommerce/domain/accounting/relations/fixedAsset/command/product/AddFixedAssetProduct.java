package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.product;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.product.FixedAssetProductAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.product.FixedAssetProductMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.product.FixedAssetProduct;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetProduct extends Command {

private FixedAssetProduct elementToBeAdded;
public AddFixedAssetProduct(FixedAssetProduct elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetProduct addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetProduct", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetProductMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetProductAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.product.relations.product.command.storeCatalog;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog.ProductStoreCatalogAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeCatalog.ProductStoreCatalogMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeCatalog.ProductStoreCatalog;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreCatalog extends Command {

private ProductStoreCatalog elementToBeAdded;
public AddProductStoreCatalog(ProductStoreCatalog elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreCatalog addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStoreCatalog", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreCatalogMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreCatalogAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

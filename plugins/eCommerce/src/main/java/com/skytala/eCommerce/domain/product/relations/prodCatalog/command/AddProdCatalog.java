package com.skytala.eCommerce.domain.product.relations.prodCatalog.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.ProdCatalogAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.ProdCatalogMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.ProdCatalog;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProdCatalog extends Command {

private ProdCatalog elementToBeAdded;
public AddProdCatalog(ProdCatalog elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProdCatalog addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProdCatalogId(delegator.getNextSeqId("ProdCatalog"));
GenericValue newValue = delegator.makeValue("ProdCatalog", elementToBeAdded.mapAttributeField());
addedElement = ProdCatalogMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProdCatalogAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

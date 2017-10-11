package com.skytala.eCommerce.domain.product.relations.prodCatalogRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalogRole.event.ProdCatalogRoleAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalogRole.mapper.ProdCatalogRoleMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalogRole.model.ProdCatalogRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProdCatalogRole extends Command {

private ProdCatalogRole elementToBeAdded;
public AddProdCatalogRole(ProdCatalogRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProdCatalogRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProdCatalogRole", elementToBeAdded.mapAttributeField());
addedElement = ProdCatalogRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProdCatalogRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

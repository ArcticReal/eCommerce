package com.skytala.eCommerce.domain.product.relations.prodCatalog.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role.ProdCatalogRoleAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.role.ProdCatalogRoleMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role.ProdCatalogRole;
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
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("ProdCatalogRole"));
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

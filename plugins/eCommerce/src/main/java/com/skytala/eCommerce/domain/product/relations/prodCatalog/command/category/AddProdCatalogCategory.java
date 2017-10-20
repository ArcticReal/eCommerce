package com.skytala.eCommerce.domain.product.relations.prodCatalog.command.category;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.category.ProdCatalogCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.category.ProdCatalogCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProdCatalogCategory extends Command {

private ProdCatalogCategory elementToBeAdded;
public AddProdCatalogCategory(ProdCatalogCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProdCatalogCategory addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProdCatalogCategory", elementToBeAdded.mapAttributeField());
addedElement = ProdCatalogCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProdCatalogCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

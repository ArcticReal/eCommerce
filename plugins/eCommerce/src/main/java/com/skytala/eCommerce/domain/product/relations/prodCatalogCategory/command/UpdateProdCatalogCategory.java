package com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.event.ProdCatalogCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.model.ProdCatalogCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProdCatalogCategory extends Command {

private ProdCatalogCategory elementToBeUpdated;

public UpdateProdCatalogCategory(ProdCatalogCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProdCatalogCategory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProdCatalogCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProdCatalogCategory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProdCatalogCategory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProdCatalogCategory.class);
}
success = false;
}
Event resultingEvent = new ProdCatalogCategoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
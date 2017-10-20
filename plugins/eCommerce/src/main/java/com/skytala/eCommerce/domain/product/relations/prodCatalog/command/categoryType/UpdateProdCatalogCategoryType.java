package com.skytala.eCommerce.domain.product.relations.prodCatalog.command.categoryType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.categoryType.ProdCatalogCategoryTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.categoryType.ProdCatalogCategoryType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProdCatalogCategoryType extends Command {

private ProdCatalogCategoryType elementToBeUpdated;

public UpdateProdCatalogCategoryType(ProdCatalogCategoryType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProdCatalogCategoryType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProdCatalogCategoryType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProdCatalogCategoryType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProdCatalogCategoryType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProdCatalogCategoryType.class);
}
success = false;
}
Event resultingEvent = new ProdCatalogCategoryTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

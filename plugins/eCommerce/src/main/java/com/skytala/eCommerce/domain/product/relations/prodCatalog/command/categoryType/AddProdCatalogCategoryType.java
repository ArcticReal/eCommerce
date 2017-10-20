package com.skytala.eCommerce.domain.product.relations.prodCatalog.command.categoryType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.categoryType.ProdCatalogCategoryTypeAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.categoryType.ProdCatalogCategoryTypeMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.categoryType.ProdCatalogCategoryType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProdCatalogCategoryType extends Command {

private ProdCatalogCategoryType elementToBeAdded;
public AddProdCatalogCategoryType(ProdCatalogCategoryType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProdCatalogCategoryType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProdCatalogCategoryTypeId(delegator.getNextSeqId("ProdCatalogCategoryType"));
GenericValue newValue = delegator.makeValue("ProdCatalogCategoryType", elementToBeAdded.mapAttributeField());
addedElement = ProdCatalogCategoryTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProdCatalogCategoryTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

package com.skytala.eCommerce.domain.product.relations.product.command.featureCatGrpAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCatGrpAppl.ProductFeatureCatGrpApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.featureCatGrpAppl.ProductFeatureCatGrpAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFeatureCatGrpAppl extends Command {

private ProductFeatureCatGrpAppl elementToBeUpdated;

public UpdateProductFeatureCatGrpAppl(ProductFeatureCatGrpAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFeatureCatGrpAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFeatureCatGrpAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFeatureCatGrpAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFeatureCatGrpAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFeatureCatGrpAppl.class);
}
success = false;
}
Event resultingEvent = new ProductFeatureCatGrpApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

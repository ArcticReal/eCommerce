package com.skytala.eCommerce.domain.product.relations.product.command.maint;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.maint.ProductMaintAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.maint.ProductMaintMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.maint.ProductMaint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductMaint extends Command {

private ProductMaint elementToBeAdded;
public AddProductMaint(ProductMaint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductMaint addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductMaintSeqId(delegator.getNextSeqId("ProductMaint"));
GenericValue newValue = delegator.makeValue("ProductMaint", elementToBeAdded.mapAttributeField());
addedElement = ProductMaintMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductMaintAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

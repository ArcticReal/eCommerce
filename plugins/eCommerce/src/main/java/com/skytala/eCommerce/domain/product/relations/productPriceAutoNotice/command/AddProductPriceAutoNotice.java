package com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.event.ProductPriceAutoNoticeAdded;
import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.mapper.ProductPriceAutoNoticeMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.model.ProductPriceAutoNotice;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPriceAutoNotice extends Command {

private ProductPriceAutoNotice elementToBeAdded;
public AddProductPriceAutoNotice(ProductPriceAutoNotice elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPriceAutoNotice addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPriceNoticeId(delegator.getNextSeqId("ProductPriceAutoNotice"));
GenericValue newValue = delegator.makeValue("ProductPriceAutoNotice", elementToBeAdded.mapAttributeField());
addedElement = ProductPriceAutoNoticeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPriceAutoNoticeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

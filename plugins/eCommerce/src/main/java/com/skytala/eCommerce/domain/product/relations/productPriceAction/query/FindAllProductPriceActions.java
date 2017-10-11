
package com.skytala.eCommerce.domain.product.relations.productPriceAction.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.event.ProductPriceActionFound;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.mapper.ProductPriceActionMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.model.ProductPriceAction;


public class FindAllProductPriceActions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPriceAction> returnVal = new ArrayList<ProductPriceAction>();
try{
List<GenericValue> results = delegator.findAll("ProductPriceAction", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPriceActionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPriceActionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

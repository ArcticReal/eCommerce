
package com.skytala.eCommerce.domain.product.relations.product.query.promoAction;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.promoAction.ProductPromoActionFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoAction.ProductPromoActionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoAction.ProductPromoAction;


public class FindAllProductPromoActions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoAction> returnVal = new ArrayList<ProductPromoAction>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoAction", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoActionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoActionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

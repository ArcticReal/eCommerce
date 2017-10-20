
package com.skytala.eCommerce.domain.product.relations.product.query.priceActionType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceActionType.ProductPriceActionTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceActionType.ProductPriceActionType;


public class FindAllProductPriceActionTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPriceActionType> returnVal = new ArrayList<ProductPriceActionType>();
try{
List<GenericValue> results = delegator.findAll("ProductPriceActionType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPriceActionTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPriceActionTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

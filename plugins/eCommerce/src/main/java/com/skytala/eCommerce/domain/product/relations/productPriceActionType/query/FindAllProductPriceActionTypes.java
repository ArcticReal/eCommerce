
package com.skytala.eCommerce.domain.product.relations.productPriceActionType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPriceActionType.event.ProductPriceActionTypeFound;
import com.skytala.eCommerce.domain.product.relations.productPriceActionType.mapper.ProductPriceActionTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceActionType.model.ProductPriceActionType;


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

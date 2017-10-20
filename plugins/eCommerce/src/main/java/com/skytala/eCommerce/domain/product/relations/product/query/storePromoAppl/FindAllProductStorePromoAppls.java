
package com.skytala.eCommerce.domain.product.relations.product.query.storePromoAppl;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storePromoAppl.ProductStorePromoApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;


public class FindAllProductStorePromoAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStorePromoAppl> returnVal = new ArrayList<ProductStorePromoAppl>();
try{
List<GenericValue> results = delegator.findAll("ProductStorePromoAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStorePromoApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStorePromoApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

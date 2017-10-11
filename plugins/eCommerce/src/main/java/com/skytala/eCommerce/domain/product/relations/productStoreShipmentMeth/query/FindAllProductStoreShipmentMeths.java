
package com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.event.ProductStoreShipmentMethFound;
import com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.mapper.ProductStoreShipmentMethMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.model.ProductStoreShipmentMeth;


public class FindAllProductStoreShipmentMeths extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreShipmentMeth> returnVal = new ArrayList<ProductStoreShipmentMeth>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreShipmentMeth", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreShipmentMethMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreShipmentMethFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

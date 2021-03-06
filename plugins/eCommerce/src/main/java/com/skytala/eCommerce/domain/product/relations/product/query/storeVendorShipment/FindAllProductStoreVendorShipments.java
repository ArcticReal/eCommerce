
package com.skytala.eCommerce.domain.product.relations.product.query.storeVendorShipment;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment.ProductStoreVendorShipmentFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeVendorShipment.ProductStoreVendorShipmentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorShipment.ProductStoreVendorShipment;


public class FindAllProductStoreVendorShipments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreVendorShipment> returnVal = new ArrayList<ProductStoreVendorShipment>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreVendorShipment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreVendorShipmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreVendorShipmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}


package com.skytala.eCommerce.domain.product.relations.vendorProduct.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.event.VendorProductFound;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.mapper.VendorProductMapper;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.model.VendorProduct;


public class FindAllVendorProducts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<VendorProduct> returnVal = new ArrayList<VendorProduct>();
try{
List<GenericValue> results = delegator.findAll("VendorProduct", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(VendorProductMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new VendorProductFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

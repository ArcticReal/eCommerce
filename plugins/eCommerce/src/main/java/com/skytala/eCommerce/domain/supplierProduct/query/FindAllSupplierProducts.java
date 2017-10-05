
package com.skytala.eCommerce.domain.supplierProduct.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.supplierProduct.event.SupplierProductFound;
import com.skytala.eCommerce.domain.supplierProduct.mapper.SupplierProductMapper;
import com.skytala.eCommerce.domain.supplierProduct.model.SupplierProduct;


public class FindAllSupplierProducts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SupplierProduct> returnVal = new ArrayList<SupplierProduct>();
try{
List<GenericValue> results = delegator.findAll("SupplierProduct", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SupplierProductMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SupplierProductFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

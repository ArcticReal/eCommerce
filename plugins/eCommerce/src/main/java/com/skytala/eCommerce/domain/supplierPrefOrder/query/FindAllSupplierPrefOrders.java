
package com.skytala.eCommerce.domain.supplierPrefOrder.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.supplierPrefOrder.event.SupplierPrefOrderFound;
import com.skytala.eCommerce.domain.supplierPrefOrder.mapper.SupplierPrefOrderMapper;
import com.skytala.eCommerce.domain.supplierPrefOrder.model.SupplierPrefOrder;


public class FindAllSupplierPrefOrders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SupplierPrefOrder> returnVal = new ArrayList<SupplierPrefOrder>();
try{
List<GenericValue> results = delegator.findAll("SupplierPrefOrder", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SupplierPrefOrderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SupplierPrefOrderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

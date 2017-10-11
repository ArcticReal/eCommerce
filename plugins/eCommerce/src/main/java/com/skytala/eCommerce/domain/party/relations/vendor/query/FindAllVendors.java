
package com.skytala.eCommerce.domain.party.relations.vendor.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.vendor.event.VendorFound;
import com.skytala.eCommerce.domain.party.relations.vendor.mapper.VendorMapper;
import com.skytala.eCommerce.domain.party.relations.vendor.model.Vendor;


public class FindAllVendors extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Vendor> returnVal = new ArrayList<Vendor>();
try{
List<GenericValue> results = delegator.findAll("Vendor", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(VendorMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new VendorFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

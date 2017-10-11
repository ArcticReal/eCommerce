
package com.skytala.eCommerce.domain.product.relations.supplierRatingType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.event.SupplierRatingTypeFound;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.mapper.SupplierRatingTypeMapper;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.model.SupplierRatingType;


public class FindAllSupplierRatingTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SupplierRatingType> returnVal = new ArrayList<SupplierRatingType>();
try{
List<GenericValue> results = delegator.findAll("SupplierRatingType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SupplierRatingTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SupplierRatingTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

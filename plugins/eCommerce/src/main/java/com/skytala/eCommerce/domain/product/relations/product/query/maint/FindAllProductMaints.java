
package com.skytala.eCommerce.domain.product.relations.product.query.maint;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.maint.ProductMaintFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.maint.ProductMaintMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.maint.ProductMaint;


public class FindAllProductMaints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductMaint> returnVal = new ArrayList<ProductMaint>();
try{
List<GenericValue> results = delegator.findAll("ProductMaint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductMaintMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductMaintFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

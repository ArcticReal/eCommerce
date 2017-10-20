
package com.skytala.eCommerce.domain.product.relations.costComponent.query.productCalc;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.productCalc.ProductCostComponentCalcFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.productCalc.ProductCostComponentCalcMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.productCalc.ProductCostComponentCalc;


public class FindAllProductCostComponentCalcs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCostComponentCalc> returnVal = new ArrayList<ProductCostComponentCalc>();
try{
List<GenericValue> results = delegator.findAll("ProductCostComponentCalc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCostComponentCalcMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCostComponentCalcFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

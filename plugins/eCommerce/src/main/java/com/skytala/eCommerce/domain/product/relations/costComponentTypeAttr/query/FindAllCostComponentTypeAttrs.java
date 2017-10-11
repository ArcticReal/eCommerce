
package com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.event.CostComponentTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.mapper.CostComponentTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.model.CostComponentTypeAttr;


public class FindAllCostComponentTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CostComponentTypeAttr> returnVal = new ArrayList<CostComponentTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("CostComponentTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CostComponentTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CostComponentTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}


package com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.event.ProductStoreSurveyApplFound;
import com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.mapper.ProductStoreSurveyApplMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.model.ProductStoreSurveyAppl;


public class FindAllProductStoreSurveyAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreSurveyAppl> returnVal = new ArrayList<ProductStoreSurveyAppl>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreSurveyAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreSurveyApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreSurveyApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

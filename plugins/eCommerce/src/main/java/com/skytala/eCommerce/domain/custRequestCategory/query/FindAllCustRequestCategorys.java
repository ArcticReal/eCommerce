
package com.skytala.eCommerce.domain.custRequestCategory.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.custRequestCategory.event.CustRequestCategoryFound;
import com.skytala.eCommerce.domain.custRequestCategory.mapper.CustRequestCategoryMapper;
import com.skytala.eCommerce.domain.custRequestCategory.model.CustRequestCategory;


public class FindAllCustRequestCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestCategory> returnVal = new ArrayList<CustRequestCategory>();
try{
List<GenericValue> results = delegator.findAll("CustRequestCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

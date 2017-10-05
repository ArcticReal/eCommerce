
package com.skytala.eCommerce.domain.dataCategory.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.dataCategory.event.DataCategoryFound;
import com.skytala.eCommerce.domain.dataCategory.mapper.DataCategoryMapper;
import com.skytala.eCommerce.domain.dataCategory.model.DataCategory;


public class FindAllDataCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataCategory> returnVal = new ArrayList<DataCategory>();
try{
List<GenericValue> results = delegator.findAll("DataCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

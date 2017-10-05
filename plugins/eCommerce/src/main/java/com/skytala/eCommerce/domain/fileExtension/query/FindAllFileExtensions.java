
package com.skytala.eCommerce.domain.fileExtension.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.fileExtension.event.FileExtensionFound;
import com.skytala.eCommerce.domain.fileExtension.mapper.FileExtensionMapper;
import com.skytala.eCommerce.domain.fileExtension.model.FileExtension;


public class FindAllFileExtensions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FileExtension> returnVal = new ArrayList<FileExtension>();
try{
List<GenericValue> results = delegator.findAll("FileExtension", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FileExtensionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FileExtensionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

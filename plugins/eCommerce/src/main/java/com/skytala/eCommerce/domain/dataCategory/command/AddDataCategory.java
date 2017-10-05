package com.skytala.eCommerce.domain.dataCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.dataCategory.event.DataCategoryAdded;
import com.skytala.eCommerce.domain.dataCategory.mapper.DataCategoryMapper;
import com.skytala.eCommerce.domain.dataCategory.model.DataCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataCategory extends Command {

private DataCategory elementToBeAdded;
public AddDataCategory(DataCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataCategory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDataCategoryId(delegator.getNextSeqId("DataCategory"));
GenericValue newValue = delegator.makeValue("DataCategory", elementToBeAdded.mapAttributeField());
addedElement = DataCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

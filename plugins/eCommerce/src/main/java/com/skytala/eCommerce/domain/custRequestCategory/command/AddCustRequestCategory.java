package com.skytala.eCommerce.domain.custRequestCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.custRequestCategory.event.CustRequestCategoryAdded;
import com.skytala.eCommerce.domain.custRequestCategory.mapper.CustRequestCategoryMapper;
import com.skytala.eCommerce.domain.custRequestCategory.model.CustRequestCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestCategory extends Command {

private CustRequestCategory elementToBeAdded;
public AddCustRequestCategory(CustRequestCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestCategory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCustRequestCategoryId(delegator.getNextSeqId("CustRequestCategory"));
GenericValue newValue = delegator.makeValue("CustRequestCategory", elementToBeAdded.mapAttributeField());
addedElement = CustRequestCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}

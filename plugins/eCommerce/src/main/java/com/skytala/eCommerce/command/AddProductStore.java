package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductStore;
import com.skytala.eCommerce.event.ProductStoreAdded;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class AddProductStore implements Command {

private ProductStore elementToBeAdded;
public AddProductStore(ProductStore elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public void execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try {
elementToBeAdded.setProductStoreId(delegator.getNextSeqId("ProductStore"));
GenericValue newValue = delegator.makeValue("ProductStore", elementToBeAdded.mapAttributeField());
delegator.create(newValue);
success = true;
} catch(GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}

Broker.instance().publish(new ProductStoreAdded(elementToBeAdded,success));
}
}

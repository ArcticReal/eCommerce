package com.skytala.eCommerce.domain.product.relations.product.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.attribute.ProductAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductAttribute extends Command {

    private ProductAttribute elementToBeUpdated;

    public UpdateProductAttribute(ProductAttribute elementToBeUpdated){
        this.elementToBeUpdated = elementToBeUpdated;
    }
    public ProductAttribute getElementToBeUpdated() {
        return elementToBeUpdated;
    }
    public void setElementToBeUpdated(ProductAttribute elementToBeUpdated){
        this.elementToBeUpdated = elementToBeUpdated;
    }

    @Override
    public Event execute() throws RecordNotFoundException{


        Delegator delegator = DelegatorFactory.getDelegator("default");


        boolean success;
        try{
            GenericValue newValue = delegator.makeValue("ProductAttribute", elementToBeUpdated.mapAttributeField());
            if(delegator.createOrStore(newValue) == null) {

                throw new RecordNotFoundException(ProductAttribute.class);
            }
            success = true;
        } catch (GenericEntityException e) {
            e.printStackTrace();
            if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
                throw new RecordNotFoundException(ProductAttribute.class);
            }
            success = false;
        }
        Event resultingEvent = new ProductAttributeUpdated(success);
        Broker.instance().publish(resultingEvent);
        return resultingEvent;
    }
}

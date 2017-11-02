package com.skytala.eCommerce.domain.order;


import com.skytala.eCommerce.domain.cart.Position;
import com.skytala.eCommerce.domain.cart.ShoppingCart;
import com.skytala.eCommerce.domain.cart.dto.ShoppingCartItemDTO;
import com.skytala.eCommerce.domain.order.dto.OrderDetailsDTO;
import com.skytala.eCommerce.domain.order.dto.OrderListItemDTO;
import com.skytala.eCommerce.domain.order.relations.orderHeader.OrderHeaderController;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderItem.OrderItemController;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;
import com.skytala.eCommerce.domain.party.PartyController;
import com.skytala.eCommerce.domain.party.model.Party;
import com.skytala.eCommerce.domain.party.relations.contactMech.ContactMechController;
import com.skytala.eCommerce.domain.party.relations.contactMech.control.party.PartyContactMechController;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;
import com.skytala.eCommerce.domain.party.relations.person.mapper.PersonMapper;
import com.skytala.eCommerce.domain.party.relations.person.model.Person;
import com.skytala.eCommerce.domain.party.relations.postalAddress.PostalAddressController;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
import com.skytala.eCommerce.domain.product.ProductController;
import com.skytala.eCommerce.domain.product.dto.ProductListItemDTO;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.domain.shipment.ShipmentController;
import com.skytala.eCommerce.domain.shipment.model.Shipment;
import net.sf.ehcache.util.TimeUtil;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Resource
    PartyController partyController;

    @Resource
    ProductController productController;

    @Resource
    ShipmentController shipmentController;

    @Resource
    OrderHeaderController orderHeaderController;

    @Resource
    OrderItemController orderItemController;

    @Resource
    ContactMechController contactMechController;

    @Resource
    PartyContactMechController partyContactMechController;

    @Resource
    PostalAddressController postalAddressController;


    @RequestMapping("/geos")
    public Object listGeos() throws GenericEntityException {
        return DelegatorFactory.getDelegator("default").findAll("Geo", false);
    }

    @RequestMapping("/orderList")
    public ResponseEntity<List<OrderListItemDTO>> getOrderList(HttpSession session) throws Exception {


        GenericValue personVal = (GenericValue)session.getAttribute("person");
        Person person;

        if(personVal!=null){

            person = PersonMapper.map(personVal);

        }else{
            return unauthorized();
        }

        Map<String, String> filter = UtilMisc.toMap("partyIdTo", person.getPartyId());

        List<Shipment> shipments = shipmentController.findShipmentsBy(filter).getBody();

        filter.clear();
        List<OrderHeader> headers = shipments.stream().map(shipment -> {
            try {
                return orderHeaderController.findById(shipment.getPrimaryOrderId()).getBody();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());


        while (headers.remove(null));

        List<OrderListItemDTO> result = headers.stream().map(OrderListItemDTO::new).collect(Collectors.toList());

        return successful(result);
    }

    @RequestMapping("/{orderHeaderId}/details")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(HttpSession session,
                                                           @PathVariable("orderHeaderId") String orderHeaderId) throws Exception {



        OrderHeader header = orderHeaderController.findById(orderHeaderId).getBody();

        List<Shipment> shipments = shipmentController.findShipmentsBy(UtilMisc.toMap("primaryOrderId", orderHeaderId)).getBody();
        Party party = null;

        if(shipments.size()==1){

            party = partyController.findById(shipments.get(0).getPartyIdTo()).getBody();

            Person person = PersonMapper.map((GenericValue)session.getAttribute("person"));
            if(person == null || !party.getPartyId().equals(person.getPartyId()))
                return unauthorized();

        }else
            return serverError();


        List<ContactMech> contactMechs = new LinkedList<>();
        PostalAddress address = null;

        if(party!=null && shipments.get(0).getDestinationContactMechId()==null) {


            List<PartyContactMech> partyContactMechs = partyContactMechController.findPartyContactMechsBy(UtilMisc.toMap("partyId", party.getPartyId()))
                    .getBody();

            List<String> contactMechIds = partyContactMechs.stream()
                    .map(partyContactMech -> {
                        return partyContactMech.getContactMechId();
                    }).collect(Collectors.toList());


            contactMechs = contactMechIds.stream().map(contactMechId -> {
                try {
                    ContactMech contactMech = contactMechController.findById(contactMechId).getBody();
                    if (contactMech.getContactMechTypeId().equals("POSTAL_ADDRESS"))
                        return contactMech;
                    else
                        return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());

            while (contactMechs.remove(null));

            if(contactMechs.size()<1)
                return badRequest();

            address = postalAddressController.findById(contactMechs.get(0).getContactMechId()).getBody();
        }else{
            String contactMechId = shipments.get(0).getDestinationContactMechId();
            if(contactMechId != null)
                address = postalAddressController.findById(contactMechId).getBody();
            else
                return badRequest();
        }


        List<OrderItem> items = orderItemController.findOrderItemsBy(UtilMisc.toMap("orderId", header.getOrderId()))
                                                   .getBody();



        List<ShoppingCartItemDTO> products = new LinkedList<>();

        for(OrderItem t : items){

            products.add(ShoppingCartItemDTO.create(productController.findByIdWithDetails(t.getProductId()).getBody(),
                                                    t.getQuantity()));
        }
        

        return successful(OrderDetailsDTO.create(header, address, products, header.getGrandTotal()));
    }


    @RequestMapping("/finish")
    public ResponseEntity<OrderDetailsDTO> finishOrder(HttpSession session, PostalAddress address) throws Exception {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(cart == null)
            return badRequest();

        //person can be null after this operation a null person indicates the user is not logged in
        Person person = PersonMapper.map((GenericValue)session.getAttribute("person"));


        //set general orderheader attributes
        OrderHeader headerToBeAdded = new OrderHeader();
        headerToBeAdded.setCurrencyUom("EUR");
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        currentTime.setNanos(0);
        headerToBeAdded.setOrderDate(currentTime);
        headerToBeAdded.setGrandTotal(cart.getGrandTotal());
        headerToBeAdded.setOrderTypeId("SALES_ORDER");
        headerToBeAdded.setProductStoreId("SKYTALA_DIETMANNSR.");
        headerToBeAdded.setStatusId("ORDER_PROCESSING");
        headerToBeAdded.setSalesChannelEnumId("WEB_SALES_CHANNEL");

        OrderHeader addedHeader = orderHeaderController.createOrderHeader(headerToBeAdded).getBody();
        if(addedHeader == null)
            return conflict();
        String orderId = addedHeader.getOrderId();

        //Set general orderItem attributes
        OrderItem itemToBeCreated = new OrderItem();
        itemToBeCreated.setOrderId(orderId);
        itemToBeCreated.setOrderItemTypeId("PRODUCT_ORDER_ITEM");

        List<ShoppingCartItemDTO> products = new LinkedList<>();

        for(Position p : cart.getPositions()){
            itemToBeCreated.setProductId(p.getProduct().getProductId());
            itemToBeCreated.setQuantity(p.getNumberProducts());
            orderItemController.createOrderItem(itemToBeCreated);
            ShoppingCartItemDTO dto = ShoppingCartItemDTO
                    .create(productController.findByIdWithDetails(p.getProduct()
                                                                   .getProductId())
                                                                   .getBody(),
                            p.getNumberProducts());
            products.add(dto);
        }

        PostalAddress addedAddress = address;
        if(person == null){

            ContactMech mechToBeAdded = new ContactMech();
            mechToBeAdded.setContactMechTypeId("POSTAL_ADDRESS");
            ContactMech addedMech = contactMechController.createContactMech(mechToBeAdded).getBody();

            address.setContactMechId(addedMech.getContactMechId());
            addedAddress = postalAddressController.createPostalAddress(address).getBody();
            person = new Person();
        }
        if(address.getContactMechId()==null) {


            ContactMech mechToBeAdded = new ContactMech();
            mechToBeAdded.setContactMechTypeId("POSTAL_ADDRESS");
            ContactMech addedMech = contactMechController.createContactMech(mechToBeAdded).getBody();

            PartyContactMech partyContactMech = new PartyContactMech();
            partyContactMech.setPartyId(person.getPartyId());
            partyContactMech.setContactMechId(addedMech.getContactMechId());

            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            currentDate.setNanos(0);
            partyContactMech.setFromDate(currentDate);
            partyContactMechController.createPartyContactMech(partyContactMech);

            address.setContactMechId(addedMech.getContactMechId());
            addedAddress = postalAddressController.createPostalAddress(address).getBody();

        }else{
            addedAddress = postalAddressController.findById(address.getContactMechId()).getBody();
        }
        Shipment shipmentToBeAdded = new Shipment();
        shipmentToBeAdded.setPrimaryOrderId(orderId);
        shipmentToBeAdded.setCurrencyUomId("EUR");
        shipmentToBeAdded.setPartyIdTo(person.getPartyId());
        shipmentToBeAdded.setDestinationContactMechId(addedAddress.getContactMechId());
        shipmentController.createShipment(shipmentToBeAdded);



        return successful(OrderDetailsDTO.create(addedHeader, addedAddress, products, cart.getGrandTotal()));
    }


}
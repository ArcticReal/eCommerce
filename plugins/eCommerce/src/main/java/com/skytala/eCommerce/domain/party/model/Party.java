package com.skytala.eCommerce.domain.party.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.io.Serializable;

import com.skytala.eCommerce.domain.party.event.PartyFound;
import com.skytala.eCommerce.domain.party.mapper.PartyMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role.ProdCatalogRole;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Scheduler;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;

import javax.servlet.http.HttpSession;

public class Party implements Serializable {

    private static final long serialVersionUID = 1L;
    private String partyId;
    private String partyTypeId;
    private String externalId;
    private String preferredCurrencyUomId;
    private String description;
    private String statusId;
    private Timestamp createdDate;
    private String createdByUserLogin;
    private Timestamp lastModifiedDate;
    private String lastModifiedByUserLogin;
    private String dataSourceId;
    private Boolean isUnread;

    private Boolean equalsDatabaseRecord;
    private final Query queryToGetData;

    /*
        Constructors

     */

    /*
        Constructor for Party that will be completely filled with the data from the DataBase.
        Please make sure to set the fields

     */
    public Party() {
        queryToGetData = null;
        equalsDatabaseRecord = true;
    }

    /*
        Constructor for Party Prototype that only has the ID.
        You need to specify a Query that will be able to find Parties from the Database for the queryToGetData parameter.

     */
    public Party(String partyId, Query queryToGetData) {
        this.partyId = partyId;
        this.queryToGetData = queryToGetData;
        equalsDatabaseRecord = false;
    }


    /*
        Getters and setters

     */

    public String getPartyId() {

        return partyId;
    }

    public void setPartyId(String  partyId) {
    this.partyId = partyId;
    }

    public String getPartyTypeId() {
        reload();
        return partyTypeId;
    }


    public void setPartyTypeId(String  partyTypeId) {
        reload();
    this.partyTypeId = partyTypeId;
    }

    public String getExternalId() {
        reload();
        return externalId;
    }

    public void setExternalId(String  externalId) {
    this.externalId = externalId;
    }

    public String getPreferredCurrencyUomId() {
        reload();
        return preferredCurrencyUomId;
    }

    public void setPreferredCurrencyUomId(String  preferredCurrencyUomId) {
        this.preferredCurrencyUomId = preferredCurrencyUomId;
    }

    public String getDescription() {
        reload();
        return description;
    }

    public void setDescription(String  description) {
    this.description = description;
    }

    public String getStatusId() {
        reload();
        return statusId;
    }

    public void setStatusId(String  statusId) {
    this.statusId = statusId;
    }

    public Timestamp getCreatedDate() {
        reload();
        return createdDate;
    }

    public void setCreatedDate(Timestamp  createdDate) {
    this.createdDate = createdDate;
    }

    public String getCreatedByUserLogin() {
        reload();
        return createdByUserLogin;
    }

    public void setCreatedByUserLogin(String  createdByUserLogin) {
    this.createdByUserLogin = createdByUserLogin;
    }

    public Timestamp getLastModifiedDate() {
        reload();
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp  lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedByUserLogin() {
        reload();
        return lastModifiedByUserLogin;
    }

    public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
        this.lastModifiedByUserLogin = lastModifiedByUserLogin;
    }

    public String getDataSourceId() {
        reload();
        return dataSourceId;
    }

    public void setDataSourceId(String  dataSourceId) {
    this.dataSourceId = dataSourceId;
    }

    public Boolean getIsUnread() {
        reload();
        return isUnread;
    }

    public void setIsUnread(Boolean  isUnread) {
    this.isUnread = isUnread;
    }


    /*
        Domain specific methods

     */

    private void reload() {
        if(!equalsDatabaseRecord) {
            try {
                this.fill(queryToGetData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            equalsDatabaseRecord = true;
        }
    }

    private void fill(Query queryToGetData) throws Exception {
        List<Party> partys =((PartyFound) Scheduler.execute(queryToGetData).data()).getPartys();
        Party party = new Party();
        if(partys != null && partys.size()==1){
            party = partys.get(0);
        }else{
            throw new Exception("No specific party found");
        }

        this.setPartyId(party.getPartyId());
        this.setPartyTypeId(party.getPartyTypeId());
        this.setExternalId(party.getExternalId());
        this.setPreferredCurrencyUomId(party.getPreferredCurrencyUomId());
        this.setDescription(party.getDescription());
        this.setStatusId(party.getStatusId());
        this.setCreatedDate(party.getCreatedDate());
        this.setCreatedByUserLogin(party.getCreatedByUserLogin());
        this.setLastModifiedDate(party.getLastModifiedDate());
        this.setLastModifiedByUserLogin(party.getLastModifiedByUserLogin());
        this.setDataSourceId(party.getDataSourceId());
        this.setIsUnread(party.getIsUnread());

    }


    public Map<String, Object> mapAttributeField() {
        return PartyMapper.map(this);
    }

    /*
        Service Methods

     */

    public void addProductCatalog(HttpSession session, ProdCatalogRole role) throws GenericServiceException {
        // convert params that we are compatible with ofbiz
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleTypeId",role.getRoleTypeId());
        paramMap.put("partyId", role.getPartyId());
        paramMap.put("prodCatalogId", role.getProdCatalogId());
        paramMap.put("fromDate", role.getFromDate());
        paramMap.put("sequenceNum", role.getSequenceNum());
        paramMap.put("thruDate", role.getThruDate());
        paramMap.put("userLogin", session.getAttribute("userLogin"));
        LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
        Map<String, Object> result = dispatcher.runSync("addProdCatalogToParty", paramMap);

        if(result.get("responseMessage").equals("error"))
            throw new IllegalArgumentException("Ofbiz was not able to process the data");

    }
}

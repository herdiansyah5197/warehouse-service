package id.co.warehouse.application.service.imp;

import id.co.warehouse.application.constant.MessageConstant;
import id.co.warehouse.application.dto.inventory.GetInventoryStockResponse;
import id.co.warehouse.application.dto.inventory.SaveInventoryRequest;
import id.co.warehouse.application.dto.inventory.SaveInventoryResponse;
import id.co.warehouse.application.exception.ErrorBussinessException;
import id.co.warehouse.application.exception.GeneralErrorException;
import id.co.warehouse.application.model.Inventory;
import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.repository.InventoryRepository;
import id.co.warehouse.application.repository.ItemRepository;
import id.co.warehouse.application.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryServiceImp implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public SaveInventoryResponse saveInventory(SaveInventoryRequest saveInventoryRequest) {
        SaveInventoryResponse response = SaveInventoryResponse.builder().build();
        try {
            var respItm = itemRepository.findById(saveInventoryRequest.getIdItem());
            if (!respItm.isPresent()) {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
            inventoryRepository.saveAndFlush(populateNewDataItem(saveInventoryRequest));
            response.setSuccess(true);
        } catch (GeneralErrorException e) {
            log.error("error save data");
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public GetInventoryStockResponse getStockByIdItem(Long id) {
        GetInventoryStockResponse response = GetInventoryStockResponse.builder().build();
        try {
            response.setIdItem(id);
            var respItm = itemRepository.findById(id);
            if (!respItm.isPresent()) {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
            if (respItm.isPresent()) {
                Item item = (Item) respItm.get();
                response.setNameItem(item.getName());
                var respDB = inventoryRepository.findStockByIdItem(id);
                if(respDB.isPresent()){
                response.setStockQty(Math.toIntExact(respDB.get()));
                }else {
                    response.setStockQty(0);
                }
            } else {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
        } catch (GeneralErrorException e) {
            log.error("error get data");
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public Inventory getinventory(Long id) {
        return null;
    }

    protected Inventory populateNewDataItem(SaveInventoryRequest request) {
        Inventory inventory = new Inventory();
        Item item = new Item();
        item.setId(request.getIdItem());
        inventory.setItem(item);
        inventory.setQty(request.getQty());
        inventory.setType(request.getType());
        return inventory;
    }
}

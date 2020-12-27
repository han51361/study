package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private PartnerRepository partnerRepository;


    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        // 1.  request data
        ItemApiRequest itemApiRequest = request.getData();

        //2 . item 생성
        Item item  = Item.builder()
                .status(itemApiRequest.getStatus())
                .name(itemApiRequest.getName())
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .brandName(itemApiRequest.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(itemApiRequest.getPartnerId()))
                .build();

        Item newItem = baseRepository.save(item);

        //3. 생성된 데이터 -> itemRepository return

        return response(newItem);

    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        // id -> repository getOne , getBy id

        return baseRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(// 없다면
                        ()-> Header.ERROR("No data"));
                        // );

    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        //1. data
        ItemApiRequest itemApiRequest = request.getData();

        //2. id -> item

        Optional<Item> optional = baseRepository.findById(itemApiRequest.getId());

        return optional.map(item->{

            item.setStatus(itemApiRequest.getStatus())
                    .setName(itemApiRequest.getName())
                    .setTitle(itemApiRequest.getTitle())
                    .setContent(itemApiRequest.getContent())
                    .setPrice(itemApiRequest.getPrice())
                    .setBrandName(itemApiRequest.getBrandName())
                    .setRegisteredAt(itemApiRequest.getRegisteredAt())
                    .setUnregisteredAt(itemApiRequest.getUnregisteredAt());


            return item;
        })
        .map(item -> baseRepository.save(item))
                .map(update -> response(update))
                .orElseGet(() -> Header.ERROR("No data"));

    }

    @Override
    public Header delete(Long id) {
        // id -> repository -> item

        Optional<Item> optional  = baseRepository.findById(id);

        //repository -> delete

        return  optional.map(item -> {
            baseRepository.delete(item);

            return Header.OK();
        }).orElseGet(() -> Header.ERROR("No data"));

        //response return

    }


    private Header<ItemApiResponse> response(Item item){

        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();


        return Header.OK(itemApiResponse);
    }
}

package com.example.study.Controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/ordergroup")
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {


    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {

        return null;
    }

    @Override
    @GetMapping({"id"})
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        log.info("read id : {}", id);
        return null;
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody  Header<OrderGroupApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping({"id"})
    public Header delete(@PathVariable Long id) {
        log.info("delete : {} ", id );
        return null;
    }
}

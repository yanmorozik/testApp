package com.morozik.app.controller;

import com.morozik.app.dto.CreateMyEntityDto;
import com.morozik.app.dto.DeleteByNameDto;
import com.morozik.app.dto.NamesDto;
import com.morozik.app.dto.ResponseDto;
import com.morozik.app.dto.ResponseDtoWithSum;
import com.morozik.app.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CreateMyEntityDto dto) {
        return ok(myService.create(dto));
    }

    @PostMapping("/remove")
    public ResponseEntity<ResponseDto> deleteByName(@Valid @RequestBody DeleteByNameDto dto) {
        return ok(myService.deleteByName(dto.getName()));
    }

    @PostMapping("/sum")
    public ResponseEntity<ResponseDtoWithSum> getSumByNames(@Valid @RequestBody NamesDto namesDto) {
        return ok(myService.getSumByNames(namesDto));
    }
}

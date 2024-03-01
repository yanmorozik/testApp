package com.morozik.app.service;

import com.morozik.app.dto.CreateMyEntityDto;
import com.morozik.app.dto.NamesDto;
import com.morozik.app.dto.ResponseDto;
import com.morozik.app.dto.ResponseDtoWithSum;
import com.morozik.app.exception.BadRequestException;
import com.morozik.app.exception.NotFoundException;
import com.morozik.app.model.MyEntity;
import com.morozik.app.repository.MyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyService {

    private final MyRepository myRepository;

    @Transactional
    public ResponseDto create(CreateMyEntityDto dto) {
        if (assertNameIsExist(dto.getName())) {
            throw new BadRequestException("Duplicate name", 400);
        }

        myRepository.save(
                MyEntity.builder()
                        .intValue(dto.getValue())
                        .stringValue(dto.getName())
                        .build()
        );

        return ResponseDto.builder()
                .code(0)
                .description("OK")
                .build();
    }

    @Transactional
    public ResponseDto deleteByName(String name) {
        if (!assertNameIsExist(name)) {
            throw new NotFoundException("User with such name not present", 404);
        }

        myRepository.deleteByStringValue(name);

        return ResponseDto.builder()
                .code(0)
                .description("OK")
                .build();
    }

    @Transactional(readOnly = true)
    public ResponseDtoWithSum getSumByNames(NamesDto namesDto) {
        return ResponseDtoWithSum.builder()
                .code(0)
                .description("OK")
                .sum(findValue(namesDto.getFirstName()) + findValue(namesDto.getSecondName()))
                .build();
    }

    @Transactional(readOnly = true)
    public int findValue(String name) {
        return myRepository.findIntValueByStringValue(name)
                .orElseThrow(() -> new NotFoundException("User with name: '" + name + "' not present", 404));
    }

    private boolean assertNameIsExist(String name) {
        return myRepository.existsByStringValue(name);
    }
}

package com.morozik.app.pototype;

import com.morozik.app.dto.CreateMyEntityDto;
import com.morozik.app.dto.NamesDto;
import com.morozik.app.model.MyEntity;

public class Prototype {

    public static MyEntity getMyEntity() {
        return MyEntity.builder()
                .intValue(0)
                .stringValue("test")
                .build();
    }

    public static CreateMyEntityDto getCreateMyEntityDto() {
        return CreateMyEntityDto.builder()
                .value(0)
                .name("test")
                .build();
    }

    public static NamesDto getNamesDto() {
        return NamesDto.builder()
                .firstName("firstNameTest")
                .secondName("secondNameTest")
                .build();
    }
}

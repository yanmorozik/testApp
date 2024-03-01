package com.morozik.app.service;

import com.morozik.app.dto.ResponseDto;
import com.morozik.app.dto.ResponseDtoWithSum;
import com.morozik.app.exception.BadRequestException;
import com.morozik.app.exception.NotFoundException;
import com.morozik.app.repository.MyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.morozik.app.pototype.Prototype.getCreateMyEntityDto;
import static com.morozik.app.pototype.Prototype.getMyEntity;
import static com.morozik.app.pototype.Prototype.getNamesDto;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyServiceTest {

    @Mock
    private MyRepository myRepository;

    @InjectMocks
    private MyService myService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void createTest() {
        when(myRepository.save(any())).thenReturn(getMyEntity());

        ResponseDto responseDto = myService.create(getCreateMyEntityDto());

        assertThat(responseDto.getCode()).isEqualTo(0);
        assertThat(responseDto.getDescription()).isEqualTo("OK");
    }

    @Test
    void throwExceptionWhileCreateWithSameNameTest() {
        when(myRepository.existsByStringValue(any())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> myService.create(getCreateMyEntityDto()));
    }

    @Test
    void deleteTest() {
        doNothing().when(myRepository).deleteByStringValue(any());
        when(myRepository.existsByStringValue(any())).thenReturn(true);

        myService.deleteByName("test");

        verify(myRepository, times(1)).deleteByStringValue("test");
    }

    @Test
    void throwExceptionIfDeletedUserNotExistTest() {
        when(myRepository.existsByStringValue(any())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> myService.deleteByName(any()));
    }

    @Test
    void getSumByNamesTest() {
        when(myRepository.findIntValueByStringValue(anyString())).thenReturn(of(1));

        ResponseDtoWithSum responseDtoWithSum = myService.getSumByNames(getNamesDto());

        assertThat(responseDtoWithSum.getCode()).isEqualTo(0);
        assertThat(responseDtoWithSum.getDescription()).isEqualTo("OK");
        assertThat(responseDtoWithSum.getSum()).isEqualTo(2);
    }

    @Test
    void throwExceptionIfValueCantFindByNameTest() {
        when(myRepository.findIntValueByStringValue(anyString())).thenReturn(empty());

        assertThrows(NotFoundException.class, () -> myService.getSumByNames(getNamesDto()));
    }
}

package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.mocks.MockAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {
    MockAccount inputObject;

    @BeforeEach
    public void setUp(){
        inputObject = new MockAccount();
    }

    @Test
    @DisplayName("It should convert an object into a DTO object.")
    public void parseEntityToDtoTest(){
        Account input = inputObject.mockEntity();
        AccountResponseDto output = Mapper.map(input, AccountResponseDto.class);
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getUsername(),output.getUsername());
        assertEquals(input.getEmail(),output.getEmail());
        assertEquals(input.getProfileImage(),output.getProfileImage());
    }

    @Test
    @DisplayName("It should convert a list of object into a list of DTO object.")
    public void parseDtoToEntityTest(){
        List<Account> input = inputObject.mockEntityList();
        List<AccountResponseDto> output = Mapper.mapList(input, AccountResponseDto.class);
        Account inputZero = input.get(0);
        AccountResponseDto outputZero = output.get(0);
        assertEquals(inputZero.getId(), outputZero.getId());
        assertEquals(inputZero.getUsername(),outputZero.getUsername());
        assertEquals(inputZero.getEmail(),outputZero.getEmail());
        assertEquals(inputZero.getProfileImage(),outputZero.getProfileImage());
    }
}
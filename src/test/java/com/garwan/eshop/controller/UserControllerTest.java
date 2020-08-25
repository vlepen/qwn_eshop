package com.garwan.eshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garwan.eshop.mapper.EshopUserMapper;
import com.garwan.eshop.model.CreateUserRequest;
import com.garwan.eshop.model.EshopUser;
import com.garwan.eshop.rest.ApiError;
import com.garwan.eshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@Import(RestControllerTestConfig.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUser() throws Exception {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
            .username("username")
            .password("password")
            .email("email@email.com")
            .build();
        EshopUser expectedEshopUser = EshopUserMapper.fromUserEntity(EshopUserMapper.toUserEntity(
            createUserRequest,
            "encrypted_password"
            )
                .toBuilder()
                .id(1L)
                .build()
        );
        when(userService.create(createUserRequest)).thenReturn(expectedEshopUser);

        MvcResult actualResult = mockMvc.perform(post("/users")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(createUserRequest))
        )
            .andExpect(status().isOk())
            .andReturn();

        verify(userService).create(createUserRequest);
        String actualResponseBody = actualResult.getResponse().getContentAsString();
        EshopUser actualEshopUser = objectMapper.readValue(actualResponseBody, EshopUser.class);
        assertThat(actualEshopUser).isEqualTo(expectedEshopUser);
    }

    @Test
    void shouldReturnHttpBadRequestWhenCreatingUserWithNullValues() throws Exception {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
            .username(null)
            .password(null)
            .email(null)
            .build();

        MvcResult actualResult = mockMvc.perform(post("/users")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(createUserRequest))
        )
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(userService, never()).create(createUserRequest);
        String actualResponseBody = actualResult.getResponse().getContentAsString();
        ApiError actualApiError = objectMapper.readValue(actualResponseBody, ApiError.class);
        assertThat(actualApiError.getStatus()).isEqualTo(BAD_REQUEST);
        assertThat(actualApiError.getSubErrors()).hasSize(3);
    }
}
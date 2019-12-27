package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ResturantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ResturantController.class)
public class ResturantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private  ResturantService resturantService;




    @Test
    public void list() throws Exception {
        List<Resturant> resturants= new ArrayList<>();
        resturants.add(Resturant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build());
        given(resturantService.getResturants()).willReturn(resturants);
        mvc.perform(get("/resturants"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Bob zip\"")));
    }
    @Test
    public  void detailWithExisted() throws Exception {
        Resturant resturant1=Resturant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        Resturant resturant2=Resturant.builder()
                .id(2020L)
                .address("Seoul")
                .name("Cyber Food")
                .build();

        resturant1.setMenuItems(Arrays.asList(MenuItem.builder().name("Kimchi").build()));
       given(resturantService.getResturant(1004L)).willReturn(resturant1);
        given(resturantService.getResturant(2020L)).willReturn(resturant2);
        mvc.perform(get("/resturants/1004"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Bob zip\"")))
                .andExpect(content().string(containsString("Kimchi")));

        mvc.perform(get("/resturants/2020"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":2020")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Cyber Food\"")));
    }
    @Test
    public void detailWithNotExisted() throws  Exception{
        given(resturantService.getResturant(404L)).willThrow(new ResturantNotFoundException(404L));
        mvc.perform(get("/resturants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

    @Test
    public void createWithValidData() throws Exception {
        given(resturantService.addResturant(any())).will(invocation->{
            Resturant resturant=invocation.getArgument(0);
            return Resturant.builder()
                    .id(1234L)
                    .name(resturant.getName())
                    .address(resturant.getAddress())
                    .build();
        });

        mvc.perform(post("/resturants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/resturants/1234"))
                .andExpect(content().string("{}"));
        verify(resturantService).addResturant(any());// 뭐든 제대로 들어오면 통과
    }

    @Test
    public void createWithInvalidData() throws Exception {
        mvc.perform(post("/resturants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());

    }



    @Test
    public void updateWithVaildData() throws Exception {
        mvc.perform(patch("/resturants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Joker Bar\",\"address\":\"Seoul\"}"))
                .andExpect(status().isOk());
        verify(resturantService).updateResturant(1004L,"Joker Bar","Seoul");
    }

    @Test
    public void updateWithInvaildData() throws Exception {
        mvc.perform(patch("/resturants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void updateWithoutName() throws Exception {
        mvc.perform(patch("/resturants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"Busan\"}"))
                .andExpect(status().isBadRequest());
    }


}
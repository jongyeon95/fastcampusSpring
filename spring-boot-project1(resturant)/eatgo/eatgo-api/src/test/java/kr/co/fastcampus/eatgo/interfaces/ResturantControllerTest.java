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
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        resturants.add(new Resturant(1004L,"Bob zip","Seoul"));
        given(resturantService.getResturants()).willReturn(resturants);
        mvc.perform(get("/resturants"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Bob zip\"")));
    }
    @Test
    public  void detail() throws Exception {
       Resturant resturant1=new Resturant(1004L,"Bob zip","Seoul");
        Resturant resturant2=new Resturant(2020L,"Cyber Food","Seoul");

        resturant1.addMenuItem(new MenuItem("Kimchi"));
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
    public void create() throws Exception {

        mvc.perform(post("/resturants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/resturants/1234"))
                .andExpect(content().string("{}"));
        verify(resturantService).addResturant(any());// 뭐든 제대로 들어오면 통과
    }


}
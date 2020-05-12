package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.OrderGroup;

import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;

import org.springframework.web.bind.annotation.*;


// @RestController 어노테이션은 스프링 4점대 버전부터 지원하는 어노테이션으로, 컨트롤러 클래스에 @RestController 만 붙이면 메서드에 @ResponseBody 어노테이션을 붙이지 않아도 문자열과 JSON 등을 전송할 수 있습니다. 뷰를 리턴하는 메서드들을 가지고 있는 @Controller와는 다르게 @RestController는 문자열, 객체 등을 리턴하는 메서드들을 가지고 있습니다.
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {



}

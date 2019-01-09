package com.controller;

import com.elasticsearch.ElasticsearchServiceImpl;
import com.service.HelloServiceImpl;
import com.service.HelloServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @Autowired
    private HelloServiceImpl helloService;

    @Autowired
    private HelloServiceImpl2 helloService2;

    @Autowired
    private ElasticsearchServiceImpl elasticsearchService;

    @RequestMapping("/master")
    public String hello() {
        return helloService.hello();
    }

    @RequestMapping("/slave")
    public String hello2() {
        return helloService2.hello();
    }

    @RequestMapping("/search")
    public Object search(@PageableDefault Pageable pageable, String keyword) {
        return elasticsearchService.demo(pageable,keyword);
    }
}

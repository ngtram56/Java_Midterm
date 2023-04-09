package com.midterm.midtram.controller;

import com.midterm.midtram.model.Order;
import com.midterm.midtram.model.OrderDetail;
import com.midterm.midtram.model.Product;
import com.midterm.midtram.service.OrderDetailService;
import com.midterm.midtram.service.OrderService;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class OrderController {
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @RequestMapping("/order-history")
    public String orderHistory(Model model) {
        Object s = session.getAttribute("user");
        List<Order> lo = orderService.getAllByUsername(String.valueOf(s));
        model.addAttribute("orders", lo);
        return "order-history";
    }

    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestParam String user, @RequestParam String pid, @RequestParam String num_pid) throws ParseException {
        System.out.println(user);
        pid = pid.substring(1, pid.length()-1);
        num_pid = num_pid.substring(1, num_pid.length()-1);
        String[] ap = pid.split(",");
        OrderDetail ods = new OrderDetail();
        for (int i = 0; i < ap.length; i++) {
            ap[i] = ap[i].substring(1,ap[i].length()-1);
            System.out.println(ap[i]);
            ods.setProduct_id(Integer.parseInt(ap[i]));
            Order order = new Order();
            order.setUsername(user);
            order.setDate(new Date());
            order.setState("Wait");
            Order o = orderService.add(order);
            ods.setOrders_id(o.getId());
        }
        String[] an = num_pid.split(",");
        for (int i = 0; i < an.length; i++) {
            System.out.println(an[i]);
            ods.setQuantity(Integer.parseInt(an[i]));
        }
        orderDetailService.add(ods);

        try {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("error", HttpStatus.OK);
        }
    }
}

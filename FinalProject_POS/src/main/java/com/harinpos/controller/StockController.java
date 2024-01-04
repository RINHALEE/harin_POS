package com.harinpos.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.harinpos.stock.*;
import com.harinpos.product.*;

@Controller
@ComponentScan({ "com.harinpos.product", "com.harinpos.stock" })
public class StockController {

	@Autowired
	private StockDao stockDao;
	@Autowired
	private ProductDao productDao;

	// 입고 수량을 받아서 처리하는 메소드
	@PostMapping("/addProduct/{code}")
	public String addProduct(@PathVariable("code") int code, @RequestParam("addP") int quantity) {
		Stock stock = new Stock(code, LocalDateTime.now(), quantity);
		stockDao.insert(stock);
		productDao.updateAddProduct(code, productDao.selectQuantity(code), quantity);
		
		return "redirect:/productControl"; // 입고 후 제품 목록 페이지로 리다이렉트
	}
	
	// 입고 페이지를 나타내는 메서드
	@RequestMapping(value= "/StockManagement", method = { RequestMethod.POST, RequestMethod.GET })
	public String stockManagement(Model model) {
		List<Stock> stocks = stockDao.selectStockAll();
		
		model.addAttribute("stocks", stocks);
		
		return "StockManagement";
	}
	
	
}

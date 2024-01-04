package com.harinpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.harinpos.product.*;
import com.harinpos.sales.*;
import com.harinpos.stock.*;

@Controller
@ComponentScan({ "com.harinpos.product", "com.harinpos.sales", "com.harinpos.stock" })
public class ProductController {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SaleDao saleDao;
	@Autowired
	private StockDao stockDao;

	// 상품 관리 페이지를 표시하는 메서드
	@GetMapping("/productControl")
	public String productControl(Model model) {
		// 사용자 데이터를 조회하는 서비스 클래스의 메서드를 호출하여 사용자 데이터를 가져옴
		List<Product> products = productDao.selectAll();

		// 모델에 사용자 데이터를 추가하여 뷰에서 사용할 수 있도록 함
		model.addAttribute("products", products);

		// 사용자 데이터를 표시하는 뷰(JSP 파일)로 이동
		return null;
	}

	// 상품 삭제 메서드
	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("code") int code) {
		// 상품 삭제 로직
		stockDao.deleteStock(code);
		saleDao.deleteSale(code);
		productDao.deleteProduct(code);

		// 삭제 후 리다이렉트
		return "redirect:/productControl";
	}

	// 상품 수정 페이지를 표시하는 메서드
	@GetMapping("/updateProductF")
	public String updateProductF(@RequestParam("code") int code, Model model) {
		model.addAttribute("code", code);
		return "updateProduct";
	}

	// 상품 정보를 수정하는 메서드
	@PostMapping("/updateProduct")
	public String updateProduct(@RequestParam("code") int code, @RequestParam("quantity") int quantity,
			@RequestParam("name") String name, @RequestParam("price") double price) {
		// 상품 수정 로직
		productDao.updateProduct(code, quantity, name, price);

		// 수정 후 리다이렉트
		return "redirect:/productControl";
	}

	// 상품 검색 메서드
	@PostMapping("/searchProduct")
	public String selectProduct(@RequestParam("name") String name, Model model) {
		List<Product> products = productDao.selectByProductName(name);

		// 모델에 사용자 데이터를 추가하여 뷰에서 사용할 수 있도록 함
		model.addAttribute("products", products);

		// 사용자 데이터를 표시하는 뷰(JSP 파일)로 이동
		return "searchProduct";
	}

	// 상품 등록 페이지를 표시하는 메서드
	@GetMapping("/insertProductF")
	public String insertProductF() {
		return "insertProduct";
	}

	// 상품 등록을 처리하는 메서드
	@RequestMapping(value = "/insertProduct", method = { RequestMethod.POST, RequestMethod.GET })
	public String insertProduct(@RequestParam("code") int code, @RequestParam("quantity") int quantity,
			@RequestParam("name") String name, @RequestParam("price") double price, Model model) {
		Product product = new Product(code, price, quantity, name);
		// 상품 코드로 중복 체크 수행
		if (productDao.existsByCode(code)) {
			// 이미 존재하는 상품 코드인 경우, 등록을 막음
			model.addAttribute("existProduct", "존재하는 상품 코드입니다. 다른 상품 코드를 사용하십시오.");
			return "redirect:/productControl";
		} else {
			productDao.insert(product);
		}

		return "redirect:/productControl"; // 등록 후, 다시 제품 목록 페이지로 리다이렉트
	}

}

package com.harinpos.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harinpos.product.*;
import com.harinpos.sales.*;

@Controller
@ComponentScan({ "com.harinpos.product", "com.harinpos.sales", "com.harinpos.stock" })
public class SellController {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private PurchasedProductDao PPDao;
	@Autowired
	private SaleDao saleDao;

	// 결제 페이지로 이동하는 메소드
	@GetMapping("/sell")
	public String sell(Model model) {
		List<Product> products = productDao.selectAll(); // 모든 제품 목록 조회

		// 모델에 사용자 데이터를 추가하여 뷰에서 사용할 수 있도록 함
		model.addAttribute("products", products);

		return "sell"; // sell.jsp 뷰로 이동
	}

	// 제품을 구매하려는 메소드
	@PostMapping("/purchaseAdd")
	public String AddSellProduct(@RequestParam("name") String name, Model model) {
		if (name != null) {
			List<Product> purchasedProducts = productDao.selectByProductName(name);

			// 구매하려는 제품들을 PurchasedProduct 테이블에 추가
			for (Product product : purchasedProducts) {
				PurchasedProduct purchasedProduct = new PurchasedProduct(product.getCode(), product.getName(),
						product.getPrice(), 1, LocalDateTime.now());
				PPDao.insertPP(purchasedProduct);
			}
		}
		return "redirect:/sell"; // sell 페이지로 리다이렉트
	}

	// 제품 검색을 수행하는 메소드
	@PostMapping("/searchSell")
	public String searchSell(@RequestParam("name") String name, Model model) {
		List<Product> products = productDao.selectByProductName(name);

		// 모델에 product 데이터를 추가하여 뷰에서 사용할 수 있도록 함
		model.addAttribute("products", products);

		return "searchSell"; // searchSell.jsp 뷰로 이동
	}

	// 판매 페이지로 이동하는 메소드
	@RequestMapping("/goSale")
	public String goSale(Model model) {
		List<PurchasedProduct> purchasedProducts = PPDao.selectAll();

		model.addAttribute("products", purchasedProducts); // 모델에 구매하려는 제품 목록 추가

		double extended_price = 0;
		for (PurchasedProduct product : purchasedProducts) {
			extended_price += product.getPrice(); // 총 가격 계산
		}

		model.addAttribute("extended_price", extended_price); // 모델에 총 가격 추가

		return "goSale"; // goSale.jsp 뷰로 이동
	}

	// 메인 페이지로 이동하는 메소드
	@GetMapping("/GoMain")
	public String goMain() {
		PPDao.deleteAll(); // 구매하려는 제품 목록 초기화

		return "main";
	}

	// 판매를 완료하는 메소드
	@GetMapping("/FinishSale")
	public String FSale(Model model) {
		model.addAttribute("pay", "결제가 완료되었습니다."); // 모델에 결제 완료 메시지 추가
		List<PurchasedProduct> purchasedProducts = PPDao.selectAll(); // 구매하려는 제품 목록 조회

		model.addAttribute("products", purchasedProducts); // 모델에 구매한 제품 목록 추가
		for (PurchasedProduct product : purchasedProducts) {
			Sale saleProduct = new Sale(product.getCode(), product.getName(), product.getQuantity(), product.getDate(),
					product.getPrice());
			saleDao.insertSale(saleProduct); // Sale 테이블에 판매 정보 추가
			productDao.updateAddProduct(product.getCode(), productDao.selectQuantity(product.getCode()), -1);
		}

		PPDao.deleteAll(); // 구매하려는 제품 목록 초기화 > cart역할

		return "main"; // main.jsp 뷰로 이동
	}

	// 구매하려는 제품 삭제하는 메소드
	@GetMapping("/PPDelete")
	public String DeleteP(@RequestParam("id") int id, @RequestParam("price") double price,
			@RequestParam("exPrice") double exPrice, Model model) {
		exPrice -= price; // 총 가격에서 삭제된 제품 가격 빼기

		PPDao.deletePP(id); // 구매하려는 제품 삭제 > 장바구니에 넣은 제품 삭제

		model.addAttribute("extended_price", exPrice); // 모델에 총 가격 추가

		return "redirect:/goSale"; // goSale 페이지로 리다이렉트
	}

	// 거래 취소하는 메소드
	@GetMapping("/DeleteAllSale")
	public String DeleteAllS(Model model) {
		model.addAttribute("pay", "결제가 취소되었습니다."); // 모델에 결제 취소 메시지 추가

		PPDao.deleteAll(); // 제품 목록 초기화 > cart 비우기

		return "main"; // main.jsp 뷰로 이동
	}

}
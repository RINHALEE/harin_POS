package com.harinpos.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harinpos.sales.*;
import com.harinpos.statistics.*;

@Controller
public class StatisticsController {
	@Autowired
	private SaleDao saleDao;

	@GetMapping("/dailySales")
	public String showDailySales(Model model) {
		// 제품별 하루 판매량과 매출액 조회
		List<DailySale> dailySales = saleDao.getDailySales();

		model.addAttribute("dailySales", dailySales);
		return "dailySales";
	}

	@GetMapping("/dailySalesData")
	@ResponseBody
	public Map<String, Object> getDailySalesData(@RequestParam("date") String date) {
		// 선택한 날짜의 제품별 하루 판매량과 매출액 조회
		List<DailySale> dailySales = saleDao.getDailySalesByDate(date);

		// 데이터를 맵으로 변환하여 반환
		Map<String, Object> response = new HashMap<>();
		List<Integer> quantityData = new ArrayList<>();
		List<Double> salesData = new ArrayList<>();

		for (DailySale dailySale : dailySales) {
			quantityData.add(dailySale.getDailyQuantity());
			salesData.add(dailySale.getDailySales());
		}

		response.put("quantityData", quantityData);
		response.put("salesData", salesData);

		return response;
	}

	@GetMapping("/weekendSales")
	public String showWeeklySales(Model model) {
		// 주별 판매량과 매출액 조회
		List<DailySale> weeklySales = saleDao.getWeeklySales();

		model.addAttribute("weeklySales", weeklySales);
		return "weekendSales";
	}

	@GetMapping("/weeklySalesData")
	@ResponseBody
	public Map<String, Object> getWeeklySalesData(@RequestParam("date") String date) {
		// 선택한 주의 제품별 판매량과 매출액 조회
		List<DailySale> weeklySales = saleDao.getWeeklySalesByDate(date);

		// 데이터를 맵으로 변환하여 반환
		Map<String, Object> response = new HashMap<>();
		List<Integer> quantityData = new ArrayList<>();
		List<Double> salesData = new ArrayList<>();

		for (DailySale weeklySale : weeklySales) {
			quantityData.add(weeklySale.getDailyQuantity());
			salesData.add(weeklySale.getDailySales());
		}

		response.put("quantityData", quantityData);
		response.put("salesData", salesData);

		return response;
	}

	@GetMapping("/monthlySales")
	public String showMonthlySales(Model model) {
		// 제품별 월별 판매량과 매출액 조회
		List<DailySale> monthSales = saleDao.getMonthlySales();

		model.addAttribute("monthSales", monthSales);
		return "monthSales";
	}

	@GetMapping("/monthlySalesData")
	@ResponseBody
	public Map<String, Object> getMonthlySalesData(@RequestParam("date") String date) {
		// 선택한 날짜의 제품별 월별 판매량과 매출액 조회
		List<DailySale> monthSales = saleDao.getMonthlySalesByDate(date);

		// 데이터를 맵으로 변환하여 반환
		Map<String, Object> response = new HashMap<>();
		List<Integer> quantityData = new ArrayList<>();
		List<Double> salesData = new ArrayList<>();

		for (DailySale monthSale : monthSales) {
			quantityData.add(monthSale.getDailyQuantity());
			salesData.add(monthSale.getDailySales());
		}

		response.put("quantityData", quantityData);
		response.put("salesData", salesData);

		return response;
	}

}

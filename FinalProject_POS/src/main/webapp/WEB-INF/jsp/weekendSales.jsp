<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>주별 판매량 및 매출액</title>
  <!-- Chart.js 라이브러리 추가 -->
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    h1 {
      text-align: center;
      margin-bottom: 20px;
    }

    .chart-container {
      width: 600px;
      margin: 0 auto;
    }

    .header {
      background-color: #333;
      color: white;
      padding: 10px;
      text-align: center;
    }

    .footer {
      background-color: #333;
      color: white;
      padding: 10px;
      text-align: center;
    }
.button:hover {
            opacity: 0.8;
        }
.button {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
        }
  </style>
</head>
<body>
<div class="header">
  <h1>주별 판매량 및 매출액</h1>
</div>
<!-- 캘린더 -->
<input type="text" id="datepicker" value="Calender" readonly>

<!-- 주 판매량 그래프를 표시할 캔버스 요소 -->
<div class="chart-container">
  <canvas id="quantityChart"></canvas>
</div>

<!-- 매출액 그래프를 표시할 캔버스 요소 -->
<div class="chart-container">
  <canvas id="salesChart"></canvas>
</div>

<script>
  $(function() {
    // 캘린더 초기화
    $("#datepicker").datepicker({
      dateFormat: 'yy-mm-dd',
      onSelect: function(dateText) {
        // 선택한 날짜로 데이터 조회
        $.ajax({
          url: "/weeklySalesData",
          method: "GET",
          data: { date: dateText },
          success: function(response) {
            updateCharts(response);
          }
        });
      }
    });
  });

  // 제품명과 매출액, 판매량 데이터를 가져와 그래프 생성
  var productNames = [];
  var quantityData = [];
  var salesData = [];

  <c:forEach items="${weeklySales}" var="weeklySale">
    productNames.push("${weeklySale.productName}");
    quantityData.push(${weeklySale.dailyQuantity});
    salesData.push(${weeklySale.dailySales});
  </c:forEach>

  // 초기 그래프 생성
  createCharts();

  function createCharts() {
    // 주 판매량 그래프 생성
    var quantityCtx = document.getElementById('quantityChart').getContext('2d');
    new Chart(quantityCtx, {
      type: 'line',
      data: {
        labels: productNames,
        datasets: [{
          label: '주별 판매량',
          data: quantityData,
          backgroundColor: 'rgba(192, 75, 192, 0.2)',
          borderColor: 'rgba(192, 75, 192, 1)',
          borderWidth: 1,
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

    // 매출액 그래프 생성
    var salesCtx = document.getElementById('salesChart').getContext('2d');
    new Chart(salesCtx, {
      type: 'bar',
      data: {
        labels: productNames,
        datasets: [{
          label: '매출액',
          data: salesData,
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  function updateCharts(response) {
    // 데이터 업데이트
    var updatedQuantityData = response.quantityData;
    var updatedSalesData = response.salesData;

    // 그래프 업데이트
    var quantityChart = Chart.getChart('quantityChart');
    var salesChart = Chart.getChart('salesChart');

    quantityChart.data.datasets[0].data = updatedQuantityData;
    salesChart.data.datasets[0].data = updatedSalesData;

    quantityChart.update();
    salesChart.update();
  }
</script>
<div class="footer">
  <button class="button" onclick="location.href='/main'">Main</button>
  <button class="button" onclick="location.href='/dailySales'">일별</button>
  <button class="button" onclick="location.href='/monthlySales'">월별</button>
</div>
</body>
</html>
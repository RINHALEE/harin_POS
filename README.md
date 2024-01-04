## 편의점 판매 관리 프로그램
편의점 POS(Point Of Sale) 웹시스템으로, 편의점에서 판매 및 재고 관리를 원활하게 수행할 수 있는 기능을 제공합니다. 이 시스템은 사용자가 편리하게 상품을 판매하고 결제 처리를 할 수 있으며, 재고를 관리하고 통계 데이터를 분석하여 그래프로 확인할 수 있고, 계정 기능을 통해 사용자를 등록하고 권한을 관리할 수 있습니다.

## 사용 기술 스택
<div align="center">
	<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white" />
	<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white" />
	<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white" />
  	<img src="https://img.shields.io/badge/javaScript-F7DF1E?style=flat&logo=javascript&logoColor=white" />
  	<img src="https://img.shields.io/badge/mysql-4479A1?style=flat&logo=mysql&logoColor=white" />
  	<img src="https://img.shields.io/badge/spring-6DB33F?style=flat&logo=spring&logoColor=white" />
</div>

## 주요 기능
1. 상품 관리: 상품 정보를 등록하고 관리할 수 있습니다. 상품명, 가격, 재고량 등을 설정하고, 새로운 상품을 추가하거나 기존 상품을 수정, 삭제할 수 있습니다.

2. 판매 처리: 판매 시스템을 통해 고객이 구매한 상품을 처리하고 상품명을 검색하거나 제품 목록에서 직접 선택하여 판매할 수 있습니다. 또한 결제 페이지에서 바로 취소가 가능하여 더욱 편리하게 이용할 수 있습니다.

3. 재고 관리: 상품의 재고량을 실시간으로 추적하여 재고 관리를 돕습니다. 상품의 판매와 입고에 따라 재고가 자동으로 업데이트됩니다.

4. 매출 분석: 판매 기록을 통해 매출 데이터를 분석하고 그래프를 생성합니다. 일별, 주간별, 월별 판매량과 매출액을 확인할 수 있으며, 특정 기간에 대한 매출 추이를 시각화하여 파악할 수 있습니다.


## ERD
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/0926bb5d-d2db-4f15-b25a-511acf26b6ff)


## 구현화면
### 1. 로그인
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/ac4c99c6-20f6-4437-9414-424d31e128f5)

#### 로그인에 실패했을 경우
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/a0e7c7c6-c024-439e-9ce8-f304bca803c9)

### 2. 회원가입
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/0dfd04b4-e69b-458d-a788-1f8fb2e71f12)

### 3. 메인 화면
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/3904cb0e-a863-4ecb-8641-c6e081c6bfad)

### 4. 결제 기능
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/f708fa35-2184-4551-873f-bf91ff042400)
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/dfbcd2b2-4c04-4a1a-97b7-11e19cd59181)

### 5. 제품 관리 기능
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/473682a9-e83c-4a24-8c04-6e6458b0d9ca)
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/1a216c0b-3812-4de0-8267-eb6938152403)

### 6. 통계 기능
판매량과 매출액을 일별, 주별, 월별로 확인할 수 있으며, 캘린더를 통해 원하는 날짜의 판매량과 매출액을 알 수 있습니다.
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/34084a7f-cec6-44ea-822b-0128f11eb7d9)
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/c292b1d6-2a5c-4d33-8418-ab615cf2cdc3)
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/ff806146-4453-4973-8cf5-125c8cd5eb2b)

### 7. 계정 관리 기능
![image](https://github.com/RINHALEE/harin_POS/assets/128150726/c1297ec6-9068-43dd-92a9-10820a1ca219)


## 개발 관련 노력한점
1. 패키지 구조 설계:
Harin_Pos 편의점 POS 시스템을 개발하기 전에 패키지 구조를 체계적으로 설계했습니다. 각 패키지는 역할에 맞게 기능을 분리하여 코드의 모듈화와 유지보수성을 높이는 데 중점을 두었습니다.
2. 스프링 프레임워크:
시스템의 유연성과 확장성을 높이기 위해 스프링 프레임워크를 도입했습니다. 의존성 주입(Dependency Injection)과 관련된 기능을 활용하여 객체 간의 결합도를 낮추고 코드의 재사용성을 향상시켰습니다.
3. 데이터베이스 관리:
데이터베이스 설계와 연동에 주요한 노력을 기울였습니다. 효율적인 데이터베이스 구조를 설계하고, 데이터베이스 연결과 관련된 기능을 개발하여 데이터의 안정성과 일관성을 유지하였습니다.
4. 상품 관리 기능 개발:
상품의 등록, 조회, 수정, 삭제 기능을 개발하여 편의점 내의 상품 관리를 원활하게 할 수 있도록 했습니다. 이를 통해 상품의 실시간 관리와 재고 조달에 대한 효율성을 높였습니다.
5. 판매 기능 개발:
제품 판매와 관련된 기능을 개발하여 실제 매출에 대한 처리를 자동화하였습니다. 판매 정보의 저장, 조회, 결제 처리 등의 기능을 구현하여 편의점의 매출 분석과 관리를 지원했습니다.
6. 통계 기능 개발:
매출 통계를 생성하고 시각화하여 사용자에게 제공하는 기능을 개발했습니다. 일일, 주간, 월간 판매량과 매출액에 대한 통계를 생성하여 시스템 사용자가 매출 동향을 파악할 수 있도록 했습니다.

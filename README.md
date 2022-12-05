# README

# SSAFIT - 운동영상 관리 서비스

## 📅  프로젝트 진행 기간

---

- 2022.11.16 ~ 2022.11.24
- SSAFY 8기 1학기 관통프로젝트

## 🔎 프로젝트 개요

---

- youtube_api를 사용한 동영상관리 서비스 웹사이트

## 🔎 주요 기술 스택

---

### Front-end

![vue.js](https://img.shields.io/badge/-vue.js-green?style=for-the-badge&logo=vue.js&logoColor=white)
![vuetify](https://img.shields.io/badge/-vuetify-blue?style=for-the-badge&logo=vuetify&logoColor=white)

### Back-end

![java](https://img.shields.io/badge/-java-brown?style=for-the-badge&logo=JAVA&logoColor=white)
![spring](https://img.shields.io/badge/-spring-green?style=for-the-badge&logo=spring&logoColor=white)
![springboot](https://img.shields.io/badge/-springboot-yellowgreen?style=for-the-badge&logo=springboot&logoColor=white)   

## ⭐  주요기능

---

### 1. 회원관리

<img width="1440" alt="%E1%84~1" src="https://user-images.githubusercontent.com/92661439/205722741-3b62e23b-e01e-42a4-8aac-d925f80f4ce0.PNG">

<img width="1440" alt="%E1%84~2" src="https://user-images.githubusercontent.com/92661439/205722759-09eabd76-8031-4fd8-b18c-007785ce6e1a.PNG">

- jwt를 사용하여 access token을 서버에서 생성하고, 회원의 세션 스토리지에 저장하여 서버측에서 로그인 정보를 저장할 필요 없게 하므로서 무상태성 유지
- 회원 가입시 아이디 중복여부 서버에서 체크, 비밀번호 두번입력은 프론트에서 확인

### 2. 메인화면

---

<img width="1440" alt="%E1%84~3" src="https://user-images.githubusercontent.com/92661439/205722884-0e862dd0-2ba9-46e4-a862-86a4f1b55e04.PNG">

- backend에서 youtube API를 사용하여 데이터를 받아와 각 주제에 맞는 동영상을 화면에 출력한다.
- vue.js 를 사용하여 SPA을 작성한다.

### 3.검색화면

---

<img width="1440" alt="%EE6EB~1" src="https://user-images.githubusercontent.com/92661439/205723198-941712eb-c851-45ac-bcf6-24ed07e01399.PNG">
- 반응형 웹을 지원하여 화면 크기에 따라 메뉴바의 위치가 변경된다.
- youtube_API를 통해 비동기 통신으로 동영상을 검색하거나 다른사람의 playlist를 검색할 수 있다.

### 4. 동영상 좋아요

---

<img width="1440" alt="%E1%84~4" src="https://user-images.githubusercontent.com/92661439/205723356-b2c82ea9-b228-4f5a-840e-ad90fe98d178.PNG">
<img width="1440" alt="%E382C~1" src="https://user-images.githubusercontent.com/92661439/205723376-19a1872f-bb5e-4ee8-bdd4-ef2658d738e6.PNG">
<img width="1440" alt="%E7AAF~1" src="https://user-images.githubusercontent.com/92661439/205723387-aeb99f4f-11d7-471f-b621-07155a255760.PNG">

- 동영상 검색후 좋아요를 누르면 그 즉시 동양상의 정보를 db에 저장한다.
- likeVideo에 들어가면 좋아요 표시한 동영상만 따로 모아서 볼 수 있다.

### 5. 플레이 리스트

---

<img width="1440" alt="%E3086~1" src="https://user-images.githubusercontent.com/92661439/205723431-0e2c8d1d-375b-431f-b70b-9c74353e01d2.PNG">
<img width="1440" alt="%E0D23~1" src="https://user-images.githubusercontent.com/92661439/205723442-24cd0627-1796-4e81-830b-1cc685c529e4.PNG">
<img width="1440" alt="%E53B8~1" src="https://user-images.githubusercontent.com/92661439/205723458-b3605a73-43b4-4179-ba80-cef856e6f0a5.PNG">

- 나만의 플레이 리스트를 만든다.
- 플레이리스트에는 공개여부, 좋아요 개수, 만든유저의 아이디 등이 저장된다.


<img width="1440" alt="%E259F~1" src="https://user-images.githubusercontent.com/92661439/205723490-6aeb35e5-8645-4350-9766-22ff9120bd81.PNG">

- BOOKMARK 버튼을 누르면 내가 만든 palylist의 정보가 화면에 표시된다.
- 저장할 플레이리스트를 누르게 되면 플레이리스트 재생목록에 추가된다.

플레이리스트 화면


<img width="763" alt="%E7738~1" src="https://user-images.githubusercontent.com/92661439/205723612-15931b99-ebdb-4923-a105-63e3e67e7a28.PNG">
<img width="1440" alt="%ED042~1" src="https://user-images.githubusercontent.com/92661439/205723619-d21fc536-cdfd-48e5-8eaf-dcadfed3523d.PNG">
<img width="761" alt="%EBCD6~1" src="https://user-images.githubusercontent.com/92661439/205723621-473402a6-4be1-4fc6-82fa-28cfe91607bf.PNG">

- 화면의 너비가 일정크기 이하로 줄어들면 재생목록이 감춰지는 반응형 웹으로 설계했다.

### 6.플레이 리스트 댓글

---

<img width="1440" alt="스크린샷 2022-12-05 오후 10 37 11" src="https://user-images.githubusercontent.com/92661439/205723793-24760923-1012-4c31-bb0b-668426b93ea4.png">

- 댓글과 대댓글 기능동작
- 댓글을 작성한 작성자만 수정,삭제 가능

### 7. 팔로우 팔로워

---

<img width="1440" alt="스크린샷 2022-12-05 오후 10 39 09" src="https://user-images.githubusercontent.com/92661439/205723807-d49cb904-8171-4bf9-becc-7380f0aad677.png">

- 친구목록, 팔로잉, 팔로워로 분류해서 나눠 볼 수 있다.
- 팔로워 팔로우를 즉시 취소할 수 있고, 다른 사람의 프로필로 이동할 수도 있다.

## ✏️ 배운점

---

**[컴포넌트 통신에 대한 이해 증가]**

vue.js를 사용하면서 spa를 설계 하였다. 컴포넌트간의 원활한 데이터 통신을 위해 vuex를 사용하였고 상태관리 패턴에 대한 이해가 높아지면서 axios를 통한 비동기 통신을 통해 데이터를 받아오는것을 훨씬더 쉽게 구현할 수 있었다.

**[git을 통한 버전관리의 이해]**

서로 다른 영역을 맡아 진행한 만큼 branch로 버전을 관리하여 서로 다른시간에 개발하더라도 진행상황을 대략적으로 알 수 있게 관리하였다. 서로 겹치는 부분이 있다는것을 알았을  때 충분한 대화를 통해 더 나은 코드로 프로젝트를 진행할 수 있었다.

**[협업 및 소통능력 향상]**

notion을 사용하여 일정관리를 체계적으로 관리하여 개발기간을 촉박하지 않게 관리했다. 개발 못지 않게 협업을 위해 소통능력이 많이 필요하다는 것을 깨달았다.

### ✏️  아쉬운점

---

- 검색을 할 때 필터링을 통한 더욱 정확한 검색을 하지 못했다.
- 회원 정보 수정을 통해 프로필 사진을 넣는 기능을 구현하지 못했다.

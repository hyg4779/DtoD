# DtoD 웹/모바일(웹 기술) 프로젝트

## DTOD 소개 및 시연 영상

시나리오가 궁금하면 [여기](/uploads/d7553987c7bfdcc59dc277adbd5263bc/시나리오.md)에서 확인할 수 있습니다.

## 🌟Overbiew

개발자 열풍이 불고 있는 지금 여러분은 어떻게 공부하시나요? 코로나가 걱정되시나요? 거리두기때문에 스터디를 하기 어려우신가요?
이런 문제를 해결하기 위해 **DTOD**가 왔습니다. 다양한 사람들과 스터디를 시작해보세요!!

## 프로젝트 기간 - 2022.01.10 ~ 2022.02.18

## 카테고리

| Application                          | Domain                                | Language                         | Framework                            |
| ------------------------------------ | ------------------------------------- | -------------------------------- | ------------------------------------ |
| :white_check_mark: Desktop Web       | :black_square_button: AI              | :white_check_mark: JavaScript    | :white_check_mark: Vue.js            |
| :black_square_button: Mobile Web     | :black_square_button: Big Data        | :black_square_button: TypeScript | :black_square_button: React          |
| :black_square_button: Responsive Web | :black_square_button: Blockchain      | :black_square_button: C/C++      | :black_square_button: Angular        |
| :black_square_button: Android App    | :black_square_button: IoT             | :black_square_button: C#         | :black_square_button: Node.js        |
| :black_square_button: iOS App        | :black_square_button: AR/VR/Metaverse | :black_square_button: Python     | :black_square_button: Flask/Django   |
| :black_square_button: Desktop App    | :black_square_button: Game            | :white_check_mark: Java          | :white_check_mark: Spring/Springboot |
|                                      |                                       | :black_square_button: Kotlin     |                                      |

## DTOD 서비스 화면

---

## 👓프로젝트 소개👓

### 주요기능

---

- 서비스 설명 : 비대면으로 집에서 예비 개발자들과 학습 열정을 공유하고 공부(개발)까지!
- 주요 기능 :
  - WebRTC를 통한 실시간 의사소통
  - 입실, 퇴실 시간을 기록해 출석률 관리
  - 게시판에서 코드 에디를 제공해 편한 코드 리뷰 및 질문 가능

### 개발 환경

---

**Backend**

- IntelliJ
- Spring Boot 2.6.2
- Spring Boot JPA
- Spring Security
- JAVA 8
- MySQL
- AWS EC2

**Frontend**

- Visual Studio Code
- Vue
- Bootstrap
- Vuetify

**Web RTC**

- OpenVidu 2.20.1

**CI/CD**

- AWS EC2
- Docker
- Nginx

**협업 툴**

- Git
- Jira
- Notion
- Mattermost
- Webex
- Discord

### Git Flow 브랜치 전략

---

- Git Flow Model을 사용하고, Git 기본 명령어를 사용한다.

- Git Flow 사용 브랜치

  - feature - 기능
  - develop - 개발
  - master - 배포

- Git Flow 진행 방식

  1. feature 브랜치가 완성되면 develop 브랜치로 pull request를 통해 merge한다.

     => pull request가 요청되면, 모든 팀원이 코드 리뷰를 하고 merge를 진행한다.

  2. 매주 금요일에 develop 브랜치를 master 브랜치로 병합하여 배포를 진행한다.

### Jira

---

일정 및 업무 관리를 위해 Jira를 사용했습니다. 매주 금요일 회의에서 다음 주에 진행될 주 단위 계획을 짜고 이슈들을 스프린트에 등록했습니다. 스프린트는 주 단위로 만들어 진행하였습니다.

- Epic : 기능들을 큰 틀로 나누어서 구성하였습니다.
- Stroy : 세부 기능들을 작성하여 Epic을 지정하였습니다.
- Task : 구현과 별개로 해야 할 목록들을 Task를 이용하여 정리하였습니다.

### Notion & Discord

---

Notion에서 모두가 봐야할 공지, 팀장미팅 내용, 프로젝트 관련된 필수 링크들을 모아서 관리했습니다. 추가로 브랜치 전략도 Notion에 기록해서 모두가 확인할 수 있도록 관리했습니다.

Discord에서는 개발에 참고할 링크, 코드들을 모아서 정리했으며 정규시간 이외 시간에는 항상 Discord에 모여서 개발을 진행했습니다.

### Scrum

---

매일 종례 후 디스코드에 모여서 오늘 했던 일, 하면서 있었던 이슈, 내일 할 일 목록을 공유하는 시간을 가졌습니다. Scrum을 통해 팀원들의 현재 상황을 공유하여 프로젝트 진행 상황을 꾸준히 모니터링 할 수 있었습니다.

### ER Diagram

---

![공통플젝ERD](/uploads/0ea21a62eb22d49ef027ff61c7cf03b8/공통플젝ERD.png)

## 팀 소개

- 허범 : 팀장, ERD설계 및 인프라 담당
- 안정석: 백엔드 개발, Swagger API 문서 관리
- 이동철: 프론트 개발
- 홍석준: 프론트 개발

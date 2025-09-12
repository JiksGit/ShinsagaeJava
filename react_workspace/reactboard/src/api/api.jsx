/* 비동기 방식의 요청을 시도할 예정
 Vue, React Jquery ajax()를 잘 사용하지 않음

 [ axios ] 
  - 비동기 방식의 요청 객체
  - web뿐만 아니라, node.js와 같은 독립실행형 자바스크립트에서도 사용이 가능
  - JSON 자동 변환 지원 따라서 서버가 보내준 데이터를 바로 접근가능(data.boardList)
  - React, vue 프로젝트에서 표준

 [ jquery ajax ]
   - jquery 에서만 지원하는 웹용 비동기 요청 객체
   - 콜백 기반이라, 코드가 복잡해질 수 있음
   - node.js에서는 사용 불가
   - 서버가 보내준 정보를 json으로 직접 파싱
   - 기존 레거시 프로젝트에서 많이 사용해왔으므로, 유지보수 시 알아야 함
*/ 
import axios from 'axios';

// 공통 URL을 선언
const URL = "http://localhost:7777/api/boards";

// 요청
export const getBoards = () => axios.get(URL); // 목록 요청
export const getBoard = (boardId) => axios.get(`${URL}/${boardId}`); // 한건 요청
export const insertBoard = (data) => axios.post(URL, data);
export const updateBoard = (boardId, data) => axios.put(`${URL}/${boardId}`, data);
export const deleteBoard = (boardId) => axios.delete(`${URL}/${boardId}`);
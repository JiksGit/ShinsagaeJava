import axios from "axios";

// 상품 관련 공통 URL
const URL = "http://localhost:7777/orders";

// 상품 목록
export const getOrders=()=>axios.get(URL);

// 상품 등록
export const registOrder=(data)=>axios.post(URL, data);

// 상품 한 건 가져오기(URL{~~/products/35}, get)
export const getOrder=(orderId)=>axios.get(`${URL}/${orderId}`);

// 상품 수정
export const updateOrder=(orderId, data)=>axios.put(`${URL}/${orderId}`, data);

// 상품 삭제
export const deleteOrder=(orderId)=>axios.delete(`${URL}/${orderId}`);
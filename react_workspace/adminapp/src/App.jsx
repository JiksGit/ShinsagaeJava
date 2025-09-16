import Preloader from  './components/Preloader'
import Navbar from './components/Navbar'
import Sidebar from './components/Sidebar'
import Footer from './components/Footer'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import ProductList from './pages/product/ProductList'
import ProductForm from './pages/product/ProductForm'
import MemberList from './pages/member/MemberList'
import MemberForm from './pages/member/MemberForm'
import OrderList from './pages/order/OrderList'
import OrderForm from './pages/order/OrderForm'

function App() {

  return (
    <BrowserRouter>
    <div className="wrapper">
      <Preloader/>
      <Navbar/>
      <Sidebar/>
      {/* 페이지 전환될 영역 */}
      <div className="content-wrapper">
        <section className="content">
          <Routes>
            {/* 상품 관련 2개 */}
            <Route path="/product/list" element={<ProductList/>}/>
            <Route path="/product/registform" element={<ProductForm/>}/>
            {/* 회원 관련 2개 */}
            <Route path="/member/list" element={<MemberList/>}/>
            <Route path="/member/registform" element={<MemberForm/>}/>
            {/* 주문 관련 2개 */}
            <Route path="/order/list" element={<OrderList/>}/>
            <Route path="/order/registform" element={<OrderForm/>}/>
          </Routes>
        </section>
      </div>
      <Footer/>
    </div>
    </BrowserRouter>
  )
}

export default App

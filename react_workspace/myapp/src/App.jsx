import { BrowserRouter, Link, Routes, Route } from 'react-router-dom'
import Home from "./pages/Home"
import Company from "./pages/Company"
import Portfolio from "./pages/Portfolio"
import Support from "./pages/Support"

function App() {  
  return (
      <BrowserRouter> 
        <nav style={{margin:"30px"}}>
          <Link to="/">Home</Link>
          <Link to="/company">Company</Link>
          <Link to="/portfolio">Portfolio</Link>
          <Link to="/support">Support</Link>
        </nav>

        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/company" element={<Company/>}/>
          <Route path="/portfolio" element={<Portfolio/>}/>
          <Route path="/support" element={<Support/>}/>
        </Routes>
      </BrowserRouter>
  );
}

export default App

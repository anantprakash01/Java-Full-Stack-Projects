import logo from './logo.svg';
import './App.css';
import Navigation from './customer/components/Navigation/Navigation';
import HomePage from './customer/components/Pages/HomePage/HomePage';
import Footer from './customer/components/Footer/Footer';
import Product from './customer/components/Product/Product/Product';
import ProductDetails from './customer/components/ProductDetails/ProductDetails';
import Cart from './customer/components/Cart/Cart';
import Checkout from './customer/components/Checkout/Checkout';
import Order from './customer/components/Order/Order';
import OrderTracker from './customer/components/Order/OrderTracker';
import OrderDetails from './customer/components/Order/OrderDetails';
import { Route, Routes } from 'react-router-dom';
import CustomerRouters from './Routers/CustomerRouters';
import AdminRouters from './Routers/AdminRouters';

function App() {
  return (
    <div className="">

      <Routes>
        <Route path='/*' element={<CustomerRouters/>}></Route>
        <Route path='/admin/*' element={<AdminRouters/>}></Route>
      </Routes>

     <div>
     
     </div>
   
    </div>
  );
}

export default App;

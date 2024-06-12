import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router-dom';
import { getOrderById } from '../../../State/Order/Action';
import { updatePayment } from '../../../State/Payment/Action';
import { Alert, AlertTitle, Grid } from '@mui/material';
import OrderTracker from '../Order/OrderTracker';
import AddressCard from '../AddressCard/AddressCard';

const PaymentSuccess = () => {
    const [paymentId,setPaymentId] = useState("");
    const [referenceId,setReferenceId] = useState();
    const [paymentStatus,setPaymentStatus] = useState("");
    const {orderId} = useParams();

    const dispatch = useDispatch();
    const {order} = useSelector(store=>store);

    
    console.log("order ",order.order)
    useEffect(()=>{
    const urlParams = new URLSearchParams(window.location.search);
    setPaymentId(urlParams.get("razorpay_payment_id"));
    setReferenceId(urlParams.get("razorpay_payment_link_reference_id"));
    setPaymentStatus(urlParams.get("razorpay_payment_link_status"));
    // console.log("order after1",order.order)
    },[])

    useEffect(()=>{
        if(paymentId){
            const data = {orderId,paymentId}
        dispatch(getOrderById(orderId));
        dispatch(updatePayment(data))
        }
        // console.log("order after",order.order)

    },[orderId,paymentId])
  return (
    <div className='px-2 lg:px-36'>
        <div className='flex flex-col justify-center items-center'>

            <Alert
            variant='filled'
            severity='success'
            sx={{mb:6,width:"fit-content"}}
            >
                <AlertTitle>Payment Success</AlertTitle>
                Congratulations! Your order get placed
            </Alert>
        </div>
        <OrderTracker activeStep={1}/>

        <Grid container className='space-y-5 py-5 pt-20'>
            <Grid container item className='shadow-xl rounded-md p-5'
            sx={{alignItems:"center", justifyContent:"space-between"}}>

                {order.order?.orderItems.map((item)=><Grid container item className='shadow-xl
                 rounded-md p-5'
                 sx={{alignItem:"center", justifyContent:"space-between"}}>

                    <Grid item xs={6}>
                        <div className='flex items-center'>
                            <img className='w-[5rem] h-[5rem]' src={item.product.imageUrl} alt="" />
                        </div>

                        <div>
                            <p>{item.product.title}</p>
                            <div className='opacity-50 text-xs font-semibold space space-x-5'>
                                <span>Color: {item.color}</span>
                                <span>Size: {item.size}</span>
                            </div>
                            <p>Seller : {item.product.brand}</p>
                            <p>{item.price}</p>
                        </div>

                    </Grid>

                    <Grid item>
                        <AddressCard address={order.order?.shippingAddress}/>
                    </Grid>

                </Grid>)}                
            </Grid>

        </Grid>
    </div>
  )
}

export default PaymentSuccess

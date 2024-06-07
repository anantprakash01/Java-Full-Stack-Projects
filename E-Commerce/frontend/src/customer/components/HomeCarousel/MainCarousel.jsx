import React from 'react'
import { MainCarouselData } from './MainCarouselData';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';

const MainCarousel = () => {


    const items = MainCarouselData.map((item)=><img className='cursor-pointer' role='presentation' src={item.Image} alt=''/>)

  return (
      <AliceCarousel
        items={items}
        autoPlay
        autoPlayInterval={1000}
        infinite
        disableButtonsControls
    />
  )
}

export default MainCarousel

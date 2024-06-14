import { Avatar, Box, Button, Card, CardHeader, FormControl, Grid, InputLabel, MenuItem, Pagination, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { deleteProduct, findProducts } from '../../State/Product/Action';
import { useLocation, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';

const ProductsTable = () => {
    const location = useLocation();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { products } = useSelector(store=> store);
  const [filterValue, setFilterValue] = useState({
    availability: "",
    category: "",
    sort: "",
  });

  console.log("Products===",products)

    useEffect(() => {
        // setFilterValue({ availability, category, sort });
        const data = {
          category:"mens_kurta",
          colors: [],
          sizes: [],
          minPrice:0,
          maxPrice: 10000,
          minDiscount: 0,
          sort: "price_low",
          pageNumber:0,
          pageSize: 5,
          stock: "",
        };
        dispatch(findProducts(data));
      }, [products.deletedProduct]);

      const handleDeleteProduct=(productId)=>{
        console.log("delete product ",productId)
        dispatch(deleteProduct(productId))
      }
  return (
    <Box width={"100%"}>
      <Card className="p-3">
        <CardHeader
          title="Sort"
          sx={{
            pt: 0,
            alignItems: "center",
            "& .MuiCardHeader-action": { mt: 0.6 },
          }}
        />
        <Grid container spacing={2}>
          <Grid item xs={4}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Category</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={filterValue.category}
                label="Category"
                // onChange={(e) => handleFilterChange(e, "category")}
              >
                <MenuItem value={"pant"}>Men's Pants</MenuItem>
                <MenuItem value={"mens_kurta"}>Men's Kurta</MenuItem>
                <MenuItem value={"saree"}>Saree</MenuItem>
                <MenuItem value={"lengha_choli"}>Lengha Choli</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={4}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">
                Availability
              </InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={filterValue.availability}
                label="Availability"
                // onChange={(e) => handleFilterChange(e, "availability")}
              >
                <MenuItem value={"All"}>All</MenuItem>
                <MenuItem value={"in_stock"}>Instock</MenuItem>
                <MenuItem value={"out_of_stock"}>Out Of Stock</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={4}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">
                Sort By Price
              </InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={filterValue.sort}
                label="Sort By Price"
                // onChange={(e) => handleFilterChange(e, "sort")}
              >
                <MenuItem value={"price_high"}>Heigh - Low</MenuItem>
                <MenuItem value={"price_low"}>Low - Heigh</MenuItem>
              </Select>
            </FormControl>
          </Grid>
        </Grid>
      </Card>
      
      <Card className="mt-2">
        <CardHeader
          title="All Products"
          sx={{
            pt: 2,
            alignItems: "center",
            "& .MuiCardHeader-action": { mt: 0.6 },
          }}
        />
        <TableContainer>
          <Table sx={{ minWidth: 800 }} aria-label="table in dashboard">
            <TableHead>
              <TableRow>
                <TableCell>Image</TableCell>
                <TableCell>Title</TableCell>
                <TableCell sx={{ textAlign: "center" }}>Category</TableCell>
                <TableCell sx={{ textAlign: "center" }}>Price</TableCell>
                <TableCell sx={{ textAlign: "center" }}>Quantity</TableCell>
                <TableCell sx={{ textAlign: "center" }}>Update</TableCell>
                <TableCell sx={{ textAlign: "center" }}>Delete</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {products?.products?.content?.map((item) => (
                <TableRow
                  hover
                  key={item.name}
                  sx={{ "&:last-of-type td, &:last-of-type th": { border: 0 } }}
                  
                >
                  <TableCell>
                    {" "}
                    <Avatar alt={item.titel} src={item.imageUrl} />{" "}
                  </TableCell>

                  <TableCell
                    sx={{ py: (theme) => `${theme.spacing(0.5)} !important` }}
                  >
                    <Box sx={{ display: "flex", flexDirection: "column" }}>
                      <Typography
                        sx={{
                          fontWeight: 500,
                          fontSize: "0.875rem !important",
                        }}
                      >
                        {item.title}
                      </Typography>
                      <Typography variant="caption">{item.brand}</Typography>
                    </Box>
                  </TableCell>
                  <TableCell sx={{ textAlign: "center" }}>{item.category.name}</TableCell>
                  <TableCell sx={{ textAlign: "center" }}>{item.discountedPrice}</TableCell>
                  <TableCell sx={{ textAlign: "center" }}>{item.quantity}</TableCell>
                  <TableCell sx={{ textAlign: "center" }}>
                    <Button onClick={()=>navigate(`/admin/product/update/${item.id}`)} variant="text">Update</Button>
                  </TableCell>
                  <TableCell sx={{ textAlign: "center" }}>
                    <Button variant="text" 
                    onClick={()=>handleDeleteProduct(item.id)}
                    >Delete</Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Card>
      <Card className="mt-2 border">

        <div className="mx-auto px-4 py-5 flex justify-center shadow-lg rounded-md">
          <Pagination
            count={products.products?.totalPages}
            color="primary"
            className=""
            // onChange={handlePaginationChange}
            // value={page}
          />
        </div>
      </Card>
    </Box>
  )
}

export default ProductsTable

package com.example.taskgroup.uis

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.taskgroup.Room.CartProduct
import com.example.taskgroup.ui.theme.btnColor
import com.example.taskgroup.ui.theme.offWhite

class Cart(val context: Context, val products: List<CartProduct>) {
    var total = products.sumOf{ it -> it.price }.toInt()
    @Composable
    fun CartDetails(){
        Column {
            LazyColumn(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                items(products) { product ->
                    CartItem(product)
                }
            }
            Row {
                Button(
                    {
                        Toast.makeText(context, "btn clicked", Toast.LENGTH_SHORT).show();
                    },
                    colors = ButtonDefaults.buttonColors(btnColor),
                    modifier = Modifier.Companion
                        .padding(start = 8.dp)
                        .fillMaxWidth(0.8f)
                ) {
                    Text("Check out !!")
                }
                Text(
                    " $total $",
                    fontSize = 20.sp,
                    modifier = Modifier.Companion
                        .padding(top = 12.dp)
                )
            }
        }
    }
    @Composable
    fun CartItem(product: CartProduct){
        Card(
            colors = CardDefaults.cardColors(Color.Companion.LightGray),
            modifier = Modifier.Companion
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(150.dp)
                .clip(shape = RoundedCornerShape(20.dp)),

            ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = "",
                    contentScale = ContentScale.Companion.FillHeight,
                    modifier = Modifier.Companion
                        .fillMaxHeight()
                        .fillMaxWidth(0.4f)
                        .background(offWhite)

                )
                Column(
                    modifier = Modifier.Companion
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = product.title,
                        fontSize = 16.sp,
                        maxLines = 2
                    )
                    Text(
                        text = "Quantity : 5",
                        fontSize = 16.sp,
                        modifier = Modifier.Companion
                            .padding(top = 8.dp)
                            .weight(1f)
                    )

                    Text(
                        text = "$ ${product.price}",
                        fontSize = 24.sp,
                        modifier = Modifier.Companion
                            .padding(8.dp)
                            .align(Alignment.Companion.End)
                    )
                }
            }
        }
    }

//    @Preview(showBackground = true, showSystemUi = true)
//    @Composable
//    fun CartItemPreview(){
//        CartItem()
//    }
//    @Preview(showBackground = true, showSystemUi = true)
//    @Composable
//    fun CartPreview(){
//        CartDetails(Product())
//    }

}
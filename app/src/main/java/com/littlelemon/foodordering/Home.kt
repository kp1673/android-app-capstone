@file:OptIn(ExperimentalMaterial3Api::class)

package com.littlelemon.foodordering

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.littlelemon.foodordering.ui.theme.FoodOrderingTheme
import com.littlelemon.foodordering.ui.theme.KarlaRegularFontFamily
import com.littlelemon.foodordering.ui.theme.LittleLemonColor
import com.littlelemon.foodordering.ui.theme.MarkaziTextFontFamily


@Composable
fun Home(navController: NavHostController) {

    val database = Room.databaseBuilder(LocalContext.current, AppDatabase::class.java, "database").build()
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    var itemCategory by remember { mutableStateOf("") }
    var menuItems = when (itemCategory){
            "starters" -> databaseMenuItems.filter { it.category == "starters" }
            "mains" -> databaseMenuItems.filter { it.category == "mains" }
            "desserts" -> databaseMenuItems.filter { it.category == "desserts" }
            else -> databaseMenuItems
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top= 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(185.dp, 40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp, 48.dp)
                    .clickable {
                        navController.navigate(Profile.route)
                    }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(LittleLemonColor.green)
                .padding(16.dp)
        ) {
            Text(
                text = "Little Lemon",
                fontFamily = MarkaziTextFontFamily,
                fontSize = 40.sp,
                fontWeight = FontWeight.Medium,
                color = LittleLemonColor.yellow
            )

            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    modifier = Modifier .fillMaxWidth(0.6f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Chicago",
                        fontFamily = MarkaziTextFontFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = LittleLemonColor.cloud
                    )
                    Text(
                        text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        fontFamily = KarlaRegularFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.height(168.dp).clip(RoundedCornerShape(20.dp))
                )
            }
            var searchPhrase by remember{ mutableStateOf("") }
            if (searchPhrase.isNotBlank()){
                menuItems = menuItems.filter {
                    it.title.contains(searchPhrase,ignoreCase=true)
                }
            }
            TextField(
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                placeholder = { Text(text = "Enter search phrase")},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search,
                    contentDescription = "")
                              },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = LittleLemonColor.cloud
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Text(
            text = "Order for Delivery!".uppercase(),
            fontFamily = KarlaRegularFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp).horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = LittleLemonColor.cloud,// Material 3
                    contentColor = LittleLemonColor.green),
                onClick = { itemCategory = "starters" },
                //shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Starters",
                    fontFamily = KarlaRegularFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = LittleLemonColor.cloud,// Material 3
                    contentColor = LittleLemonColor.green),
                onClick = { itemCategory = "mains" }
            ) {
                Text(
                    text = "Mains",
                    fontFamily = KarlaRegularFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = LittleLemonColor.cloud,// Material 3
                    contentColor = LittleLemonColor.green),
                onClick = { itemCategory = "desserts" }
            ) {
                Text(
                    text = "Desserts",
                    fontFamily = KarlaRegularFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
            }
        }

        Divider(
            color = LittleLemonColor.cloud,
            thickness = 4.dp,
            modifier = Modifier.fillMaxWidth()
        )


        MenuItems(menuItems)
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(menuItems: List<MenuItemRoom>) {

    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        menuItems.forEach { item ->
            Text(
                text = item.title,
                fontFamily = KarlaRegularFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(
                modifier = Modifier.padding(top = 8.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.7f),
                    // .align(Alignment.BottomStart)
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = item.description,
                        fontFamily = KarlaRegularFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = LittleLemonColor.green
                    )
                    Text(
                        text = "$%.2f".format(item.price),
                        fontFamily = KarlaRegularFontFamily,
                        color = LittleLemonColor.green,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                when (item.id) {
                    2 -> Image(
                        painter = painterResource(id = R.drawable.lemon_dessert),
                        contentDescription = item.title,
                        modifier = Modifier.height(100.dp),
                        contentScale = ContentScale.FillBounds
                    )

                    3 -> Image(
                        painter = painterResource(id = R.drawable.grilled_fish),
                        contentDescription = item.title,
                        modifier = Modifier.height(100.dp),
                        contentScale = ContentScale.FillBounds
                    )

                    else -> GlideImage(
                        model = item.image,
                        contentDescription = item.title,
                        modifier = Modifier.height(100.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }

                //GlideImage(
                //    model = item.image,
                //    contentDescription = item.title,
                //    modifier = Modifier.height(100.dp),
                //    contentScale = ContentScale.FillBounds
                //)

            }

            Divider(
                color = LittleLemonColor.cloud,
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth()
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview(){
    FoodOrderingTheme {
        Home(navController = rememberNavController())
    }
}





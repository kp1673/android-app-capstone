package com.littlelemon.foodordering

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.littlelemon.foodordering.ui.theme.FoodOrderingTheme
import com.littlelemon.foodordering.ui.theme.KarlaRegularFontFamily
import com.littlelemon.foodordering.ui.theme.LittleLemonColor
import com.littlelemon.foodordering.ui.theme.MarkaziTextFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Profile(navController: NavHostController){

    val context = LocalContext.current
    val preferencesHandler = remember {
        PreferencesHandler(context)
    }

    val firstName = preferencesHandler.getData("FIRST_NAME", "")
    val lastName = preferencesHandler.getData("LAST_NAME", "")
    val email = preferencesHandler.getData("EMAIL", "")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(185.dp, 40.dp)
        )
        Text(
            text = "Profile Information",
            color = LittleLemonColor.yellow,
            fontFamily = MarkaziTextFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(LittleLemonColor.green)
                .padding(16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(4.dp))
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier.size(120.dp, 120.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = LittleLemonColor.green,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                        )) {
                    append("First name:   ")
                }
                append(firstName.replaceFirstChar { it.uppercase() })
                },
                fontSize = 24.sp,
                fontFamily = KarlaRegularFontFamily,
                color = Color.Black,
                letterSpacing = 0.5.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = LittleLemonColor.green,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                        )) {
                        append("Last name:   ")
                    }
                    append(lastName.replaceFirstChar { it.uppercase() })
                },
                fontSize = 24.sp,
                fontFamily = KarlaRegularFontFamily,
                color = Color.Black,
                letterSpacing = 0.5.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = LittleLemonColor.green,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                        )) {
                        append("Email:            ")
                    }
                    append(email)
                },
                fontSize = 24.sp,
                fontFamily = KarlaRegularFontFamily,
                color = Color.Black,
                letterSpacing = 0.5.sp
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth().padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LittleLemonColor.yellow,// Material 3
                contentColor = Color.Black
                 ),
            onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                preferencesHandler.clearAll()
            }
            navController.navigate(Onboarding.route)
        }) {
            Text(
                text = "Log out",
                fontFamily = KarlaRegularFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    FoodOrderingTheme {
        Profile(navController = rememberNavController())
    }
}
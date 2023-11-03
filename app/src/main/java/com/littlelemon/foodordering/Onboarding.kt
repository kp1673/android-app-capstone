package com.littlelemon.foodordering

import android.widget.Toast
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavHostController){
    //val scope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val preferencesHandler = remember {
        PreferencesHandler(context)
    }

    Column (
        modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Spacer(modifier = Modifier.padding(16.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(185.dp, 40.dp)
        )
        //Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Let's get to know you!",
            fontFamily = MarkaziTextFontFamily,
            color = LittleLemonColor.yellow,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(LittleLemonColor.green)
                .padding(16.dp)
                .fillMaxWidth()
            //style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Column(
            modifier = Modifier
                .padding(16.dp, 16.dp),
                //.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "First name",
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = { Text(text = "Tilly")},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = LittleLemonColor.cloud
                )
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "Last name",
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = { Text(text = "Doe")},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = LittleLemonColor.cloud
                )
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it},
                placeholder = { Text(text = "tillydoe@example.com")},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = LittleLemonColor.cloud
                )
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LittleLemonColor.yellow,// Material 3
                    contentColor = Color.Black
                ),
                onClick = {
                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()){
                        Toast.makeText(context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_LONG).show()
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                        //scope.launch {
                            preferencesHandler.saveData("FIRST_NAME", firstName)
                            preferencesHandler.saveData("LAST_NAME", lastName)
                            preferencesHandler.saveData("EMAIL", email)
                        }

                        Toast.makeText(context,
                            "Registration successful!",
                            Toast.LENGTH_LONG).show()

                        navController.navigate(Home.route)
                    }
                }
            ) {
                Text(
                    text = "Register",
                    fontFamily = KarlaRegularFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                    //style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    FoodOrderingTheme {
        Onboarding(navController = rememberNavController())
    }
}
package com.example.plusshoping.presentation.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.plusshoping.R
import com.example.plusshoping.presentation.viewModel.RegisterViewModel

class RegisterScreen:Screen {
    @Composable
    override fun Content() {

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var number by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        val context = LocalContext.current
        val registerViewModel: RegisterViewModel = hiltViewModel()

        val errorMessage by remember { mutableStateOf<String?>(null) }

        val navigator = LocalNavigator.currentOrThrow




            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){

                        // App logo and title
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,


                            )

                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(id = R.string.thankYouForChoosingUs),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(id = R.string.letsHelpYouMeetUp),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.appColor)
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        // Full Name Input
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(stringResource(id = R.string.firstName)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.profile_icon), // Replace with your icon
                                    contentDescription = "Name Icon",
                                )                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Email Input
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(stringResource(id = R.string.email)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.email_icon), // Replace with your icon
                                    contentDescription = "Email Icon",
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Phone Number Input
                        OutlinedTextField(
                            value = number,
                            onValueChange = { number = it },
                            label = { Text(stringResource(id = R.string.phoneNumber)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.phone_icon), // Replace with your icon
                                    contentDescription = "Phone Icon",
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Password Input
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(stringResource(id = R.string.password)) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock_icon), // Replace with your icon
                                    contentDescription = "Password Icon",
                                )
                            }
                            ,visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                            trailingIcon = {
                                val image =
                                    if (passwordVisible) {
                                        painterResource(id = R.drawable.show_password)
                                    } else {
                                        painterResource(id = R.drawable.hide_password)
                                    }

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        painter = image,
                                        contentDescription = "Toggle password visibility"
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Confirm Password Input
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text(stringResource(id = R.string.confirmPassword)) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock_icon), // Replace with your icon
                                    contentDescription = "Confirm Icon",
                                )                            }
                            ,visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                            trailingIcon = {
                                val image =
                                    if (passwordVisible) {
                                        painterResource(id = R.drawable.show_password)
                                    } else {
                                        painterResource(id = R.drawable.hide_password)
                                    }

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        painter = image,
                                        contentDescription = "Toggle password visibility"
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Sign Up Button
                        Button(
                            onClick = {
//                                viewModel.register(email = email, name = name, phone = phoneNumber, password = password)
                                registerViewModel.register(email = email, password = password,name = name,phone = number,
                                    { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
                                    ,{ Toast.makeText(context, it, Toast.LENGTH_LONG).show() })
                                      },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.appColor))
                        ) {
                            Text(
                                text = stringResource(id = R.string.signUp),
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Sign In Text
                        TextButton(onClick = { navigator.push(LoginScreen()) }) {
                            Row {
                                Text(
                                    text = stringResource(id = R.string.alreadyHaveAnAccount),
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = stringResource(id = R.string.signIn)
                                    ,fontSize = 16.sp
                                    ,color = colorResource(id = R.color.appColor))
                            }
                        }

                        errorMessage?.let {
                            Text(text = it, color = Color.Red)
                        }
            }










        }
    }







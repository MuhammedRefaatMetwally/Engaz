package com.example.engaz.features.profile.view.screens

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.engaz.R
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.core.views.components.CustomProgressIndicator
import com.example.engaz.core.views.components.CustomTextField
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.destinations.MainScreenDestination
import com.example.engaz.features.profile.view.components.Header
import com.example.engaz.features.profile.view.components.PhoneNumberField
import com.example.engaz.features.profile.view.components.UsernameField
import com.example.engaz.features.profile.view.viewmodels.edit_profile.EditProfileState
import com.ramcosta.composedestinations.annotation.Destination
import com.togitech.ccp.component.TogiCountryCodePicker
import com.togitech.ccp.data.CountryData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun EditProfileScreen(
    navigator: DestinationsNavigator?,
    profileState: EditProfileState = EditProfileState(),
    updateUsername : (String)-> Unit = {},
    updatePhone : (String)-> Unit = {},
    updateProfileImage : (Uri?)-> Unit = {},
    onSave : (DestinationsNavigator,Context)-> Unit = {_,_-> },

    ){

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            updateProfileImage(it)
        }
    )

    val context: Context = LocalContext.current



    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(30.dp))


            Header(
                label = stringResource(id = R.string.edit_profile_ar),
                onClick = {
                    navigator?.let {
                        navigator.popBackStack()
                    }
                }
            )

            Spacer(modifier = Modifier.height(45.dp))

            Box(
                modifier = Modifier.height(146.dp)

            ) {
                Surface(
                    modifier = Modifier
                        .size(132.dp),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Image( painter = rememberImagePainter(
                        data =R.drawable.profile,
                        builder = {
                            transformations(CircleCropTransformation()) // Apply transformations if needed
                            placeholder(R.drawable.profile).size(50) // Placeholder resource while loading
                            error(R.drawable.profile).size(50) // Error resource if loading fails
                        }), contentDescription = "")
                }

                Surface(
                    Modifier
                        .size(32.dp)
                        .align(Alignment.BottomStart),
                    shape = CircleShape,
                    color = Primary,
                ){

                    Box(Modifier.fillMaxSize()) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(18.dp)
                                .clickable {
                                    singlePhotoPicker.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = null,
                            tint = Neutral100
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.height(50.dp))
            CustomTextField(
                value = "",
                onValueChange = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                placeHolder = stringResource(R.string.user_name_ar),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.profile_inactive),
                        contentDescription = ""
                    )
                },
                isError = false,
                errorMessage =  "",
                label = stringResource(R.string.user_name_ar)
            )

            Spacer(modifier = Modifier.height(8.dp))

            var phoneNumber: String by rememberSaveable { mutableStateOf("") }
            var fullPhoneNumber: String by rememberSaveable { mutableStateOf("") }
            var isNumberValid: Boolean by rememberSaveable { mutableStateOf(false) }

            TogiCountryCodePicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onValueChange = { (code, phone), isValid ->

                    phoneNumber = phone
                    fullPhoneNumber = code + phone
                    isNumberValid = isValid
                },
                fallbackCountry = CountryData.Egypt,
                label = { Text(stringResource(id = R.string.phone_number_ar)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = Neutral500,
                    focusedBorderColor = colorResource(id = R.color.primary_color),
                    errorBorderColor = colorResource(id = R.color.primary_color),
                    errorTrailingIconColor = colorResource(id = R.color.primary_color),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = "",
                onValueChange = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                isError = false,
                errorMessage = "",
                label = stringResource(R.string.email_ar)

            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = "",
                onValueChange = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                isError = false,
                errorMessage = "",
                label = stringResource(R.string.data_of_birth_ar)

            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                value = "",
                onValueChange = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                isError = false,
                errorMessage = "",
                label = stringResource(R.string.address_ar)

            )

            Spacer(modifier = Modifier.height(16.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {
                            navigator.navigate(MainScreenDestination)
                        }
                    },
                cardColor = Primary,
                borderColor = Color.Transparent
            ) {

                if (profileState.profileIsLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = stringResource(R.string.save_ar),
                        style = TextStyle(
                            fontFamily = Cairo,
                            color = Color.White,
                            fontWeight = FontWeight.W700,
                            fontSize = 20.sp,
                        )
                    )
                }

            }


        }
    }



}



@Preview
@Composable
fun EditProfileScreenPreview(){
    EditProfileScreen(navigator = null)
}
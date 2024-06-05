package com.example.engaz.core.views

//import com.example.marketapp.destinations.MethodsScreenDestination
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.core.views.screens.DoneMessageScreen
import com.example.engaz.core.views.screens.OnBoardingScreen
import com.example.engaz.core.views.screens.SplashScreen
import com.example.engaz.features.auth.view.screens.login.LoginScreen
import com.example.engaz.features.auth.view.screens.login_with_fingerprint.LoginWithFingerPrintScreen
import com.example.engaz.features.auth.view.screens.register.ActivationPinScreen
import com.example.engaz.features.auth.view.screens.register.RegisterScreen
import com.example.engaz.features.auth.view.screens.reset_password.*
import com.example.engaz.features.auth.view.viewmodels.login.LoginEvent
import com.example.engaz.features.auth.view.viewmodels.login.LoginViewModel
import com.example.engaz.features.auth.view.viewmodels.loginWithFingerPrint.LoginWithFignerPrintEvent
import com.example.engaz.features.auth.view.viewmodels.loginWithFingerPrint.LoginWithFignerPrintViewModel
import com.example.engaz.features.auth.view.viewmodels.register.RegisterEvent
import com.example.engaz.features.auth.view.viewmodels.register.RegisterViewModel
import com.example.engaz.features.auth.view.viewmodels.reset_password.ResetPasswordMethodsEvent
import com.example.engaz.features.auth.view.viewmodels.reset_password.ResetPasswordViewModel
import com.example.engaz.features.home.view.screens.main.MainScreen
import com.example.engaz.features.home.view.screens.main_info_screens.CompletedRequestsScreen
import com.example.engaz.features.home.view.screens.main_info_screens.InfoAboutCarScreen
import com.example.engaz.features.home.view.screens.main_info_screens.InfoAboutServiceScreen
import com.example.engaz.features.home.view.screens.main_info_screens.RenewLicenseScreen
import com.example.engaz.features.home.view.screens.main_info_screens.onPaymentSheetResult
import com.example.engaz.features.home.view.screens.main_info_screens.presentPaymentSheet
import com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership.AcceptedRequestDetails
import com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership.CompletePaymentScreen
import com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership.RequestsScreen
import com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership.SendTransferingRequestDetailsScreen
import com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership.SendTransferingRequestScreen
import com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership.TransferCarOwnershipScreen
import com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership.onPaymentSheetAcceptedRequestResult
import com.example.engaz.features.home.view.viewmodels.main.MainViewModel
import com.example.engaz.features.home.view.viewmodels.main_info.complete_payment.CompletePaymentViewModel
import com.example.engaz.features.home.view.viewmodels.main_info.completed_requests.CompletedRequestsEvent
import com.example.engaz.features.home.view.viewmodels.main_info.completed_requests.CompletedRequestsViewModel
import com.example.engaz.features.home.view.viewmodels.main_info.info_about_service.HomeInfoCarEvent
import com.example.engaz.features.home.view.viewmodels.main_info.info_about_service.HomeInfoCarViewModel
import com.example.engaz.features.home.view.viewmodels.main_info.info_about_service.HomeInfoEvent
import com.example.engaz.features.home.view.viewmodels.main_info.info_about_service.HomeInfoViewModel
import com.example.engaz.features.home.view.viewmodels.main_info.renew_license.RenewLicenseEvent
import com.example.engaz.features.home.view.viewmodels.main_info.renew_license.RenewLicenseViewModel
import com.example.engaz.features.home.view.viewmodels.main_info.transfer_car_ownership.TransferCarOwnerShipEvent
import com.example.engaz.features.home.view.viewmodels.main_info.transfer_car_ownership.TransferCarOwnerShipViewModel
import com.example.engaz.features.identity.views.pages.FaceRecognitionScreen
import com.example.engaz.features.identity.views.viewmodel.IdentityEvent
import com.example.engaz.features.identity.views.viewmodel.IdentityViewModel
import com.example.engaz.features.identity.views.pages.IdentityScreen
import com.example.engaz.features.notification.views.pages.NotificationsPage
import com.example.engaz.features.notification.views.viewmodel.NotificationEvent
import com.example.engaz.features.notification.views.viewmodel.NotificationViewModel
import com.example.engaz.features.profile.view.pages.ProfilePage
import com.example.engaz.features.transactions.view.screens.*
import com.example.engaz.features.transactions.view.viewmodel.select_location.SelectLocationEvent
import com.example.engaz.features.transactions.view.viewmodel.select_location.SelectLocationViewModel
import com.example.engaz.features.profile.view.screens.EditProfileScreen
import com.example.engaz.features.profile.view.viewmodels.edit_profile.EditProfileEvent
import com.example.engaz.features.profile.view.viewmodels.edit_profile.EditProfileViewModel
import com.example.engaz.features.wallet.view.pages.ChargeBalanceScreen
import com.example.engaz.features.wallet.view.pages.WalletPage
import com.example.engaz.features.wallet.view.screens.WalletMessageScreen
import com.example.engaz.features.wallet.view.viewmodel.wallet.WalletEvent
import com.example.engaz.features.wallet.view.viewmodel.wallet.WalletViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.rememberPaymentSheet
import io.github.raamcosta.compose_destinations.NavGraphs
import io.github.raamcosta.compose_destinations.destinations.AcceptedRequestDetailsDestination
import io.github.raamcosta.compose_destinations.destinations.ActivationPinScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ChargeBalanceScreenDestination
import io.github.raamcosta.compose_destinations.destinations.CompletePaymentScreenDestination
import io.github.raamcosta.compose_destinations.destinations.CompletedRequestsScreenDestination
import io.github.raamcosta.compose_destinations.destinations.DoneMessageScreenDestination
import io.github.raamcosta.compose_destinations.destinations.EditProfileScreenDestination
import io.github.raamcosta.compose_destinations.destinations.FaceRecognitionScreenDestination
import io.github.raamcosta.compose_destinations.destinations.IdentityScreenDestination
import io.github.raamcosta.compose_destinations.destinations.InfoAboutCarScreenDestination
import io.github.raamcosta.compose_destinations.destinations.InfoAboutServiceScreenDestination
import io.github.raamcosta.compose_destinations.destinations.LoginScreenDestination
import io.github.raamcosta.compose_destinations.destinations.LoginWithFingerPrintScreenDestination
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import io.github.raamcosta.compose_destinations.destinations.NotificationsPageDestination
import io.github.raamcosta.compose_destinations.destinations.OnBoardingScreenDestination
import io.github.raamcosta.compose_destinations.destinations.OrderMessageScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ProfilePageDestination
import io.github.raamcosta.compose_destinations.destinations.RegisterScreenDestination
import io.github.raamcosta.compose_destinations.destinations.RenewLicenseScreenDestination
import io.github.raamcosta.compose_destinations.destinations.RequestsScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ResetPasswordByPhoneScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ResetPasswordNewPasswordScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ResetPasswordPinScreenDestination
import io.github.raamcosta.compose_destinations.destinations.SearchLocationFromScreenDestination
import io.github.raamcosta.compose_destinations.destinations.SearchLocationToScreenDestination
import io.github.raamcosta.compose_destinations.destinations.SelectLocationScreenDestination
import io.github.raamcosta.compose_destinations.destinations.SendTransferingRequestDetailsScreenDestination
import io.github.raamcosta.compose_destinations.destinations.SendTransferingRequestScreenDestination
import io.github.raamcosta.compose_destinations.destinations.SplashScreenDestination
import io.github.raamcosta.compose_destinations.destinations.TransferCarOwnershipScreenDestination
import io.github.raamcosta.compose_destinations.destinations.WalletMessageScreenDestination
import io.github.raamcosta.compose_destinations.destinations.WalletPageDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Navigation(
    coreViewModel: CoreViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    loginWithFignerPrintViewModel: LoginWithFignerPrintViewModel = hiltViewModel(),
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    homeInfoViewModel: HomeInfoViewModel = hiltViewModel(),
    homeInfoCarViewModel: HomeInfoCarViewModel = hiltViewModel(),
    transferCarOwnerShipViewModel: TransferCarOwnerShipViewModel = hiltViewModel(),
    renewLicenseViewModel: RenewLicenseViewModel = hiltViewModel(),
    completedRequestsViewModel: CompletedRequestsViewModel = hiltViewModel(),
    notificationViewModel: NotificationViewModel = hiltViewModel(),
    identityViewModel: IdentityViewModel = hiltViewModel(),
    editProfileViewModel: EditProfileViewModel = hiltViewModel(),
    completePaymentViewModel: CompletePaymentViewModel = hiltViewModel(),
    locationViewModel: SelectLocationViewModel = hiltViewModel(),
    walletViewModel: WalletViewModel = hiltViewModel(),
    profileViewModel: EditProfileViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()

    Box {
        DestinationsNavHost(NavGraphs.root) {

            composable(SplashScreenDestination) {
                SplashScreen(navigator = destinationsNavigator,
                    onScreenLaunch = { scope.launch { coreViewModel.onSplashScreenLaunch(it) } })
            }

            composable(OnBoardingScreenDestination) {
                OnBoardingScreen(
                    onSkipClick = { coreViewModel.onOnBoardingScreenSkipClick(it) },
                    onNextClick = { coreViewModel.onOnBoardingScreenNextClick(it) },
                    navigator = destinationsNavigator
                )
            }


            composable(LoginScreenDestination) {
                LoginScreen(
                    navigator = destinationsNavigator,
                    state = loginViewModel.state,
                    onChangeEmailOrPassCode = { loginViewModel.updateEmailOrPassCode(it) },
                    onChangePhoneWithCountryCode = { loginViewModel.updatePhoneWithCountryCode(it) },
                    onChangePassword = { loginViewModel.updatePassword(it) },
                    onRememberMeClick = { loginViewModel.onEvent(LoginEvent.RememberMe) },
                    onLoginClick = { navigator, context ->
                        loginViewModel.onEvent(
                            LoginEvent.Login(
                                navigator,
                                context
                            )
                        )
                    },
                    onRegisterClick = { loginViewModel.onEvent(LoginEvent.Register(it)) },
                    onForgotPasswordClick = { loginViewModel.onEvent(LoginEvent.ForgotPassword(it)) },
                    onSecurePasswordClick = { loginViewModel.updatePasswordSecureState() },
                    onBackArrowClick = { loginViewModel.onEvent(LoginEvent.OnBackClick(it)) },
                )
            }

            composable(LoginWithFingerPrintScreenDestination) {
                LoginWithFingerPrintScreen(navigator = destinationsNavigator, onBackArrowClick = {
                    loginWithFignerPrintViewModel.onEvent(LoginWithFignerPrintEvent.OnBackClick(it))
                })
            }

            composable(ResetPasswordByPhoneScreenDestination) {
                ResetPasswordByPhoneScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onPhoneChange = { resetPasswordViewModel.updatePhoneNumber(it) },
                    onPhoneWithCountryCode = {
                        resetPasswordViewModel.updatePhoneNumberWithCountryCode(
                            it
                        )
                    },
                    onNextClick = { navigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnSendCodeToPhoneClick(
                                navigator, context
                            )
                        )
                    },
                )
            }


            composable(ResetPasswordPinScreenDestination) {
                ResetPasswordPinScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onPinChangeClick = { resetPasswordViewModel.updatePin(it) },
                    onValidateClick = { navaigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnValidateClick(
                                navaigator, context
                            )
                        )
                    },
                    onComplete = { navaigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnValidateClick(
                                navaigator, context
                            )
                        )
                    },
                    onResendClick = { navigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnResendClickClick(navigator, context)
                        )
                    })
            }

            composable(ResetPasswordNewPasswordScreenDestination) {
                ResetPasswordNewPasswordScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onDoneClick = { navigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnSettingNewPasswordClick(navigator, context)
                        )
                    },
                    onNewPasswordChange = { resetPasswordViewModel.updateNewPassword(it) },
                    onNewPasswordRpeatedChange = {
                        resetPasswordViewModel.updateNewPasswordRepeated(
                            it
                        )
                    }
                )

            }

            composable(DoneMessageScreenDestination) {
                DoneMessageScreen(
                    navigator = destinationsNavigator,
                    onButtonTap = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnDoneMessageScreenClick(
                                it
                            )
                        )
                    }
                )

            }

            composable(RegisterScreenDestination) {
                RegisterScreen(
                    navigator = destinationsNavigator,
                    state = registerViewModel.state,
                    onBackArrowClick = { registerViewModel.onEvent(RegisterEvent.OnBackClick(it)) },
                    onChangePhone = { navigator, context ->
                        registerViewModel.updatePhone(
                            navigator,
                            context
                        )
                    },
                    onChangePassCode = {
                        registerViewModel.updatePassCOde(it)
                    },
                    onChangePhoneWithCountryCode = { registerViewModel.updatePhoneWithCountryCode(it) },
                    onChangePassword = { registerViewModel.updatePassword(it) },
                    onChangePasswordRenter = { registerViewModel.updatePasswordRenter(it) },
                    onSecurePasswordClick = { registerViewModel.updateIsPasswordSecure() },
                    onSecurePasswordRenterClick = { registerViewModel.updateIsPasswordRenterSecure() },
                    onLoginClick = { registerViewModel.onEvent(RegisterEvent.OnLoginClick(it)) },
                    onRegisterClick = { navigator, context ->
                        registerViewModel.onEvent(
                            RegisterEvent.Register(navigator, context)
                        )
                    },
                    onChangeFullName = {
                        registerViewModel.updateFullName(it)
                    },
                    onTermsClick = {
                        registerViewModel.updateTerms()
                    },


                    )
            }

            composable(InfoAboutServiceScreenDestination) {
                InfoAboutServiceScreen(navigator = destinationsNavigator, onBackArrowClick = {
                    homeInfoViewModel.onEvent(HomeInfoEvent.OnBackClick(it))
                })
            }

            composable(InfoAboutCarScreenDestination) {
                InfoAboutCarScreen(navigator = destinationsNavigator, onBackArrowClick = {
                    homeInfoCarViewModel.onEvent(HomeInfoCarEvent.OnBackClick(it))
                })
            }

            composable(TransferCarOwnershipScreenDestination) {
                TransferCarOwnershipScreen(navigator = destinationsNavigator, onBackArrowClick = {
                    transferCarOwnerShipViewModel.onEvent(TransferCarOwnerShipEvent.OnBackClick(it))
                })
            }
            composable(CompletedRequestsScreenDestination) {
                CompletedRequestsScreen(navigator = destinationsNavigator, onBackArrowClick = {
                    completedRequestsViewModel.onEvent(CompletedRequestsEvent.OnBackClick(it))
                })
            }

            composable(RenewLicenseScreenDestination) {
                val paymentSheet = rememberPaymentSheet {
                    onPaymentSheetResult(it, renewLicenseViewModel.showDialog)
                }
                var customerConfig by remember {
                    mutableStateOf<PaymentSheet.CustomerConfiguration?>(
                        null
                    )
                }
                var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }
                RenewLicenseScreen(
                    navigator = destinationsNavigator,
                    state = renewLicenseViewModel.state,
                    onBackArrowClick = {
                        renewLicenseViewModel.onEvent(RenewLicenseEvent.OnBackClick(it))
                    },
                    onRenewLicense = { navigator, context ->
                        renewLicenseViewModel.onEvent(
                            RenewLicenseEvent.OnRenewLicense(
                                navigator ?: destinationsNavigator,
                                context = context,
                                onPayment = {
                                    scope.launch(Dispatchers.IO) {
                                        walletViewModel.makePayment { clientSecret, customerId, ephemeralKey ->
                                            paymentIntentClientSecret = clientSecret
                                            customerConfig = PaymentSheet.CustomerConfiguration(
                                                customerId ?: "",
                                                ephemeralKey ?: ""
                                            )
                                        }
                                        presentPaymentSheet(
                                            paymentSheet,
                                            paymentIntentClientSecret!!,
                                            customerConfig!!
                                        )
                                    }
                                }
                            )
                        )

                    },
                    showDialog = renewLicenseViewModel.showDialog,
                    onChangeCarDescription = {
                        renewLicenseViewModel.updateCarDescription(it)
                    },
                    onChangeCurrentAddress = { renewLicenseViewModel.updateCurrentAddress(it) },
                    onChangeDriverName = { renewLicenseViewModel.updateDriverName(it) },
                    onChangeEmail = { renewLicenseViewModel.updateEmail(it) },
                    onChangeExpiryDate = { renewLicenseViewModel.updateExpiryDate(it) },
                    onChangeLicenseNumber = { renewLicenseViewModel.updateLicenseNumber(it) },
                    onChangePhoneNumber = { renewLicenseViewModel.updatePhone(it) },
                    onChangeRequestNumber = { renewLicenseViewModel.updateRequestNumber(it) }
                )
            }

            composable(NotificationsPageDestination) {
                NotificationsPage(navigator = destinationsNavigator, onBackArrowClick = {
                    notificationViewModel.onEvent(NotificationEvent.OnBackClick(it))
                })
            }

            composable(ProfilePageDestination) {
                ProfilePage(navigator = destinationsNavigator, onBackArrowClick = {
                    editProfileViewModel.onEvent(EditProfileEvent.OnBackClick(it))
                })
            }

            composable(SendTransferingRequestScreenDestination) {
                SendTransferingRequestScreen(
                    navigator = destinationsNavigator,
                    onBackArrowClick = {
                        transferCarOwnerShipViewModel.onEvent(
                            TransferCarOwnerShipEvent.OnBackClick(
                                it
                            )
                        )
                    })
            }
            composable(SendTransferingRequestDetailsScreenDestination) {
                SendTransferingRequestDetailsScreen(
                    navigator = destinationsNavigator,
                    onBackArrowClick = {
                        transferCarOwnerShipViewModel.onEvent(
                            TransferCarOwnerShipEvent.OnBackClick(
                                it
                            )
                        )
                    })
            }
            composable(RequestsScreenDestination) {
                RequestsScreen(navigator = destinationsNavigator, onBackArrowClick = {
                    transferCarOwnerShipViewModel.onEvent(TransferCarOwnerShipEvent.OnBackClick(it))
                })
            }
            composable(AcceptedRequestDetailsDestination) {
                val paymentSheet = rememberPaymentSheet {
                    onPaymentSheetAcceptedRequestResult(it, transferCarOwnerShipViewModel.showDialog)
                }
                var customerConfig by remember {
                    mutableStateOf<PaymentSheet.CustomerConfiguration?>(
                        null
                    )
                }
                var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }
                AcceptedRequestDetails(navigator = destinationsNavigator, onBackArrowClick = {
                    transferCarOwnerShipViewModel.onEvent(TransferCarOwnerShipEvent.OnBackClick(it))
                }, onAcceptRequest = {
                    transferCarOwnerShipViewModel.onEvent(
                        TransferCarOwnerShipEvent.OnAcceptRequest(
                            destinationsNavigator
                        ) {
                            scope.launch(Dispatchers.IO) {
                                walletViewModel.makePayment { clientSecret, customerId, ephemeralKey ->
                                    paymentIntentClientSecret = clientSecret
                                    customerConfig = PaymentSheet.CustomerConfiguration(
                                        customerId ?: "",
                                        ephemeralKey ?: ""
                                    )
                                }
                                presentPaymentSheet(
                                    paymentSheet,
                                    paymentIntentClientSecret!!,
                                    customerConfig!!
                                )
                            }
                        })

                })
            }
            composable(IdentityScreenDestination) {
                IdentityScreen(navigator = destinationsNavigator, onBackArrowClick = {
                    identityViewModel.onEvent(IdentityEvent.OnBackClick(it))
                })
            }
            composable(FaceRecognitionScreenDestination) {
                FaceRecognitionScreen(navigator = destinationsNavigator, onBackArrowClick = {
                    identityViewModel.onEvent(IdentityEvent.OnBackClick(it))
                })
            }

            composable(CompletePaymentScreenDestination) {
                CompletePaymentScreen(destinationsNavigator, onBackArrowClick = {
                    transferCarOwnerShipViewModel.onEvent(TransferCarOwnerShipEvent.OnBackClick(it))
                }, onSecureCVCClick = {
                    completePaymentViewModel.updateCVCSecureState()
                })
            }
            composable(WalletPageDestination) {
                WalletPage(navigator = destinationsNavigator, onBackArrowClick = {
                    walletViewModel.onEvent(WalletEvent.OnBackClick(it))
                })
            }
            composable(ActivationPinScreenDestination) {
                ActivationPinScreen(
                    navigator = destinationsNavigator,
                    state = registerViewModel.state,
                    onBackArrowClick = {
                        registerViewModel.onEvent(
                            RegisterEvent.OnBackClick(
                                it
                            )
                        )
                    },
                    onPinChangeClick = { registerViewModel.updatePin(it) },
                    onValidateClick = { navaigator, context ->
                        registerViewModel.onEvent(
                            RegisterEvent.OnValidateClick(
                                navaigator, context
                            )
                        )
                    },
                    onComplete = { navaigator, context ->
                        registerViewModel.onEvent(
                            RegisterEvent.OnValidateClick(
                                navaigator, context
                            )
                        )
                    },
                    onResendClick = { navigator, context ->
                        registerViewModel.onEvent(
                            RegisterEvent.OnResendClick(navigator, context)
                        )
                    })
            }

            composable(MainScreenDestination) {
                MainScreen(
                    state = mainViewModel.state,
                    navigator = destinationsNavigator,
                    onIndexChange = {
                        mainViewModel.updateCurrentIndex(it)
                    }
                )
            }



            composable(SelectLocationScreenDestination) {
                SelectLocationScreen(
                    state = locationViewModel.state,
                    navigator = destinationsNavigator,
                    onFromLocationClick = {
                        locationViewModel.onEvent(
                            SelectLocationEvent.OnFromLocationClick(
                                it
                            )
                        )
                    },
                    onToLocationClick = {
                        locationViewModel.onEvent(
                            SelectLocationEvent.OnToLocationClick(
                                it
                            )
                        )
                    },
                    onNextClick = { navigator, context ->
                        locationViewModel.onEvent(
                            SelectLocationEvent.OnLocationSelected(navigator, context)
                        )
                    },
                    getDirections = { locationViewModel.onEvent(SelectLocationEvent.GetDirections) }
                )

            }


            composable(SearchLocationToScreenDestination) {
                SearchLocationToScreen(
                    state = locationViewModel.state,
                    navigator = destinationsNavigator,
                    onQueryChange = { locationViewModel.onSearchUpdate(it) },
                    onLocationSelect = { navigator, placeInfo ->
                        locationViewModel.onEvent(
                            SelectLocationEvent.OnFromLocationSelect(navigator, placeInfo)
                        )
                    },
                    onSearch = { }
                )

            }

            composable(SearchLocationFromScreenDestination) {
                SearchLocationFromScreen(
                    state = locationViewModel.state,
                    navigator = destinationsNavigator,
                    onQueryChange = { locationViewModel.onSearchUpdate(it) },
                    onLocationSelect = { navigator, placeInfo ->
                        locationViewModel.onEvent(
                            SelectLocationEvent.OnToLocationSelect(navigator, placeInfo)
                        )
                    },
                    onSearch = { }
                )

            }

            composable(OrderMessageScreenDestination) {
                OrderMessageScreen(
                    navigator = destinationsNavigator,
                    onButtonTap = { it.navigate(MainScreenDestination) }

                )

            }

            composable(ChargeBalanceScreenDestination) {
                /* ChargeBalanceScreen(
                     navigator = destinationsNavigator,
                     walletState = walletViewModel.state,
                     onImageSelection = { walletViewModel.updateImageState(it) },
                     onUploadImage = { navigator, context ->
                         walletViewModel.onEvent(
                             WalletEvent.OnBalanceRecharge(
                                 navigator,
                                 context
                             )
                         )
                     },
                 )*/

            }

            composable(WalletMessageScreenDestination) {
                WalletMessageScreen(
                    navigator = destinationsNavigator,
                    onButtonTap = { it.navigate(MainScreenDestination) }
                )

            }

            composable(EditProfileScreenDestination) {
                EditProfileScreen(
                    navigator = destinationsNavigator,
                    profileState = profileViewModel.state,
                    updatePhone = { profileViewModel.updatePhone(it) },
                    updateUsername = { profileViewModel.updateUsername(it) },
                    updateProfileImage = { profileViewModel.updateProfileImage(it) },
                    onSave = { navigator, context ->
                        profileViewModel.onEvent(
                            EditProfileEvent.OnSave(
                                navigator,
                                context
                            )
                        )
                    }
                )

            }

        }




        SnackbarHost(
            modifier = Modifier.align(Alignment.TopCenter),
            hostState = CoreViewModel.snackbarHostState,
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                color = if (isSystemInDarkTheme()) Neutral800 else Neutral200
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {

                    val messages = it.visuals.message.split(":")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = messages[0],
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                    fontSize = 20.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            messages.forEachIndexed { index, text ->
                                if (index != 0) Text(
                                    text = text,
                                    style = TextStyle(
                                        fontFamily = Lato,
                                        color = if (isSystemInDarkTheme()) Neutral400 else Neutral600,
                                        fontSize = 16.sp
                                    )
                                )
                            }

                        }
                        Icon(
                            modifier = Modifier.clickable {
                                CoreViewModel.snackbarHostState.currentSnackbarData?.dismiss()
                            },
                            painter = painterResource(id = R.drawable.close_circle),
                            contentDescription = null,
                            tint = if (isDebugInspectorInfoEnabled) Neutral300 else Neutral700
                        )
                    }

                }
            }
        }
    }

}
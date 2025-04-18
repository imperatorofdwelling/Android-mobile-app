package com.imperatorofdwelling.android.presentation.ui.landlord.creating

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.dwelling.Adults
import com.imperatorofdwelling.android.presentation.ui.components.Attention
import com.imperatorofdwelling.android.presentation.ui.components.DefaultTopBar
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.EditTextTrailing
import com.imperatorofdwelling.android.presentation.ui.home_screen.ResidentsItem
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.Plate
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.components.Help
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.components.PhotoPickerWithPreview
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.components.StepCounter
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.components.TypeSelector
import com.imperatorofdwelling.android.presentation.ui.theme.GreyDividerColor
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import org.koin.androidx.compose.koinViewModel

class CreatingScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CreatingViewModel>()
        val state = viewModel.state.collectAsState()
        val locale = LocalConfiguration.current.locales[0]
        val imageUris = viewModel.imageUris
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                DefaultTopBar(
                    title = if (state.value.showCreatingHelp) {
                        ""
                    } else stringResource(R.string.creating_an_advert),
                    onBackPressed = {
                        if(state.value.step != 1){
                            viewModel.previousStep()
                        } else {
                            navigator.pop()
                        }
                    }
                )
            }
        ) { paddingValues ->
            if (state.value.showCreatingHelp) {
                Help(
                    modifier = Modifier.padding(paddingValues),
                    onClickGetStarted = { viewModel.updateCreatingHelpScreen(false) },
                )
            } else {
                ContentBody(
                    modifier = Modifier.padding(paddingValues),
                    step = state.value.step,
                    onTitleChanged = viewModel::onTitleChanged,
                    houseSelected = state.value.houseSelected,
                    houseClick = viewModel::houseClick,
                    apartmentSelected = state.value.apartmentSelected,
                    apartmentClick = viewModel::apartmentClick,
                    hotelSelected = state.value.hotelSelected,
                    hotelClick = viewModel::hotelClick,
                    titleValue = state.value.title,
                    approachNumberValue = state.value.approachNumberValue,
                    onApproachNumberChanged = viewModel::onApproachNumberChanged,
                    apartmentNumberValue = state.value.apartmentNumberValue,
                    onApartmentNumberChanged = viewModel::onApartmentNumberChanged,
                    currentAddress = if (state.value.currentAddress != null) {
                        if (locale.language == "ru") state.value.currentAddress!!.toRussianString()
                        else state.value.currentAddress.toString()
                    } else "",
                    addressCancel = viewModel::addressCancel,
                    numberOfRoomsList = state.value.numberOfRoomsList,
                    onNextStep = viewModel::onNextStep,
                    imageUris = imageUris,
                    onImageSelected = viewModel::onImageSelected,
                    onImageCancel = viewModel::onImageCancel,
                    onReorder = viewModel::onReorder,
                    onNumberOfRoomsSelected = viewModel::onNumberOfRoomsSelected,
                    numberOfRoomsSelected = state.value.numberOfRoomsSelected,
                    residentsCount = state.value.residentsCount,
                    onResidentsCountChanged = viewModel::onResidentsCountChanged,
                    numberOfBedsSelected = state.value.numberOfBedsSelected,
                    numberOfBedsList = state.value.numberOfBedsList,
                    onNumberOfBedsSelected = viewModel::onNumberOfBedsSelected,
                )
            }
        }
    }


    @Composable
    private fun ContentBody(
        step: Int,
        onTitleChanged: (String) -> Unit,
        titleValue: String,
        modifier: Modifier = Modifier,
        houseSelected: Boolean,
        houseClick: (Boolean) -> Unit,
        apartmentSelected: Boolean,
        apartmentClick: (Boolean) -> Unit,
        hotelSelected: Boolean,
        hotelClick: (Boolean) -> Unit,
        apartmentNumberValue: String,
        onApartmentNumberChanged: (String) -> Unit,
        approachNumberValue: String,
        onApproachNumberChanged: (String) -> Unit,
        currentAddress: String,
        addressCancel: () -> Unit = {},
        numberOfRoomsList: List<String>,
        onNextStep: () -> Unit,
        imageUris: List<Uri>,
        onImageSelected: (Uri) -> Unit,
        onImageCancel: (Uri) -> Unit,
        onReorder: (List<Uri>) -> Unit,
        onNumberOfRoomsSelected: (String) -> Unit,
        numberOfRoomsSelected: String,
        residentsCount: Int,
        onResidentsCountChanged: (Int) -> Unit,
        numberOfBedsList: List<String>,
        numberOfBedsSelected: String,
        onNumberOfBedsSelected: (String) -> Unit
    ) {

        when (step) {
            1 -> {
                FirstStep(
                    onTitleChanged = onTitleChanged,
                    titleValue = titleValue,
                    houseSelected = houseSelected,
                    houseClick = houseClick,
                    apartmentSelected = apartmentSelected,
                    apartmentClick = apartmentClick,
                    hotelSelected = hotelSelected,
                    hotelClick = hotelClick,
                    currentAddress = currentAddress,
                    addressCancel = addressCancel,
                    onApproachNumberChanged = onApproachNumberChanged,
                    approachNumberValue = approachNumberValue,
                    onApartmentNumberChanged = onApartmentNumberChanged,
                    apartmentNumberValue = apartmentNumberValue,
                    numberOfRoomsList = numberOfRoomsList,
                    onNextStep = onNextStep,
                    modifier = modifier,
                    onNumberOfRoomsSelected = onNumberOfRoomsSelected,
                    numberOfRoomsSelected = numberOfRoomsSelected,
                    residentsCount = residentsCount,
                    onResidentsCountChanged = onResidentsCountChanged,
                    numberOfBedsList = numberOfBedsList,
                    numberOfBedsSelected = numberOfBedsSelected,
                    onNumberOfBedsSelected = onNumberOfBedsSelected
                )
            }

            2 -> {
                SecondStep(
                    imageUris = imageUris,
                    onImageSelected = onImageSelected,
                    onImageCancel = onImageCancel,
                    onReorder = onReorder,
                    modifier = modifier
                )
            }

            3 -> {
                Spacer(Modifier.height(24.dp))
                HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
                Spacer(Modifier.height(24.dp))

            }

        }
    }

    @Composable
    private fun FirstStep(
        onTitleChanged: (String) -> Unit,
        titleValue: String,
        houseSelected: Boolean,
        houseClick: (Boolean) -> Unit,
        apartmentSelected: Boolean,
        apartmentClick: (Boolean) -> Unit,
        hotelSelected: Boolean,
        hotelClick: (Boolean) -> Unit,
        currentAddress: String,
        addressCancel: () -> Unit,
        onApproachNumberChanged: (String) -> Unit,
        approachNumberValue: String,
        onApartmentNumberChanged: (String) -> Unit,
        apartmentNumberValue: String,
        numberOfRoomsList: List<String>,
        numberOfRoomsSelected: String,
        onNumberOfRoomsSelected: (String) -> Unit,
        onNextStep: () -> Unit,
        modifier: Modifier = Modifier,
        residentsCount: Int = 1,
        onResidentsCountChanged: (Int) -> Unit,
        numberOfBedsList: List<String>,
        numberOfBedsSelected: String,
        onNumberOfBedsSelected: (String) -> Unit
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = extraLargeDp, horizontal = largeDp)
                .verticalScroll(rememberScrollState())
                .then(modifier)
        ) {
            Text(text = stringResource(R.string.step, 1), style = h3)
            Spacer(Modifier.height(10.dp))
            StepCounter(modifier = Modifier.fillMaxWidth(), 1)
            Spacer(Modifier.height(24.dp))
            HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
            Spacer(Modifier.height(24.dp))
            EditTextTrailing(
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = painterResource(id = R.drawable.cross),
                onValueChanged = {
                    onTitleChanged(it)
                },
                placeholderText = stringResource(R.string.title_of_the_advert_header),
                value = titleValue,
                onClickTrailing = { onTitleChanged("") }
            )
            Spacer(Modifier.height(16.dp))
            Text(text = stringResource(R.string.obligatory_field), style = h5)
            Spacer(Modifier.height(24.dp))
            Text(text = stringResource(R.string.what_you_ll_be_handing_in), style = h2)
            Spacer(Modifier.height(24.dp))
            TypeSelector(
                houseSelected = houseSelected,
                houseClick = houseClick,
                apartmentSelected = apartmentSelected,
                apartmentClick = apartmentClick,
                hotelSelected = hotelSelected,
                hotelClick = hotelClick
            )
            Spacer(Modifier.height(24.dp))
            HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
            Spacer(Modifier.height(24.dp))
            Text(text = stringResource(R.string.address), style = h2)
            Spacer(Modifier.height(16.dp))

            //val navigator = LocalNavigator.currentOrThrow
            EditTextTrailing(
                modifier = Modifier
                    .width(360.dp)
                    .clickable { /*navigator.push(AddressSelectionScreen())*/ },
                trailingIcon = painterResource(id = R.drawable.cross),
                onValueChanged = {

                },
                value = currentAddress,
                enabled = false,
                placeholderText = stringResource(R.string.city_and_street),
                onClickTrailing = {
                    addressCancel()
                },
                maxLines = 4,
                icon = painterResource(id = R.drawable.map_item),
            )
            Spacer(Modifier.height(16.dp))
            Row {
                EditTextTrailing(
                    modifier = Modifier.width(174.dp),
                    trailingIcon = painterResource(id = R.drawable.cross),
                    onValueChanged = {
                        onApproachNumberChanged(it)
                    },
                    placeholderText = stringResource(R.string.approach),
                    value = approachNumberValue,
                    onClickTrailing = { onApproachNumberChanged("") }
                )
                Spacer(Modifier.width(12.dp))
                EditTextTrailing(
                    modifier = Modifier.width(174.dp),
                    trailingIcon = painterResource(id = R.drawable.cross),
                    onValueChanged = {
                        onApartmentNumberChanged(it)
                    },
                    placeholderText = stringResource(R.string.apartment),
                    value = apartmentNumberValue,
                    onClickTrailing = { onApartmentNumberChanged("") }
                )
            }
            Spacer(Modifier.height(24.dp))
            HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
            Spacer(Modifier.height(24.dp))
            Text(text = stringResource(R.string.number_of_rooms), style = h2)
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (roomCount in numberOfRoomsList) {
                    Plate(
                        isActive = numberOfRoomsSelected == roomCount,
                        modifier = Modifier.size(44.dp),
                        onClick = { value ->
                            onNumberOfRoomsSelected(roomCount)
                        }
                    ) {
                        Text(style = h4_white, text = roomCount)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
            Spacer(Modifier.height(24.dp))
            Text(text = stringResource(R.string.number_of_residents), style = h2)
            Spacer(Modifier.height(24.dp))
            Attention(message = stringResource(R.string.indicate_how_many_guests_will_be_accommodated_in_all_main_and_extra_seats))
            Spacer(Modifier.height(24.dp))
            ResidentsItem(
                modifier = Modifier.fillMaxWidth(),
                textName = stringResource(R.string.maximum_residents),
                textCount = residentsCount.toString(),
                onPlusClick = { onResidentsCountChanged(residentsCount + 1)},
                onMinusClick = { onResidentsCountChanged(residentsCount - 1) }
            )
            Spacer(Modifier.height(24.dp))
            HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
            Spacer(Modifier.height(24.dp))
            Text(text = stringResource(R.string.number_of_beds), style = h2)
            Spacer(Modifier.height(24.dp))
            Attention(
                modifier = Modifier.fillMaxWidth(),
                message = stringResource(R.string.specify_the_number_and_types_of_main_and_extra_beds)
            )
            Spacer(Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (bedCount in numberOfBedsList) {
                    Plate(
                        isActive = bedCount == numberOfBedsSelected,
                        modifier = Modifier.size(44.dp),
                        onClick = { value ->
                            onNumberOfBedsSelected(bedCount)
                        }
                    ) {
                        Text(style = h4_white, text = bedCount)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            PrimaryButton(text = stringResource(R.string.continue_string)) {
                onNextStep()
            }
        }
    }

    @Composable
    private fun SecondStep(
        modifier: Modifier = Modifier,
        imageUris: List<Uri>,
        onImageSelected: (Uri) -> Unit,
        onImageCancel: (Uri) -> Unit,
        onReorder: (List<Uri>) -> Unit
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = extraLargeDp, horizontal = largeDp)
                .then(modifier)
        ) {
            Text(text = stringResource(R.string.step, 2), style = h3)
            Spacer(Modifier.height(10.dp))
            StepCounter(modifier = Modifier.fillMaxWidth(), 2)
            Spacer(Modifier.height(24.dp))
            HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
            Spacer(Modifier.height(24.dp))
            Text(text = stringResource(R.string.add_photos), style = h2)
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.remember_the_first_photo_is_the_first_thing),
                style = h4_grey
            )
            PhotoPickerWithPreview(
                imageUris = imageUris,
                onImageSelected = onImageSelected,
                onImageCancel = onImageCancel,
                onReorder = onReorder,
            )
            Spacer(Modifier.height(24.dp))
            Attention(
                modifier = Modifier.fillMaxWidth(),
                message = stringResource(R.string.upload_a_minimum_of_3_photos_of_your_facility)
            )
        }
    }

}


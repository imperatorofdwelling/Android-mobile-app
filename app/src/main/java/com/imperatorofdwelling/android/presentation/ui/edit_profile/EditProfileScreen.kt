package com.imperatorofdwelling.android.presentation.ui.edit_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.DefaultTopBar
import com.imperatorofdwelling.android.presentation.ui.components.GenderSelection
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.DateTextTrailing
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.EditTextTrailing
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

class EditProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<EditProfileViewModel>()
        val state = viewModel.state.collectAsState()
        val lce = viewModel.lce.collectAsState()
        Scaffold(
            topBar = {
                DefaultTopBar(stringResource(id = R.string.edit))
            }
        ) { paddingValues ->
            if (lce.value == LCE.Loading) {
                EditProfilePlaceholder(modifier = Modifier.padding(paddingValues))
            } else {
                EditProfileBody(
                    modifier = Modifier.padding(paddingValues),
                    name = state.value.name,
                    nameHasError = state.value.nameError,
                    nameLengthHasError = state.value.lengthNameError,
                    onNameChange = viewModel::onNameChange,
                    email = state.value.email,
                    onEmailChange = viewModel::onEmailChange,
                    emailHasError = state.value.emailError,
                    onNumberChange = viewModel::onPhoneChange,
                    numberHasError = state.value.phoneError,
                    date = state.value.date,
                    place = state.value.place,
                    number = state.value.phone,
                    onDateChange = viewModel::onDateChange,
                    dateHasError = state.value.dateError,
                    onCheckedMale = viewModel::onMaleSelected,
                    onCheckedFemale = viewModel::onFemaleSelected,
                    isSelectedMale = state.value.isMale,
                    isSelectedFemale = state.value.isFemale
                )
            }
        }
    }

    @Composable
    private fun EditProfileBody(
        modifier: Modifier,
        name: String,
        nameHasError: Boolean,
        nameLengthHasError: Boolean,
        onNameChange: (String) -> Unit,
        email: String,
        emailHasError: Boolean,
        onEmailChange: (String) -> Unit,
        number: String,
        onNumberChange: (String) -> Unit,
        numberHasError: Boolean,
        place: String,
        date: String,
        onDateChange: (String) -> Unit,
        dateHasError: Boolean,
        onCheckedMale : (Boolean) -> Unit,
        onCheckedFemale : (Boolean) -> Unit,
        isSelectedMale: Boolean,
        isSelectedFemale: Boolean,
    ) {
        var showGenderSelection by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(horizontal = largeDp)
                .then(modifier)
        ) {
            Spacer(modifier = Modifier.height(largeDp))

            EditTextTrailing(
                trailingIcon = painterResource(id = R.drawable.cross),
                onClickTrailing = { onNameChange("") },
                placeholderText = stringResource(id = R.string.name),
                value = name,
                onValueChanged = { newValue ->
                    onNameChange(newValue)
                },
                modifier = Modifier.fillMaxWidth(),
                errorString = if (nameHasError) {
                    stringResource(id = R.string.username_can_only_contain_letters_and_numbers)
                } else if (nameLengthHasError) {
                    stringResource(id = R.string.username_is_too_short)
                } else {
                    null
                },
                hasError = nameHasError || nameLengthHasError
            )
            Spacer(modifier = Modifier.height(largeDp))
            EditTextTrailing(
                trailingIcon = painterResource(id = R.drawable.cross),
                onClickTrailing = { onEmailChange("") },
                placeholderText = stringResource(id = R.string.email),
                value = email,
                onValueChanged = { newValue ->
                    onEmailChange(newValue)
                },
                errorString = stringResource(id = R.string.email_is_incorrect),
                hasError = emailHasError,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(largeDp))

            EditTextTrailing(
                trailingIcon = painterResource(id = R.drawable.cross),
                onClickTrailing = { onNumberChange("") },
                placeholderText = stringResource(R.string.phone_number),
                value = number,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChanged = { newValue ->
                    onNumberChange(newValue)
                },
                errorString = stringResource(R.string.number_is_incorrect),
                hasError = numberHasError,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(largeDp))

            DateTextTrailing(
                trailingIcon = painterResource(id = R.drawable.cross),
                onClickTrailing = { onDateChange("") },
                placeholderText = stringResource(R.string.date_of_birth),
                value = date,
                onValueChanged = { newValue ->
                    onDateChange(newValue)
                },
                errorString = stringResource(R.string.date_is_incorrect),
                hasError = dateHasError,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(largeDp))

            EditTextTrailing(
                trailingIcon = painterResource(id = R.drawable.cross),
                onClickTrailing = { },
                placeholderText = stringResource(R.string.where_i_live),
                value = place,
                onValueChanged = { newValue ->
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(largeDp))

            EditTextTrailing(
                trailingIcon = painterResource(id = R.drawable.cross),
                onClickTrailing = { },
                placeholderText = stringResource(R.string.gender),
                value = if(isSelectedMale){
                    stringResource(id = R.string.male)
                } else if (isSelectedFemale){
                    stringResource(id = R.string.female)
                } else {
                    ""
                },
                onValueChanged = { },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showGenderSelection = !showGenderSelection }
            )

            Spacer(modifier = Modifier.height(largeDp))
            EditTextTrailing(
                trailingIcon = painterResource(id = R.drawable.cross),
                onClickTrailing = { },
                placeholderText = stringResource(R.string.language),
                value = place,
                onValueChanged = { newValue ->
                },
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )


        }
        if(showGenderSelection){
            GenderSelection(
                onDismissRequest = {
                    showGenderSelection = false
                },
                onCheckedMale = onCheckedMale,
                onCheckedFemale = onCheckedFemale,
                isSelectedMale = isSelectedMale,
                isSelectedFemale = isSelectedFemale
            )
        }
    }

    @Composable
    private fun EditProfilePlaceholder(
        modifier: Modifier = Modifier
    ) {
        val shimmer = rememberShimmer(
            shimmerBounds = ShimmerBounds.View,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .then(modifier)
        ) {

            repeat(8) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .shimmer(shimmer)
                        .background(
                            color = Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(4.dp)
                        )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
package com.imperatorofdwelling.android.presentation.ui.edit_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.DateTextTrailing
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.EditTextTrailing
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import org.koin.androidx.compose.koinViewModel

class EditProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<EditProfileViewModel>()
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = {
                EditProfileTopBar()
            }
        ) { paddingValues ->
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
                dateHasError = state.value.dateError
            )
        }
    }

    @Composable
    private fun EditProfileTopBar() {

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
        dateHasError: Boolean
    ) {
        Column(modifier.padding(horizontal = largeDp)) {
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
                errorString = "Number is incorrect",
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
//                        place = newValue
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
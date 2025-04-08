package com.imperatorofdwelling.android.presentation.ui.landlord.creating.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.theme.GreyDividerColor
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

@Composable
fun Help(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = largeDp, vertical = extraLargeDp)
            .then(modifier),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(R.string.it_s_easy_to_get_started_on_imperator_of_dwelling),
                style = h2
            )
            val steps = listOf(
                Triple(
                    "Tell us about your property",
                    "Share basic information such \n" +
                            "as where it is located, type of\naccommodation and add a headline.",
                    painterResource(id = R.drawable.step_one),
                ),
                Triple(
                    "Make him stand out \n" +
                            "from the crowd",
                    "Add at least 5 great quality\n" + "photos so the renter can see\n" + "if the property is suitable.",
                    painterResource(id = R.drawable.step_two),
                ),
                Triple(
                    "Rules of stay",
                    "Write a policy on cancellation \n" +
                            "and check-in.",
                    painterResource(id = R.drawable.step_three),
                ),
                Triple(
                    "Finish up and publish",
                    "Choose a starting price, then\npublish your listing.",
                    painterResource(id = R.drawable.step_two),
                ),

                )
            steps.forEachIndexed { index, step ->
                Step(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = extraLargeDp),
                    textTitle = step.first,
                    textContent = step.second,
                    number = index + 1,
                    painter = step.third
                )
                if (index < steps.size - 1) {
                    HorizontalDivider(
                        Modifier.fillMaxWidth(),
                        thickness = 0.5.dp,
                        color = GreyDividerColor
                    )
                }
            }
        }
        PrimaryButton(
            Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.get_started)
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewHelp() {
    Help()
}
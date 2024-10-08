package com.mensinator.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import java.time.LocalDate


@Composable
fun StatisticsDialog(
    nextPeriodStart: String, // This actually needs to be calculated in CalendarScreen due to the calendar
    follicleGrowthDays: String,
    nextPredictedOvulation: String?, // This actually needs to be calculated in CalendarScreen due to the calendar
) {
    val context = LocalContext.current
    val dbHelper = remember { PeriodDatabaseHelper(context) }
    val calcHelper = remember { Calculations(context) }
    val averageCycleLength = calcHelper.averageCycleLength()
    val periodCount = dbHelper.getPeriodCount()
    val ovulationCount = dbHelper.getOvulationCount()
    val averagePeriodLength = calcHelper.averagePeriodLength()
    val avgLutealLength = calcHelper.averageLutealLength()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(text = stringResource(id = R.string.statistics_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Column(
            modifier = Modifier
                //.padding(16.dp)  // Padding around the text content
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.period_count, periodCount),
                fontSize = 16.sp
            )
            Text(
                text = stringResource(id = R.string.average_cycle_length, averageCycleLength),
                fontSize = 16.sp
            )
            Text(
                text = stringResource(id = R.string.average_period_length, averagePeriodLength),
                fontSize = 16.sp
            )
                Text(
                    text = if (nextPeriodStart < LocalDate.now().toString()) {
                        stringResource(id = R.string.next_period_start_past, nextPeriodStart)
                } else {
                        stringResource(id = R.string.next_period_start_future, nextPeriodStart)
                  },
                  fontSize = 16.sp
             )
            // Ovulation statistics
            Text(
                text = stringResource(id = R.string.ovulation_count, ovulationCount),
                fontSize = 16.sp
            )
            Text(
                text = stringResource(id = R.string.average_ovulation_day, follicleGrowthDays),
                fontSize = 16.sp
            )
                Text(
                    text = nextPredictedOvulation?.let {
                        stringResource(id = R.string.next_predicted_ovulation, it)
                    } ?: stringResource(id = R.string.next_predicted_ovulation_default),
                    fontSize = 16.sp
                )
            Text(
                text = stringResource(id = R.string.average_luteal_length, avgLutealLength),
                fontSize = 16.sp
            )
        }
    }
}
    /*
    confirmButton = {
        Button(onClick = onDismissRequest) {
            Text(stringResource(id = R.string.close_button))
        }
    },
    modifier = Modifier
//.padding(16.dp)
        .fillMaxWidth()
    )
}
*/
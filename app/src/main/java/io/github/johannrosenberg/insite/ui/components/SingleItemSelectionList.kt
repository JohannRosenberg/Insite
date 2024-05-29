package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R
import io.github.johannrosenberg.insite.utils.capitalize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleItemSelectionList(
    items: List< SingleItemSelectionListInfo>,
    title: String,
    onDismissed: (id: Any?) -> Unit
) {

    var selectedItem by remember { mutableStateOf(items.firstOrNull { it.selected }) }

    BasicAlertDialog(
        onDismissRequest = {
        }
    ) {
        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())) {

                Text( text = title.capitalize(), modifier = Modifier.padding(bottom = 20.dp))

                Column(modifier = Modifier.selectableGroup()) {
                    items.forEach {item ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = item.id == selectedItem?.id,
                                    onClick = { selectedItem = item},
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = item.id == selectedItem?.id,
                                onClick = null // null recommended for accessibility with screenreaders
                            )
                            Column {
                                Text(
                                    text = item.text,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(
                        onClick = {
                            onDismissed(null)
                        }
                    ) {
                        Text(App.context.getString(R.string.cancel))
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    TextButton(
                        onClick = {
                            onDismissed(selectedItem?.id)
                        }
                    ) {
                        Text(App.context.getString(R.string.okay))
                    }
                }
            }
        }
    }
}

data class SingleItemSelectionListInfo(
    val id: Any,
    val text: String,
    val selected: Boolean =  false
)
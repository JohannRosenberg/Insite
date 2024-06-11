package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.johannrosenberg.insite.da.LineInfo
import io.github.johannrosenberg.insite.da.LineType
import io.github.johannrosenberg.insite.ui.theme.MaterialColors

@Composable
fun Markdown(
    lines: MutableList<LineInfo>
) {
    lines.forEach {
        when (it.lineType) {
            LineType.H1, LineType.H2, LineType.H3, LineType.H4, LineType.H5, LineType.H6 -> {
                Text(
                    text = it.text, fontSize = when (it.lineType) {
                        LineType.H1 -> 24.sp
                        LineType.H2 -> 18.sp
                        LineType.H3 -> 20.sp
                        LineType.H4 -> 18.sp
                        LineType.H5 -> 16.sp
                        else -> 14.sp
                    }, fontWeight = FontWeight.SemiBold,
                    color = MaterialColors.cyan200,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            LineType.BulletedListItem -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = (10 * it.indentSize).dp, bottom = 10.dp)
                ) {
                    Text(text = "•", fontSize = 14.sp, modifier = Modifier.width(15.dp))
                    Text(text = it.text, fontSize = 14.sp)
                }

            }

            LineType.NumberedListItem -> {
                val numberEndPos = it.text.indexOf(" ")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = (10 * it.indentSize).dp, bottom = 10.dp)
                ) {
                    Text(text = it.text.substring(0, numberEndPos), fontSize = 14.sp, modifier = Modifier.width(30.dp))
                    Text(text = it.text.substring(numberEndPos + 1), fontSize = 14.sp)
                }
            }

            LineType.BlankLine -> {
                Spacer(modifier = Modifier.height(10.dp))
            }

            else -> {
                Text(text = it.text, fontSize = 14.sp, modifier = Modifier.padding(bottom = 10.dp))
            }
        }
    }
}
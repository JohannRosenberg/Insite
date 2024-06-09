package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.insite.da.web.IMAGES_PATH
import io.github.johannrosenberg.insite.models.Levels
import io.github.johannrosenberg.insite.models.Post
import io.github.johannrosenberg.insite.ui.theme.AppColors.challengeLevelEasyText
import io.github.johannrosenberg.insite.ui.theme.AppColors.challengeLevelHardText
import io.github.johannrosenberg.insite.ui.theme.AppColors.challengeLevelModerateText
import io.github.johannrosenberg.insite.ui.theme.MaterialColors

@Composable
fun PostHeader (
    post: Post,
    onPostClick: ((post: Post) -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .clickable {
                onPostClick?.invoke(post)
            }
    ) {
        AsyncImage(
            model = IMAGES_PATH + post.id + ".jpg",
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialColors.gray900,
                        Color(0x80616161)
                    )
                )
            )
            .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = Repository.getCategoryNameById(post.category),
                    fontSize = 12.sp
                )

                Box(
                    modifier = Modifier

                        .wrapContentSize(Alignment.Center)
                        .background(
                            color =
                            when (post.level) {
                                Levels.EASY -> challengeLevelEasyText
                                Levels.MODERATE -> challengeLevelModerateText
                                else -> challengeLevelHardText
                            },
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {
                    Text(
                        text = post.level.toString(), textAlign = TextAlign.End,
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(19.dp)
                            .padding(horizontal = 10.dp, vertical = 0.dp)
                            .offset(y = -2.dp),
                        color = MaterialColors.black,
                        fontSize = 12.sp
                    )
                }
            }


            Text(
                text = post.title,
                fontWeight = FontWeight.W300,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = post.date, fontSize = 12.sp
                )

                Text(text = post.author, fontSize = 12.sp)
            }
        }
    }
}
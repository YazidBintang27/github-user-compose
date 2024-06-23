package com.latihan.githubuser.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.latihan.githubuser.models.UserModel
import com.latihan.githubuser.ui.theme.GithubUserTheme

@Composable
fun UserFoundCard(
    modifier: Modifier = Modifier,
    userData: List<UserModel.Item?>,
    index: Int
) {
    UserFoundCardContent(
        modifier = modifier,
        userData = userData,
        index = index
    )
}

@Composable
fun UserFoundCardContent(
    modifier: Modifier,
    userData: List<UserModel.Item?>,
    index: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top = 20.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            ),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = userData[index]?.avatar_url),
                contentDescription = "Image Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 20.dp, end = 12.dp)
                    .clip(shape = RoundedCornerShape(50))
                    .width(50.dp)
                    .height(50.dp)
            )
            Text(
                text = userData[index]?.login ?: "",
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun UserFoundContentPreview() {
    GithubUserTheme {

    }
}

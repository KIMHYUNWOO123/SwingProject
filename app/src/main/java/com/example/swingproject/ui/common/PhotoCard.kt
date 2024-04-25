package com.example.swingproject.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.entity.PhotoData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoCard(
    item: () -> PhotoData,
    bookmarkData: () -> List<PhotoData>,
    onInsert: () -> Unit,
    onDelete: () -> Unit,
    isBookmarkFeed: Boolean = false,
    isLoading: Boolean,
) {
    var isLike by remember {
        mutableStateOf(bookmarkData.invoke().any {
            it.id == item.invoke().id
        })
    }
    LaunchedEffect(isLoading) {
        isLike = bookmarkData.invoke().any { it.id == item.invoke().id }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
        Card(
            onClick = {
                if (isLike) {
                    onDelete.invoke()
                    if (!isBookmarkFeed) {
                        isLike = false
                    }
                } else {
                    onInsert.invoke()
                    isLike = true
                }
            },
            shape = RoundedCornerShape(15.dp)
        ) {
            Box(
                modifier = Modifier.wrapContentSize()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.invoke().url)
                        .crossfade(true).build(), contentDescription = null,
                    modifier = Modifier.size(180.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Box(modifier = Modifier.size(50.dp), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = if (isLike) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = if (isLike) Color.Red else Color.White
            )
        }
    }
}
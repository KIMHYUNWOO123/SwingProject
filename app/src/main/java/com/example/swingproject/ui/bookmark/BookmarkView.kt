package com.example.swingproject.ui.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swingproject.R
import com.example.swingproject.ui.common.EmptyTerm
import com.example.swingproject.ui.common.LoadingBar
import com.example.swingproject.ui.common.PhotoCard

@Composable
fun BookmarkView(
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val photoData by viewModel.photoFlowData.collectAsState()
    val isLoading by viewModel.isLoading.observeAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(colorResource(id = R.color.main_bg1), colorResource(id = R.color.main_bg2), colorResource(id = R.color.main_bg3)))),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f), contentAlignment = Alignment.Center
            ) {
                Text(text = "총 ${photoData.size}개", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(), contentAlignment = Alignment.TopCenter
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(photoData.size) {
                        PhotoCard(
                            item = { photoData[it] },
                            bookmarkData = { photoData },
                            onInsert = { },
                            onDelete = { viewModel.deletePhoto(photoData[it].id) },
                            isBookmarkFeed = true,
                            isLoading = false
                        )
                    }
                }
                if (photoData.isEmpty()) {
                    EmptyTerm()
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (isLoading == true) {
                        LoadingBar()
                    }
                }
            }
        }
    }
}
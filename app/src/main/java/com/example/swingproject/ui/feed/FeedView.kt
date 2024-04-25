@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.swingproject.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.swingproject.R
import com.example.swingproject.ui.common.EmptyTerm
import com.example.swingproject.ui.common.LoadingBar
import com.example.swingproject.ui.common.PhotoCard
import kotlinx.coroutines.delay

@Composable
fun FeedView(
    viewModel: FeedViewModel = hiltViewModel(),
) {
    val (text: String, setValue: (String) -> Unit) = remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    val hideKeyboard = {
        focusManager.clearFocus()
    }
    val photoData = viewModel.photoPagingData.collectAsLazyPagingItems()

    val bookmarkData by viewModel.photoFlowData.collectAsState()

    var isLoading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(text) {
        if (text.isNotBlank()) {
            delay(300)
            viewModel.clearPhoto()
            viewModel.getPhotos(text)
        } else {
            delay(300)
            viewModel.clearPhoto()
        }
    }
    isLoading = photoData.loadState.prepend is LoadState.Loading || photoData.loadState.append is LoadState.Loading
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(colorResource(id = R.color.main_bg1), colorResource(id = R.color.main_bg2), colorResource(id = R.color.main_bg3))))
            .clickable(interactionSource = MutableInteractionSource(), indication = null) { hideKeyboard.invoke() }, contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.02f))
            OutlinedTextField(
                value = text, onValueChange = { setValue.invoke(it) },
                enabled = true,
                label = { Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.background(Color.Transparent)) },
                placeholder = { Text(text = "search for Photos") },
                maxLines = 1,
                shape = RoundedCornerShape(30.dp),
                singleLine = true,
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.02f))
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp), verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(photoData.itemCount) { index ->
                        photoData[index]?.let {
                            PhotoCard(
                                item = { it },
                                bookmarkData = { bookmarkData },
                                onInsert = {
                                    viewModel.insertPhoto(it)
                                },
                                onDelete = {
                                    viewModel.deletePhoto(it.id)
                                },
                                isLoading = isLoading
                            )
                        }
                    }
                }
            }
        }
        if (photoData.itemCount == 0) {
            EmptyTerm()
        }
        if (isLoading && photoData.itemCount != 0) {
            LoadingBar()
        }
    }
}

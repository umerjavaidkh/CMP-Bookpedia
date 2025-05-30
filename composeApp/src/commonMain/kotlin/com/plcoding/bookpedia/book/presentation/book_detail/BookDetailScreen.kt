package com.plcoding.bookpedia.book.presentation.book_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.book_description_unavailable
import cmp_bookpedia.composeapp.generated.resources.book_languages
import cmp_bookpedia.composeapp.generated.resources.book_ratings
import cmp_bookpedia.composeapp.generated.resources.book_synopsis
import com.plcoding.bookpedia.book.presentation.book_detail.components.BlurredImageBackground
import com.plcoding.bookpedia.book.presentation.book_detail.components.BookChip
import com.plcoding.bookpedia.book.presentation.book_detail.components.ChipSize
import com.plcoding.bookpedia.book.presentation.book_detail.components.TitleContent
import com.plcoding.bookpedia.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round


@Composable
fun BookDetailScreenRoot(
    viewModel: BookDetailViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    BookDetailScreen(state = state) { action ->
        when (action) {
            BookDetailAction.OnBackClick -> onBackClick()
            else -> Unit
        }
        viewModel.onAction(action)
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BookDetailScreen(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit
) {

    val book = state.book
    BlurredImageBackground(
        imageUrl = book?.imageUrl,
        isFavorite = state.isFavorite,
        onFavoriteClick = {
            onAction(BookDetailAction.OnFavoriteClick)
        },
        onBackClick = { onAction(BookDetailAction.OnBackClick) },
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.book != null) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollState) // 👈 Use verticalScroll for Column
                    .padding(
                        vertical = 16.dp,
                        horizontal = 24.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = state.book.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = state.book.authors.joinToString(),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    state.book.averageRating?.let { rating ->

                        TitleContent(
                            title = stringResource(Res.string.book_ratings)
                        ) {
                            BookChip(
                                modifier = Modifier
                            ) {
                                Text(text = "${round(rating * 10) / 10.0}")

                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = SandYellow
                                )
                            }
                        }

                    }

                    state.book.numPages?.let { pageCount ->
                        BookChip(
                            modifier = Modifier
                        ) {
                            Text(text = pageCount.toString())
                        }
                    }
                }

                if (state.book.languages.isNotEmpty()) {

                    TitleContent(
                        title = stringResource(Res.string.book_languages),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.wrapContentSize(Alignment.Center)
                        ) {
                            state.book.languages.forEach { it ->
                                BookChip(modifier = Modifier, size = ChipSize.SMALL) {
                                    Text(
                                        text = it.uppercase(),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }

                Text(
                    text = stringResource(Res.string.book_synopsis),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(
                            top = 24.dp,
                            bottom = 8.dp
                        )
                )

                if (state.isLoading) {
                    Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                        CircularProgressIndicator()
                    }
                } else {
                    Text(
                        text = state.book.description
                            ?: stringResource(
                                Res.string.book_description_unavailable
                            ),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        color = if (state.book.description.isNullOrBlank()) Color.Black.copy(alpha = 0.4f) else Color.Black,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .fillMaxWidth()
                            .padding(
                                top = 24.dp,
                                bottom = 8.dp
                            )
                    )
                }
            }
        }
    }
}
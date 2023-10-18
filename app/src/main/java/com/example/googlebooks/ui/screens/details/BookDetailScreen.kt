package com.example.googlebooks.ui.screens.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.googlebooks.R
import com.example.googlebooks.model.ui.DetailedBook




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    detailedBook: DetailedBook?,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateUp: Boolean = false
) {
    BackHandler {
        onNavigateUp()
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    if (detailedBook == null) {
        NoBookSelected(
            modifier = modifier
                .fillMaxSize()
        )
        return
    }

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BookDetailScreenTopBar(
                scrollBehavior = scrollBehavior,
                canNavigateUp = canNavigateUp,
                onNavigateUp = onNavigateUp,
            )
        }
    ) {
        BookDetails(
            detailedBook = detailedBook,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(horizontal = 32.dp, vertical = 16.dp)
        )
    }
}

@Composable
fun NoBookSelected(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ){
        Text(
            text = stringResource(R.string.no_book_selected),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier,
            color = MaterialTheme.colorScheme.onBackground
                .copy(alpha = 0.5f)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreenTopBar(
    canNavigateUp: Boolean,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        title = {
                Text(
                    text = "Book Detail"
                )
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            } else {
                Box {}
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun BookDetails(
    detailedBook: DetailedBook,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ThumbnailAndInfo(
            thumbnailSrc = detailedBook.thumbnailSrc,
            rating = detailedBook.averageRating,
            pageCount = detailedBook.pageCount,
            mainCategory = detailedBook.mainCategory,
        )
        Spacer(modifier = Modifier.height(32.dp))
        TitleAndAuthor(
            title = detailedBook.title,
            author = detailedBook.author,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Description(
            description = detailedBook.description,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
@Composable
fun ThumbnailAndInfo(
    thumbnailSrc: String,
    rating: Double?,
    pageCount: Int,
    mainCategory: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
    ) {
        Thumbnail(
            thumbnailSrc = thumbnailSrc,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
        Spacer(
            modifier = Modifier
                .width(16.dp)
        )
        InfoColumn(
            rating = rating,
            pageCount = pageCount,
            mainCategory = mainCategory
        )
    }
}

@Composable
fun Thumbnail(
    thumbnailSrc: String,
    modifier: Modifier = Modifier
) {
   Card(
       modifier = modifier,
       elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
   ){
        AsyncImage(
            model = thumbnailSrc,
            contentDescription = stringResource(id = R.string.book_thumbnail),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight(),
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.ic_broken_image)
        )
    }
}

@Composable
fun InfoColumn(
    rating: Double?,
    pageCount: Int,
    mainCategory: String?,
    modifier: Modifier = Modifier
) {
    val mainCategory = mainCategory?.split("/")?.get(0)
    Column(
        modifier = modifier.width(IntrinsicSize.Max)
    ) {
        InfoCard(
            imageVector = Icons.Outlined.Star,
            title = "Rating",
            text = if (rating != null) "$rating/5.0" else "N/A",
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoCard(
            imageVector = Icons.Outlined.Info,
            title = "Page Count",
            text = pageCount.toString(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoCard(
            imageVector = Icons.Outlined.AccountBox,
            title = "Main Category",
            text = mainCategory ?: "N/A",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun InfoCard(
    imageVector: ImageVector,
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .padding(12.dp)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = imageVector.name,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextAndTitleColumn(
                title = title,
                text = text,
            )
        }
    }
}

@Composable
fun TextAndTitleColumn(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Normal,
            maxLines = 1,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
        )
    }
}

@Composable
fun TitleAndAuthor(
    title: String,
    author: String?,
    modifier: Modifier = Modifier
) {
    val mainAuthor = author ?: stringResource(R.string.anonymous)
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Book by $mainAuthor",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun Description(
    description: String?,
    modifier: Modifier = Modifier
) {
    Text(
        text = description ?: stringResource(R.string.no_description_available),
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        modifier = modifier
    )
}

@Preview
@Composable
fun ThumbnailAndInfoPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    MaterialTheme {
        Surface {
            BookDetailsScreen(
                detailedBook = DetailedBook(
                    id = "",
                    title = "The greatest of all time",
                    author = "Nikoloz Nadaraia",
                    description = text,
                    pageCount = 256,
                    mainCategory = "Android",
                    averageRating = 4.5,
                    thumbnailSrc = ""
                ),
                onNavigateUp = {},
                canNavigateUp = true
            )
        }
    }
}



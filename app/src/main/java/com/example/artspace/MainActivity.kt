package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

data class Artwork(
    val titleId: Int,
    val artistId: Int,
    val yearId: Int,
    val descriptionId: Int,
    val imageId : Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace(arrayOf(
                        Artwork(
                            titleId = R.string.mona_lisa_title,
                            artistId = R.string.mona_lisa_artist,
                            yearId = R.string.mona_lisa_year,
                            descriptionId = R.string.mona_lisa_description,
                            imageId = R.drawable.mona_lisa
                        ),
                        Artwork(
                            titleId = R.string.starry_night_title,
                            artistId = R.string.starry_night_artist,
                            yearId = R.string.starry_night_year,
                            descriptionId = R.string.starry_night_description,
                            imageId = R.drawable.starry_night
                        ),
                        Artwork(
                            titleId = R.string.persistence_of_memory_title,
                            artistId = R.string.persistence_of_memory_artist,
                            yearId = R.string.persistence_of_memory_year,
                            descriptionId = R.string.persistence_of_memory_description,
                            imageId = R.drawable.persistence_of_memory
                        ),
                        Artwork(
                            titleId = R.string.guernica_title,
                            artistId = R.string.guernica_artist,
                            yearId = R.string.guernica_year,
                            descriptionId = R.string.guernica_description,
                            imageId = R.drawable.guernica
                        )
                    ))
                }
            }
        }
    }
}

@Composable
fun ArtSpace( artworks : Array<Artwork>, modifier: Modifier = Modifier) {
    var currentArtwork by remember { mutableStateOf(0)}
    val artworkSize = artworks.size
    ArtSpaceTextAndImage(
        textLabelTitlleId = artworks[currentArtwork].titleId,
        textLabelArtistId = artworks[currentArtwork].artistId,
        textLabelYearId = artworks[currentArtwork].yearId,
        drawableResourceId = artworks[currentArtwork].imageId,
        onBackButtonClick = {
            if(currentArtwork == 0){
                currentArtwork=artworkSize-1
            }
            else{
                currentArtwork--
            }

        },
        onNextButtonClick = {
            if(currentArtwork == artworkSize-1){
                currentArtwork = 0
            }
            else{
                currentArtwork++
            }

        },
        contentDescriptionResourceId = artworks[currentArtwork].descriptionId
    )


}
@Composable
fun ArtSpaceTextAndImage (
    textLabelTitlleId: Int,
    textLabelArtistId: Int,
    textLabelYearId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onBackButtonClick:() -> Unit,
    onNextButtonClick:() -> Unit,
    modifier: Modifier = Modifier
) {

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = contentDescriptionResourceId.toString(),
            modifier = Modifier
                .size(dimensionResource(R.dimen.image_size))
        )

        Spacer(modifier = Modifier.height(40.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.text_box_padding))
                .background(Color(230, 230, 230,))
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = textLabelTitlleId),
                fontSize = 25.sp
            )
            Row {
                Text(
                    text = stringResource(id = textLabelArtistId)+"   ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = textLabelYearId),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Spacer(modifier = Modifier.height(10.dp))

        }
        Row {
            Button(
                onClick = onBackButtonClick,
            ) {
                Text(
                    text = stringResource(id = R.string.back_button)
                )
            }
            Spacer(modifier = Modifier.width(60.dp))
            Button(
                onClick = onNextButtonClick,
            ) {
                Text(
                    text = stringResource(id = R.string.next_button)
                )
            }
        }

    }



}



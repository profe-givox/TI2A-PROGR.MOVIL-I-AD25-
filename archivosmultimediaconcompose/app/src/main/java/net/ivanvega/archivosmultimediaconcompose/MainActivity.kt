package net.ivanvega.archivosmultimediaconcompose

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil3.compose.AsyncImage
import com.google.android.exoplayer2.SimpleExoPlayer
import net.ivanvega.archivosmultimediaconcompose.providers.MiFileProviderMultimedia
import net.ivanvega.archivosmultimediaconcompose.ui.theme.ArchivosmultimediaconcomposeTheme

import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchivosmultimediaconcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //TakePictureComposable(modifier = Modifier.padding(innerPadding).fillMaxSize())
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    GrabarAudioScreen(
                        {},
                        {},
                        {},
                        {}

                    )

                }
            }
        }
    }
}

@Composable
fun TakePictureComposable ( modifier: Modifier = Modifier){

    var uriFile : Uri? = null
    val hayFoto = remember { mutableStateOf(false) }
    val hayVideo = remember { mutableStateOf(false) }
    var uriPhoto = remember { mutableStateOf<Uri?>(null) }


    val context = LocalContext.current



    val takePictureLauncher =   rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) {
        if (it) {
            // Handle result
            Log.d("TAGFOTO", "TakePictureComposable: $uriFile")
            hayFoto.value = it
            uriPhoto.value = uriFile

        }

    }

    val takeVidepLauncher =   rememberLauncherForActivityResult(
        ActivityResultContracts.CaptureVideo()
    ) {
        if (it) {
            // Handle result
            Log.d("TAGFOTO", "TakePictureComposable: $uriFile")
            uriPhoto.value = uriFile
            hayVideo.value = it


        }

    }


    Column (modifier = modifier) {


        Button(onClick = {

            uriFile = MiFileProviderMultimedia.getImageUri(context)

            takePictureLauncher.launch(uriFile!!)
        }) {
            Text(stringResource(R.string.take_picture_button))
        }

        Button(onClick = {

            uriFile = MiFileProviderMultimedia.getImageUri(context)

            takeVidepLauncher.launch(uriFile)
        }) {
            Text(stringResource(R.string.take_video_button))
        }


        if (hayFoto.value){
            Log.d("URIFOTO", uriPhoto.value.toString())
        AsyncImage(
            model = uriPhoto.value,
            contentDescription = stringResource(R.string.take_picture_description),
            modifier = Modifier.fillMaxWidth()
        )}
        if(hayVideo.value){
            VideoPlayer(videoUri = uriPhoto.value!!)
        }





    }

}

@Composable
fun VideoPlayer(videoUri: Uri, modifier: Modifier = Modifier.fillMaxWidth()) {
    val context = LocalContext.current
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
        }
    }
    val playbackState = exoPlayer
    val isPlaying = playbackState?.isPlaying ?: false

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
        modifier = modifier
    )

    IconButton(
        onClick = {
            if (isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
        },
        modifier = Modifier
            //.align(Alignment.BottomEnd)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = if (isPlaying) Icons.Filled.Refresh else Icons.Filled.PlayArrow,
            contentDescription = if (isPlaying) "Pause" else "Play",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArchivosmultimediaconcomposeTheme {
        Greeting("Android")
    }
}
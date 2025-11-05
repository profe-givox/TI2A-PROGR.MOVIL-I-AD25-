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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import net.ivanvega.archivosmultimediaconcompose.providers.MiFileProviderMultimedia
import net.ivanvega.archivosmultimediaconcompose.ui.theme.ArchivosmultimediaconcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchivosmultimediaconcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TakePictureComposable(modifier = Modifier.padding(innerPadding).fillMaxSize())
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                }
            }
        }
    }
}

@Composable
fun TakePictureComposable ( modifier: Modifier = Modifier){

    var uriFile : Uri? = null
    val hayFoto = remember { mutableStateOf(false) }
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


    Column (modifier = modifier) {


        Button(onClick = {

            uriFile = MiFileProviderMultimedia.getImageUri(context)

            takePictureLauncher.launch(uriFile)
        }) {
            Text(stringResource(R.string.take_picture_button))
        }


        if (uriPhoto.value != null){
            Log.d("URIFOTO", uriPhoto.value.toString())
        AsyncImage(
            model = uriPhoto.value,
            contentDescription = stringResource(R.string.take_picture_description),
            modifier = Modifier.fillMaxWidth()
        )}




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
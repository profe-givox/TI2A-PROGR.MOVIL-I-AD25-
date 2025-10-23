package net.ivanvega.archivosmultimediaconcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.ivanvega.archivosmultimediaconcompose.ui.theme.ArchivosmultimediaconcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchivosmultimediaconcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TakePictureComposable(modifier = Modifier.padding(innerPadding))
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

    Column {

        Button(onClick = {}) {
            Text(stringResource(R.string.take_picture_button))
        }

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
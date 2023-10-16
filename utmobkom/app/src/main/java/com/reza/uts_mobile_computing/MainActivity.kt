package com.reza.uts_mobile_computing

import android.graphics.Color
import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reza.uts_mobile_computing.ui.theme.UTS_Mobile_ComputingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTS_Mobile_ComputingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Cyan
                ) {
                    LazyVerticalGrid()
                }
            }
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
    UTS_Mobile_ComputingTheme {
        Greeting("Android")
    }
}
@Preview
@Composable
fun LazyVerticalGrid(){
    val list = (0..11).map { it.toString() }
    val sks_2 = stringResource(id = R.string.Dua_SKS)
    val sks_3 = stringResource(id = R.string.Tiga_SKS)
    val sks_1 = stringResource(id = R.string.Satu_SKS)
    var sks = arrayOf(sks_1, sks_2, sks_3)
    val anikom = stringResource(id = R.string.animasi_komputer)
    val calc1 = stringResource(id = R.string.kalkulus_1)
    val calc2 = stringResource(id = R.string.kalkulus_2)
    val kkn = stringResource(id = R.string.kkn)
    val kwh = stringResource(id = R.string.kewirausahaan)
    val komas = stringResource(id = R.string.komputer_masyarakat)
    val metlit = stringResource(id = R.string.metode_penelitian)
    val mobkom = stringResource(id = R.string.mobile_computing)
    val olim = stringResource(id = R.string.olimpisme)
    val graf = stringResource(id = R.string.pengantar_teori_graf)
    val tba = stringResource(id = R.string.teori_bahasa)
    val pkb = stringResource(id = R.string.pengantar_kecerdasan_buatan)
    val matkul = arrayOf(anikom,calc1,calc2,kkn,kwh,komas,metlit,mobkom,olim,graf,tba,pkb)

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),

        // content padding
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 24.dp,
            end = 16.dp,
            bottom = 20.dp
        ),
        content = {
            items(list.size) { index ->
                Row(
                    modifier = Modifier
                        .border(2.dp, Color.Red, RoundedCornerShape(10.dp))
                        .background(Color.Black)
                ) {
                    Column {
                        Bebekx()
                    }
                    Column {
                        Text(
                            text = matkul[index],
                            //text = "asd",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(top = 9.dp)
                        )
                        Text(
                            text = sks[(0..2).random()],
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Blue,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    )
}
@Composable
fun Bebekx (){
    val img = painterResource(R.drawable.duckduck__2_)
    Image(
        painter = img,
        contentDescription = "Bebekx",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .padding(9.dp)
            .border(2.dp, Color.Transparent, RoundedCornerShape(10.dp))
    )
}
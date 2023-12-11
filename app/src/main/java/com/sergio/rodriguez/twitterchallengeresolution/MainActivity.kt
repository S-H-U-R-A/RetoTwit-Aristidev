package com.sergio.rodriguez.twitterchallengeresolution

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sergio.rodriguez.twitterchallengeresolution.data.UsersDataSource
import com.sergio.rodriguez.twitterchallengeresolution.domain.model.Twit
import com.sergio.rodriguez.twitterchallengeresolution.ui.theme.TwitterChallengeResolutionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwitterChallengeResolutionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {

                    val dataSource: List<Twit> = UsersDataSource.listTwits

                    //TwitScreen(twits = dataSource) //Esto es propio con Columnas y Filas

                    TwitScreenCons(twits = dataSource) //Esto es propio usando constraint layout

                }

                /*

                Ejemplo Aristidevs

                Column(
                    modifier = Modifier.fillMaxSize().background(Color(0xFF161D26))
                ) {
                    TwitterCard()
                    TuitDivider()
                    TwitterCard()
                }*/


            }
        }
    }
}
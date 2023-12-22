package com.example.honinbo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.honinbo.ui.theme.HoninboTheme
import com.example.honinbo.game.Board
import com.example.honinbo.game.GameState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoninboTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val gamestate = GameState();
    gamestate.playMove(gamestate.getVertex(3,3), 0);
    gamestate.playMove(gamestate.getVertex(16,3), 1);
    gamestate.playMove(gamestate.getVertex(3,16), 0);
    gamestate.undoMove();
    gamestate.undoMove();
    Text(
        text = gamestate.toString(),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HoninboTheme {
        Greeting("Android")
    }
}
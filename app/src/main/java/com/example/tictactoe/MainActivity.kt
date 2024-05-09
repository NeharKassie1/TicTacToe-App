import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R

class MainActivity : AppCompatActivity() {

    private var currentPlayer = 1
    private var gameState = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonClick(view: View) {
        val buttonClicked = view as Button
        val buttonIndex = when(buttonClicked.id) {
            R.id.button1 -> 0
            R.id.button2 -> 1
            R.id.button3 -> 2
            R.id.button4 -> 3
            R.id.button5 -> 4
            R.id.button6 -> 5
            R.id.button7 -> 6
            R.id.button8 -> 7
            R.id.button9 -> 8
            else -> -1
        }

        if (gameState[buttonIndex] == 0) {
            gameState[buttonIndex] = currentPlayer

            if (currentPlayer == 1) {
                buttonClicked.text = "X"
            } else {
                buttonClicked.text = "O"
            }

            currentPlayer = if (currentPlayer == 1) 2 else 1

            checkWinner()
        }
    }

    private fun checkWinner() {
        val winningPositions = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        for (i in winningPositions.indices) {
            val (a, b, c) = winningPositions[i]
            if (gameState[a] == gameState[b] && gameState[b] == gameState[c] && gameState[a] != 0) {
                declareWinner(gameState[a])
                return
            }
        }

        if (!gameState.contains(0)) {
            declareWinner(0)
        }
    }

    private fun declareWinner(winner: Int) {
        val message: String = when (winner) {
            0 -> "It's a draw!"
            1 -> "Player X wins!"
            2 -> "Player O wins!"
            else -> ""
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun resetGame(view: View) {
        currentPlayer = 1
        gameState = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

        findViewById<Button>(R.id.button1).text = ""
        findViewById<Button>(R.id.button2).text = ""
        findViewById<Button>(R.id.button3).text = ""
        findViewById<Button>(R.id.button4).text = ""
        findViewById<Button>(R.id.button5).text = ""
        findViewById<Button>(R.id.button6).text = ""
        findViewById<Button>(R.id.button7).text = ""
        findViewById<Button>(R.id.button8).text = ""
        findViewById<Button>(R.id.button9).text = ""
    }
}

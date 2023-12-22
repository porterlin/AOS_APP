package com.example.honinbo.game

import com.example.honinbo.game.Board
import java.util.Vector

class GameState() {
    var gameHistory: Vector<Board> = Vector<Board>();
    var koHistory: Vector<Int> = Vector<Int>();
    var board: Board = Board();
    var moveNumber: Int = 0;

    init {
        clearBoard();
    }

    fun clearBoard() {
        resetBoard(getBoardSize(), getKomi());
    }

    fun resetBoard(boardSize: Int, komi: Float) {
        this.board.reset(boardSize, komi);
        this.gameHistory.clear();
        this.gameHistory.add(this.board.deepCopy());
        this.koHistory.clear();
        this.koHistory.add(this.board.computeHash());
        this.moveNumber = 0;
    }

    fun undoMove() {
        if (this.moveNumber <= 0) return;

        while (this.gameHistory.size > this.moveNumber) {
            this.gameHistory.removeAt(this.gameHistory.size-1);
        }
        this.moveNumber -= 1;
        this.board = this.gameHistory[this.moveNumber].deepCopy();
    }

    fun superko() : Boolean {
        val hash = board.computeHash();
        for (i in 0..this.moveNumber-2) {
            if (hash == this.koHistory[i]) {
                return true;
            }
        }
        return false;
    }

    fun playMove(vtx: Int, color: Int) : Boolean {
        if (!board.isLegal(vtx, color)) {
            return false;
        }
        if (vtx != this.board.resignMove) {
            this.board.playMoveAssumeLegal(vtx, color);
            this.moveNumber += 1;
            this.gameHistory.add(this.board.deepCopy());
            this.koHistory.add(this.board.computeHash());
        }
        return true;
    }

    override fun toString(): String {
        return this.board.toString();
    }

    fun isLegal(vtx: Int, color: Int) : Boolean {
        return this.board.isLegal(vtx, color);
    }

    fun getBoardSize() : Int {
        return this.board.boardSize;
    }

    fun getKomi() : Float {
        return this.board.komi;
    }

    fun getVertex(x: Int, y :Int) : Int {
        return this.board.getVertex(x,y);
    }

    fun getIndex(x: Int, y :Int) : Int {
        return this.board.getIndex(x,y);
    }

    fun setKomi(komi: Float) {
        this.board.komi = komi;
    }
}
package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.ir.annotations.Immutable;

public class Knight extends Piece {

  private final static int[] CANDIDATE_MOVE_COORDINATE = {-17, 15, -10, -6, 6, 10, 15, 17};

  Knight(final int piecePosition, final Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public List<Move> calculateLegalMoves(Board board) {

    int candidateDestinationCoordinate;
    final List<Move> legalMove = new ArrayList<>();

    for (final int currentCandidate: CANDIDATE_MOVE_COORDINATE) {
      candidateDestinationCoordinate = this.piecePosition + currentCandidate;

      if(true /*isValidTileCoordinate*/) {
        final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

        if(!candidateDestinationTile.isTileOccupied()) {
          legalMove.add(new Move());
        } else {
          final Piece pieceAtDestination = candidateDestinationTile.getPiece();
          final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

          if(this.pieceAlliance != pieceAlliance) {
            legalMove.add(new Move());
          }
        }
      }
    }
    return ImmutableList.copyOf(legalMove);
  }
}

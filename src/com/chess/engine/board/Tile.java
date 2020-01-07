package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.ir.annotations.Immutable;

public abstract class Tile {

  protected final int tileCoordinate;

  // is there's any, every possible empty tile that could ever exist, I've created it up
  // so I never have to construct them again
  // So, if i want the empty tile that references the first chess tile -> emptyTile.get(0)
  private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTile();

  private static Map<Integer, EmptyTile> createAllPossibleEmptyTile() {

    final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

    for(int i = 0; i<64;i++) {
      emptyTileMap.put(i, new EmptyTile(i));
    }
    // can use Collections.unmodifiableMap(emptyTileMap)
    // use ImmutableMap in guava library
    return ImmutableMap.copyOf(emptyTileMap); //After constructed emptyMap, we don't want anyone to be able to change it.
  }

  public static Tile createTile(final int tileCoordinate, final Piece piece) {
    return piece != null? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
  }

  private Tile(int tileCoordinate) {
    this.tileCoordinate = tileCoordinate;
  }
  public abstract boolean isTileOccupied();

  public abstract Piece getPiece();

  public static final class EmptyTile extends Tile {

    private EmptyTile(int coordinate) {
      super(coordinate);
    }

    @Override
    public boolean isTileOccupied() {
      return false;
    }

    @Override
    public Piece getPiece() {
      return null;
    }
  }

  public static final class OccupiedTile extends Tile {

    private final Piece pieceOnTile;

    private OccupiedTile(int tileCoordinate, Piece piece) {
      super(tileCoordinate);
      this.pieceOnTile = piece;
    }

    @Override
    public boolean isTileOccupied() {
      return true;
    }

    @Override
    public Piece getPiece() {
      return this.pieceOnTile;
    }
  }

}

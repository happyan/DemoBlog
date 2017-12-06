// IGameManager.aidl
package com.ishowtree.aidldemo;
import com.ishowtree.aidldemo.Game;
// Declare any non-default types here with import statements

interface IGameManager {
    List<Game>getGameList();
      void addGame(in Game game);
}

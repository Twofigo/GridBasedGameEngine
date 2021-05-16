javac -d release/ engine/*.java
javac -d release/ engine/Graphics/*.java
javac -d release/ engine/tools/*.java

javac -d release/ games/Sokoban/*.java

javac -d release/ games/TestGame/*.java

javac -d release/ games/Dungeon/*.java
javac -d release/ games/Dungeon/Inventory/*.java
javac -d release/ games/Dungeon/World/*.java

cp -R Texture/ release/

cd release/

jar cf engine.jar engine/*

jar cf Sokoban.jar engine/* Texture/* games/Sokoban/*
jar ufe Sokoban.jar games/Sokoban/Sokoban

jar cf Dungeon.jar engine/* Texture/* games/Dungeon/*
jar ufe Dungeon.jar games/Dungeon/DungeonMaster

jar cf SimpleGame.jar engine/* Texture/* games/TestGame/*
jar ufe SimpleGame.jar games/TestGame/SimpleGame

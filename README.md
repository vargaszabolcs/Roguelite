# Roguelite
Roguelite is a small roguelike game made in java for a university project.

<h1>Features</h1>
<h2>Framework</h2>
The framework contains the reusable, general part of the code. Most of the game code implements from these classes.
<h3>Scene System</h3>
Simple and easy to use scene system. Every scene has its own overrideable update loop where game logic should be implemented. Scenes also handle the updating and rendering of GameObjects and GameMaps.
<h3>Game Objects</h3>
GameObjects are objects that can be rendered in-game and can have their own logic. By default their update is turned off for performance purposes. To make them render images/textures, an image must be added using the setTexture function.
<h3>Game Maps</h3>
GameMaps are similar to GOs, they are renderable and can also contain logic. The difference is that these maps are always rendered and updated first!



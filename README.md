Project Overview
This project was developed for the Object-Oriented Programming (POO) course at ISCTE-IUL (2024/25). The goal was to build a functional game engine for a Donkey Kong-style arcade game, where a player (Jump-Man) must navigate platforms, avoid traps, and reach the goal while facing a dynamic adversary.

Key Technical Features
Singleton Pattern: Implemented in the GameEngine to centralize state management and entity updates.
OOP Architecture: Used abstract classes and interfaces to handle diverse entities (enemies, power-ups, obstacles) with autonomous interaction logic.
Collision & Physics: Custom logic for gravity ("falling" mechanics) and interaction-based damage systems.
Level Parsing: Dynamic level loading from .txt files, allowing for progressive difficulty and custom map layouts.
Data Persistence: Persistent high-score system implemented via File I/O.

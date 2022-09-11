@echo off
javac -d "build" engine/raytracing/*.java engine/pov/elements/*.java engine/ui/*.java engine/pov/reader/*.java
java -cp build/ engine.ui.Main
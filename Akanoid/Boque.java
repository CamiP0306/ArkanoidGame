package Akanoid;

class Bloque {
    int x, y; // Posición del bloque
    int ancho, alto; // Dimensiones del bloque
    boolean visible; // Indica si el bloque debe ser dibujado

    public Bloque(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.visible = true;
    }
}
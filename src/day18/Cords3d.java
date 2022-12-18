package day18;

import java.util.Objects;

public class Cords3d {

    int x;
    int y;
    int z;

    public Cords3d(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Cords3d[] getAdjacentCords() {
        Cords3d[] adjacent = new Cords3d[6];
        adjacent[0] = new Cords3d(x + 1, y, z);
        adjacent[1] = new Cords3d(x - 1, y, z);
        adjacent[2] = new Cords3d(x, y + 1, z);
        adjacent[3] = new Cords3d(x, y - 1, z);
        adjacent[4] = new Cords3d(x, y, z + 1);
        adjacent[5] = new Cords3d(x, y, z - 1);
        return adjacent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cords3d cords3d = (Cords3d) o;
        return x == cords3d.x && y == cords3d.y && z == cords3d.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}

package ru.ekabardinsky.humaninterface.template;

public class ProfilPoint implements Comparable<ProfilPoint> {
    private int i;
    private double x;
    private double y;

    public ProfilPoint(int i, double x, double y) {
        this.i = i;
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + i;
        return result;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProfilPoint other = (ProfilPoint) obj;
        if (i != other.i)
            return false;
        return true;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(ProfilPoint o) {
        return Integer.compare(i, o.getI());
    }


}

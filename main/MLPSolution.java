public class MLPSolution implements Comparable{
    double generizationAbility;
    int H1;
    int H2;
    int H3;
    
    public MLPSolution(double generizationAbility, int h1, int h2, int h3) {
        this.generizationAbility = generizationAbility;
        H1 = h1;
        H2 = h2;
        H3 = h3;
    }

    public double getGenerizationAbility() {
        return generizationAbility;
    }

    public void setGenerizationAbility(double generizationAbility) {
        this.generizationAbility = generizationAbility;
    }

    public int getH1() {
        return H1;
    }

    public void setH1(int h1) {
        H1 = h1;
    }

    public int getH2() {
        return H2;
    }

    public void setH2(int h2) {
        H2 = h2;
    }

    public int getH3() {
        return H3;
    }

    public void setH3(int h3) {
        H3 = h3;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(generizationAbility);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MLPSolution other = (MLPSolution) obj;
        if (Double.doubleToLongBits(generizationAbility) != Double.doubleToLongBits(other.generizationAbility))
            return false;
        return true;
    }

    @Override
    public int compareTo(Object o) {
        int res = 0;
        MLPSolution sol = (MLPSolution) o;
        if (this.generizationAbility - sol.getGenerizationAbility() > 0) res = 1;
        else if (this.generizationAbility - sol.getGenerizationAbility() < 0) res = -1;
        return res;
    }

    
}

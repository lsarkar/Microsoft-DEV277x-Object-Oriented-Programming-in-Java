public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    public Fraction() {
        this(0, 1);
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public void setDenominator(int denominator) {

        int multiplier = 1;

        // if negative denominator, bump the sign to the numerator
        if (denominator < 0) {
            multiplier = -1;
            this.numerator *= multiplier;
        }

        this.denominator = denominator*=multiplier;
    }

    public int getDenomimator() {
        return this.denominator;
    }

    @Override
    public String toString() {
        toLowestTerms();
        return String.format("%d/%d", this.numerator, this.denominator);
    }

    public double toDouble() {
        return this.numerator / this.denominator;
    }

    public Fraction add(Fraction fraction) {

        int summedNumerator = (this.numerator * fraction.getDenomimator()) + (fraction.getNumerator() * this.denominator);
        int commonDenomiator = this.getDenomimator() * fraction.getDenomimator();

        return new Fraction(summedNumerator, commonDenomiator);
    }

    public Fraction subtract(Fraction fraction) {
        int num =  (this.numerator * fraction.denominator) - (fraction.numerator * this.denominator);
        int denom =  fraction.denominator * this.denominator;

        return new Fraction(num,denom);
    }

    public Fraction multiply(Fraction fraction) {
        int n =  fraction.getNumerator() * this.numerator;
        int d =  fraction.getDenomimator() * this.denominator;

        return new Fraction(n,d);
    }

    public Fraction divide(Fraction fraction) {
        int n =  fraction.denominator * this.numerator;
        int d =  fraction.numerator * this.denominator;

        Fraction frac;
        if (n % d == 0)
            frac = new Fraction(n/d);
        else
            frac = new Fraction(n,d);

        return frac;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Fraction)) {
            return false;
        }

        Fraction f = (Fraction) obj;
        f.toLowestTerms();

        if (this.numerator == f.getNumerator() && this.denominator == f.getDenomimator()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void toLowestTerms() {
        //To convert a fraction to lowest terms we have to determine the greatest common divisor (factor)
        // between the numerator and denominator.
        // The greatest common divisor of two numbers a and b, is the largest number that evenly divides both a and b.

        int gcd = Fraction.gcd(this.numerator, this.denominator);

        this.numerator/=gcd;
        this.denominator/=gcd;
    }

    public static int gcd(int inA, int inB) {
        /*
        while a and b are not zero
        find the remainder of a divided by b
        set a to b
        set b to the remainder you found
        return a
         */

        int a = inA;
        int b = inB;

        while (a != 0 && b != 0) {
            int remainder = a % b;

            a = b;
            b = remainder;
        }

        return a;
    }
}

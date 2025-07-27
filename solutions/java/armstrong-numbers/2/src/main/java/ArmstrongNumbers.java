class ArmstrongNumbers {

    int cifras(int n) {
        int nc = 0;
        while (n != 0) {
            n /= 10;
            nc++;
        }
        return nc;
    }

    int pow(int base, int exp) {
        int res = 1;
        for (int i = 0; i < exp; ++i) {
            res *= base;
        }
        return res;
    }

    boolean isArmstrongNumber(int numberCheck) {
        int n = numberCheck;
        int numero_digitos = cifras(n);
        int sum = 0;
        while (n != 0) {
            sum += pow(n % 10, numero_digitos);
            n /= 10;
        }
        return sum == numberCheck;
    }

}

package by.epam.lab;

public enum RoundMethod {
    FLOOR {
        double roundFunction(double d) {
            return Math.floor(d);
        }
    },
    CEIL {
        double roundFunction(double d) {
            return Math.ceil(d);
        }
    },
    ROUND {
        double roundFunction(double d) {
            return Math.round(d);
        }
    };

    abstract double roundFunction(double value);

    private static final int[] tenPowD = {1, 10, 100, 1000, 10000,
            100000, 1000000, 10000000, 100000000};

    int round(double roundedValue, int d) {
        return (int) roundFunction(roundedValue / tenPowD[d]) * tenPowD[d];
    }

}


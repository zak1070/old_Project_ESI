package Model;

/**
 * The Code class represents a numerical code consisting of three digits.
 * Each digit must be between 1 and 5. This class provides methods to manipulate and retrieve information
 * about the code.
 */
public class Code {
    private final int code;

    /**
     * Constructor for Code.
     * Validates and sets the code. Each digit of the code must be between 1 and 5.
     *
     * @param code The three-digit code.
     * @throws TuringException If any digit of the code is not between 1 and 5.
     */
    public Code(int code) {
        // Extracts the digits
        int hundreds = code / 100;
        int tens = (code / 10) % 10;
        int units = code % 10;

        if (hundreds < 1 || hundreds > 5 ||
                tens < 1 || tens > 5 ||
                units < 1 || units > 5) {
            throw new TuringException("Each digit of the code must be between 1 and 5.");
        }
        this.code = code;
    }

    /**
     * Gets the hundreds place of the code.
     *
     * @return The hundreds digit of the code.
     */
    private int getHundreds() {
        return code / 100;
    }

    /**
     * Gets the tens place of the code.
     *
     * @return The tens digit of the code.
     */
    private int getTens() {
        return (code / 10) % 10;
    }

    /**
     * Gets the units place of the code.
     *
     * @return The units digit of the code.
     */
    private int getUnits() {
        return code % 10;
    }

    /**
     * Retrieves the full code.
     *
     * @return The integer representation of the code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns a string representation of the code.
     *
     * @return A string representing the code.
     */
    @Override
    public String toString() {
        return "" + getHundreds() + getTens() + getUnits();
    }

    /**
     * Retrieves a specific digit of the code based on its position.
     * Position 0 for hundreds, 1 for tens, and 2 for units.
     *
     * @param i The position of the digit to retrieve (0 for hundreds, 1 for tens, 2 for units).
     * @return The digit at the specified position.
     * @throws IllegalArgumentException If the position is invalid.
     */
    public int getDigit(int i) {
        return switch (i) {
            case 0 -> getHundreds();
            case 1 -> getTens();
            case 2 -> getUnits();
            default -> throw new IllegalArgumentException("Invalid digit position. Please enter 0 for hundreds, 1 for tens, or 2 for units.");
        };
    }

    /**
     * Gets the length of the code.
     *
     * @return The length of the code, which is always 3.
     */
    public int length() {
        return 3;
    }
}
